package org.abigfish.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;

@Configuration
public class MilvusConfig {
	
	@Autowired
	private MilvusProperties milvusProperties;
	
	@Bean
	public EmbeddingStore build() {
		return  MilvusEmbeddingStore.builder()
	            .uri(milvusProperties.getUrl())
	            .collectionName(milvusProperties.getCollectionName())
	            .dimension(milvusProperties.getEmbeddingDimension())
	            .indexType(IndexType.FLAT)
	            .metricType(MetricType.IP)
	            .build();
	}
	
	
}
