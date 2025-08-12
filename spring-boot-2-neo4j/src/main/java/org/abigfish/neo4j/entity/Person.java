package org.abigfish.neo4j.entity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
 
@Node
public class Person {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int age;
 
    // 空构造方法、带参数构造方法、getter 和 setter 方法
    public Person() {}
 
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
 
    // getter 和 setter 方法
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
}