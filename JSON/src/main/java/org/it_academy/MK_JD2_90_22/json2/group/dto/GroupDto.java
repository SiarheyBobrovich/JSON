package org.it_academy.MK_JD2_90_22.json2.group.dto;

import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;

import java.util.Objects;

public class GroupDto {

    private final long id;
    private final String newName;

    public GroupDto(long id, String newName) {
        this.id = id;
        this.newName = newName;
    }

    public long getId() {
            return id;
        }

    public String getNewName() {
            return newName;
        }
}
