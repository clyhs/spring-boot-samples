package org.abigfish.neo4j.entity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
 
@Node
public class Movie {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String genre;
 
    // 空构造方法、带参数构造方法、getter 和 setter 方法
    public Movie() {}
 
    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }
 
    // getter 和 setter 方法
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getGenre() {
        return genre;
    }
 
    public void setGenre(String genre) {
        this.genre = genre;
    }
 
    // 定义从 Movie 到 Person 的关系（演员参演）
    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private Person actor;
 
    public Person getActor() {
        return actor;
    }
 
    public void setActor(Person actor) {
        this.actor = actor;
    }
}