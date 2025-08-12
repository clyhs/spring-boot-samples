package org.abigfish.neo4j.entity;

import java.io.Serializable;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Node
public class Person2 implements Serializable {

	@Id @GeneratedValue
	private Long id;
	
	@Property("name")
	private String name;
}
