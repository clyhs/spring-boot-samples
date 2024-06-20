package org.abigfish.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="abigfish.milvus")
public class MilvusProperties {
	
	private String url;
	
	
	private String collectionName;
	
	private Integer embeddingDimension;
	
	private String metricType;
	
	private String indexType;

}
