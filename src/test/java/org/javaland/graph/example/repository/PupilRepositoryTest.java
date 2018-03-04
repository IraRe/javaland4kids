package org.javaland.graph.example.repository;

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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PupilRepositoryTest {

    @Autowired
    private PupilRepository pupilRepository;

    @Before
    public void setUp() {
        Pupil ernst = new Pupil();
        ernst.setGivenName("Ernst");
        ernst.setFamilyName("Schlaumeyer");
        ernst.setAdditionalName("Von");
        ernst.setBirthDate(LocalDate.of(2009, 10, 11).toEpochDay());

        Pupil susi = new Pupil();
        susi.setGivenName("Susanne");
        susi.setFamilyName("Fröhlich");
        susi.setBirthDate(LocalDate.of(2010, 1, 11).toEpochDay());

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
        Assert.assertEquals("Susanne", pupils.get(0).getGivenName());
    }

    @After
    public void cleanUp() {
        pupilRepository.deleteAll();
    }

}
