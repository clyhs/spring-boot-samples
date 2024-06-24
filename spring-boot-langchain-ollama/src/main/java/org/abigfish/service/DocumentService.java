package org.abigfish.service;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;
import static java.util.stream.Collectors.joining;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class DocumentService {

	private static final String PATH = "d:/Temp/data/";
	
	@Autowired
	private EmbeddingStore embeddingStore;
	@Autowired
	private EmbeddingModel embeddingModel;
	@Autowired
	private ChatLanguageModel chatLanguageModel;
	

	
	public void uploadDocumentInit() {
		
		//String path = this.getClass().getResource("/").getPath();
		//path = path +"/documents/miles-of-smiles-terms-of-use.txt";
		String path = "d:/eclipse64/workspace/spring-boot-samples/spring-boot-langchain-ollama/src/main/resources/documents/tiancom.txt";
		//String path = "/documents/miles-of-smiles-terms-of-use.txt";
		log.info("doc: {}",path);
		File file = new File(path);
		DocumentParser documentParser = new TextDocumentParser();
		Document document = loadDocument(path, documentParser);
		
		DocumentSplitter splitter = DocumentSplitters.recursive(300, 0);
        List<TextSegment> segments = splitter.split(document);
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
        embeddingStore.addAll(embeddings, segments);
        
	}
	
	public List<EmbeddingMatch<TextSegment>> search(String keyword){
		Embedding queryEmbedding = embeddingModel.embed(keyword).content();
		int maxResults = 3;
	    double minScore = 0.7;
		List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, maxResults,minScore);
		//EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);
		return relevant;
	}
	public String chat(String message) throws UnsupportedEncodingException {
		long startTime = System.currentTimeMillis();
		List<EmbeddingMatch<TextSegment>> relevantEmbeddings = search(message);
		long endTime = System.currentTimeMillis();
		log.info("redis search time:{}", endTime - startTime);
		
		String information = relevantEmbeddings.stream()
                .map(match -> match.embedded().text())
                .collect(joining("\n\n"));
		String str = new String(information.getBytes(),"utf8");
		
		log.debug(str);
		
		Prompt promp = getChatPrompt(message,str);
		
		AiMessage aiMessage = chatLanguageModel.generate(promp.toAiMessage()).content();
		log.debug(aiMessage.text());
		String answer = new String(aiMessage.text().getBytes(),"utf8");
		
		return answer;
	}
	
	private Prompt getChatPrompt(String question, String information) {
		PromptTemplate promptTemplate = PromptTemplate.from(
                "请用仅用以下内容回答:\n"
                        + "\n"
                        + "问题:\n"
                        + "{{question}}\n"
                        + "\n"
                        + "答案:\n"
                        + "{{information}}");
		Map<String, Object> variables = new HashMap<>();
        variables.put("question", question);
        variables.put("information", information);
        Prompt prompt = promptTemplate.apply(variables);
        return prompt;
	}

}
