package org.abigfish.neo4j.entity;

import java.time.LocalDate;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Data
@Node("Product") 
public class Product {

	@Id
    @GeneratedValue  // 自动生成ID
    private Long id;
    
    @Property("name")  // 映射到节点的属性
    private String name;
    
}
