package org.abigfish.rest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.abigfish.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;


@RestController
@RequestMapping("/api/rag")
public class IndexController {

	@Autowired
	private DocumentService documentService;


	
	@GetMapping("/uploadInit")
	public ResponseEntity upload() {
		//documentService.uploadDocument(file);
		documentService.uploadDocumentInit();
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<String>> searchDoc(@RequestParam String keyword) {
		
		List<String> texts = new ArrayList<>();
		List<EmbeddingMatch<TextSegment>> list = documentService.search(keyword);
		if(!CollectionUtils.isEmpty(list)) {
			for(EmbeddingMatch<TextSegment> t:list) {
				texts.add(t.embedded().text());
			}
		}
		
		return ResponseEntity.ok(texts);
	}
	
	@GetMapping("/chat")
	public ResponseEntity chat(@RequestParam String message) throws UnsupportedEncodingException {
		return ResponseEntity.ok(documentService.chat(message));
	}


}