package org.abigfish.service;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.abigfish.reader.ParagraphTextReader;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;


/**
 * @Description: 文档服务
 * @Author: jay
 * @Date: 2024/3/18 10:02
 * @Version: 1.0
 */
@Slf4j
@Service
public class DocumentService {

	@Autowired
	private VectorStore vectorStore;

	@Autowired
	private OllamaChatModel ollamaChatClient;
	//private OllamaChatClient ollamaChatClient;

	// TODO 请将路径修改为你的本地路径
	private static final String PATH = "d:/Temp/data/";
	
	
	public void uploadDocumentInit() {
		
		
		File file1 = new File(PATH+"/aigc.txt");
		
		List<Document> documentList = paragraphTextReader(file1);
		vectorStore.add(documentList);
	}


	/**
	 * 使用spring ai解析txt文档
	 *
	 * @param file
	 * @throws MalformedURLException
	 */
	public void uploadDocument(MultipartFile file) {
		//保存file到本地
		String textResource = file.getOriginalFilename();
		//判断文件是否是TXT
		if (!textResource.endsWith(".txt")) {
			throw new RuntimeException("只支持txt格式文件");
		}
		String filepath = PATH + textResource;
		File file1 = new File(filepath);
		if (file1.exists()) {
			throw new RuntimeException("文件已存在");
		}
		try {
			file.transferTo(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Document> documentList = paragraphTextReader(file1);
		vectorStore.add(documentList);
	}

	private List<Document> paragraphTextReader(File file) {
		List<Document> docs = null;
		try {
			ParagraphTextReader reader = new ParagraphTextReader(new FileUrlResource(file.toURI().toURL()), 5);
			reader.getCustomMetadata().put("filename", file.getName());
			reader.getCustomMetadata().put("filepath", file.getAbsolutePath());
			docs = reader.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}

	/**
	 * 合并文档列表
	 *
	 * @param documentList 文档列表
	 * @return 合并后的文档列表
	 */
	private List<Document> mergeDocuments(List<Document> documentList) {
		if(documentList == null || documentList.isEmpty()){
			return new ArrayList<>();
		}
		List<Document> mergeDocuments = new ArrayList<>();
		mergeDocuments.add(documentList.get(0));
		return mergeDocuments;
	}

	/**
	 * 根据关键词搜索向量库
	 *
	 * @param keyword 关键词
	 * @return 文档列表
	 */
	public List<Document> search(String keyword) {
		return mergeDocuments(vectorStore.similaritySearch(keyword));
	}

	/**
	 * 问答,根据输入内容回答
	 *
	 * @param message 输入内容
	 * @return 回答内容
	 */
	public String chat(String message) {
		//查询获取文档信息
		long startTime = System.currentTimeMillis();
		List<Document> documents = search(message);
		long endTime = System.currentTimeMillis();
		log.info("redis search time:{}", endTime - startTime);

		//提取文本内容
		String content = documents.stream()
				.map(Document::getContent)
				.collect(Collectors.joining("\n"));

		//封装prompt并调用大模型
		String chatPrompt2String = getChatPrompt2String(message, content);
		log.info("chat prompt:{}", chatPrompt2String);
//		Prompt prompt = new Prompt();
//		ollamaChatClient.call(prompt);
		String chatResponse = ollamaChatClient.call(chatPrompt2String);
		log.info("chat response:{}", chatResponse);
		return chatResponse;
	}

	/**
	 * 获取prompt
	 *
	 * @param message 提问内容
	 * @param context 上下文
	 * @return prompt
	 */
	private String getChatPrompt2String(String message, String context) {
		String promptText = """
				请用仅用以下内容回答"%s":
				%s
				
				
				""";
		return String.format(promptText, message, context);
	}
}
