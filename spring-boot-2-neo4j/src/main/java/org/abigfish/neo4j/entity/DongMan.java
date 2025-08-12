package org.abigfish.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import java.io.Serializable;

/**
 * @className:
 * @author: zhaopeng
 * @description: TODO(......)
 * @date: 2021年12月27日 9:09
 */
@Data
@Node("dongMan")
public class DongMan implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;
    @Property
    private String age;
    @Property
    private String sex;
}
