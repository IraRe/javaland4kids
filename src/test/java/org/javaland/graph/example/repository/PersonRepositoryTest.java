package org.javaland.graph.example.repository;

import org.javaland.graph.example.entity.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        Person jane = new Person();
        jane.setGivenName("Jane");
        jane.setFamilyName("Doe");
        jane.setBirthDate(LocalDate.of(2000, 12, 27).toEpochDay());
        jane.setEmail("jane@doe.com");
        Person max = new Person();
        max.setGivenName("Max");
        max.setFamilyName("Mustermann");
        max.setBirthDate(LocalDate.of(1978, 4,12).toEpochDay());
        max.setEmail("max@mustermann.de");
        personRepository.save(jane);
        personRepository.save(max);
    }

    @Test
    public void findAllTest() {
        Iterable<Person> persons = personRepository.findAll();
        Assert.assertNotNull(persons);
        Assert.assertEquals(2, persons.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void findByFamilyNameTest() {
        List<Person> persons = personRepository.findByFamilyName("Mustermann");
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
        Assert.assertEquals(1, persons.size());
        Person person = persons.get(0);
        Assert.assertEquals("Max", person.getGivenName());
    }

    @After
    public void cleanUp() {
        personRepository.deleteAll();
    }
}
