package org.abigfish.rest;

import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/milvus")
public class MilvusController {
	@Autowired
    private VectorStore vectorStore;
	
	@GetMapping("/addDoc")
	public String addDocuments() {
        List<Document> documents = List.of(
            new Document("Exploring the depths of AI with Spring", Map.of("category", "AI")),
            new Document("Navigating through large datasets efficiently"),
            new Document("Harnessing the power of vector search", Map.of("importance", "high"))
        );

        vectorStore.add(documents);
        return "add";
    }

}
