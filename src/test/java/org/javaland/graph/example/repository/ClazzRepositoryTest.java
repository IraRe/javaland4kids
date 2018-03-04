package org.javaland.graph.example.repository;


import org.javaland.graph.example.entity.Clazz;
import org.javaland.graph.example.entity.Pupil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClazzRepositoryTest {

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Before
    public void setUp() {
        Clazz mathematics = new Clazz();
        mathematics.setName("Mathematik");

        Clazz geography = new Clazz();
        geography.setName("Erdkunde");

        clazzRepository.save(mathematics);
        clazzRepository.save(geography);

        Pupil susi = new Pupil();
        susi.setGivenName("Susanne");
        susi.setFamilyName("Fröhlich");
        susi.setBirthDate(LocalDate.of(2010, 1, 11).toEpochDay());
        susi.setClazzes(Arrays.asList(mathematics, geography));
        pupilRepository.save(susi);

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

    @Test
    public void testFindByAttendee() {
        Pupil susi = pupilRepository.findByFamilyName("Fröhlich").get(0);
        List<Clazz> classes = clazzRepository.findByAttendee(susi.getId());
        Assert.assertNotNull(classes);
        Assert.assertEquals(2, classes.size());
    }

    @After
    public void cleanUp() {
        clazzRepository.deleteAll();
        pupilRepository.deleteAll();
    }
}
