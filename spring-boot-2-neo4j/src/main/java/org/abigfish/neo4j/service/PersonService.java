package org.abigfish.neo4j.service;
import org.abigfish.neo4j.dao.PersonRepository;
import org.abigfish.neo4j.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
 
    // 保存 Person
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
 
    // 根据姓名查询 Person
    public Person findPersonByName(String name) {
        return personRepository.findByName(name);
    }
 
    // 删除所有 Person
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}