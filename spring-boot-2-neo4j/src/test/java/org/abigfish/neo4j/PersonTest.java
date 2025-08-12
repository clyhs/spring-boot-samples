package org.abigfish.neo4j;
import org.abigfish.neo4j.dao.Person2RelationRepository;
import org.abigfish.neo4j.dao.Person2Repository;
import org.abigfish.neo4j.entity.Person2;
import org.abigfish.neo4j.entity.Person2Relation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonTest {
 
    @Autowired
    private Person2Repository personRepository;
 
    @Autowired
    private Person2RelationRepository personRelationRepository;
 
    @Test
    public void testSave() {
        Person2 person = Person2.builder().name("唐僧").build();
        Person2 person2 = Person2.builder().name("孙悟空").build();
        Person2 person3 = Person2.builder().name("猪八戒").build();
        Person2 person4 = Person2.builder().name("沙僧").build();
        Person2 person5 = Person2.builder().name("白龙马").build();
        List<Person2> personList = new ArrayList<>(Arrays.asList(
                person, person2, person3, person4, person5));
        personRepository.saveAll(personList);
        System.out.println("person 数据保存成功");
 
        Person2Relation personRelation = new Person2Relation(person, person2, "徒弟");
        Person2Relation personRelation2 = new Person2Relation(person, person3, "徒弟");
        Person2Relation personRelation3 = new Person2Relation(person, person4, "徒弟");
        Person2Relation personRelation4 = new Person2Relation(person, person5, "徒弟");
        List<Person2Relation> personRelationList = new ArrayList<>(Arrays.asList(
                personRelation, personRelation2, personRelation3,
                personRelation4
        ));
        // 保存关系数据
        personRelationRepository.saveAll(personRelationList);
        System.out.println("person 关系数据保存成功");
    }
 
}