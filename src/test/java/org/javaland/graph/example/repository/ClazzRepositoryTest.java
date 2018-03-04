package org.javaland.graph.example.repository;


import org.javaland.graph.example.entity.Clazz;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClazzRepositoryTest {

    @Autowired
    private ClazzRepository clazzRepository;

    @Before
    public void setUp() {
        Clazz mathematics = new Clazz();
        mathematics.setName("Mathematik");

        Clazz geography = new Clazz();
        geography.setName("Erdkunde");

        clazzRepository.save(mathematics);
        clazzRepository.save(geography);
    }

    @Test
    public void testFindAll(){
        Iterable<Clazz> classes = clazzRepository.findAll();
        Assert.assertNotNull(classes);
        Assert.assertEquals(2, classes.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFindByClassName() {
        Clazz erdkunde = clazzRepository.findByName("Erdkunde");
        Assert.assertNotNull(erdkunde);
        Assert.assertNotNull(erdkunde.getId());
    }

    @After
    public void cleanUp() {
        clazzRepository.deleteAll();
    }
}
