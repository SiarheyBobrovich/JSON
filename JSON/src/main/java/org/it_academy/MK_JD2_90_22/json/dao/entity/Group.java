package org.it_academy.MK_JD2_90_22.json.dao.entity;

import org.it_academy.MK_JD2_90_22.json.dao.entity.api.IGroup;

import java.io.Serializable;
import java.util.Objects;

public class Group implements Serializable, IGroup {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return id == group.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
