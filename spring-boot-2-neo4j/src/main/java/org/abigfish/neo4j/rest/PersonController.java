package org.abigfish.neo4j.rest;
import org.abigfish.neo4j.entity.Person;
import org.abigfish.neo4j.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;
 
    // 添加 Person
    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }
 
    // 根据姓名查询 Person
    @GetMapping("/{name}")
    public Person getPersonByName(@PathVariable String name) {
        return personService.findPersonByName(name);
    }
 
    // 删除所有 Person
    @DeleteMapping
    public void deleteAllPersons() {
        personService.deleteAllPersons();
    }
}