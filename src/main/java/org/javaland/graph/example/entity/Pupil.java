package org.javaland.graph.example.entity;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Pupil extends Person {

    @Relationship(type = "TAKES")
    private List<Clazz> clazzes;

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }

    public void addClazz(Clazz clazz) {
        if (this.clazzes == null) {
            this.clazzes = new ArrayList<>();
        }
        this.clazzes.add(clazz);
    }
}
