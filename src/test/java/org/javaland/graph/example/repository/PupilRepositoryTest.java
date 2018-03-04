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
public class PupilRepositoryTest {

    @Autowired
    private PupilRepository pupilRepository;

    @Before
    public void setUp() {

        Clazz mathematics = new Clazz();
        mathematics.setName("Mathematik");

        Clazz geography = new Clazz();
        geography.setName("Erdkunde");

        Pupil ernst = new Pupil();
        ernst.setGivenName("Ernst");
        ernst.setFamilyName("Schlaumeyer");
        ernst.setAdditionalName("Von");
        ernst.setBirthDate(LocalDate.of(2009, 10, 11).toEpochDay());
        ernst.addClazz(geography);

        Pupil susi = new Pupil();
        susi.setGivenName("Susanne");
        susi.setFamilyName("Fröhlich");
        susi.setBirthDate(LocalDate.of(2010, 1, 11).toEpochDay());
        susi.setClazzes(Arrays.asList(mathematics, geography));

        pupilRepository.save(ernst);
        pupilRepository.save(susi);
    }

    @Test
    public void testFindAll() {
        Iterable<Pupil> pupils = pupilRepository.findAll();
        Assert.assertNotNull(pupils);
        Assert.assertEquals(2, pupils.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFindByFamilyName() {
        List<Pupil> pupils = pupilRepository.findByFamilyName("Fröhlich");
        Assert.assertNotNull(pupils);
        Assert.assertEquals(1, pupils.size());
        Pupil susi = pupils.get(0);
        Assert.assertEquals("Susanne", susi.getGivenName());
        Assert.assertEquals(2, susi.getClazzes().size());
    }

    @After
    public void cleanUp() {
        pupilRepository.deleteAll();
    }

}
