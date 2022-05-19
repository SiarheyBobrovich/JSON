package org.it_academy.MK_JD2_90_22.json.dao.entity;

import java.io.Serializable;

public class Group implements Serializable {

    private long id;

    private String name;

    public Group(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
