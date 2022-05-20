package org.it_academy.MK_JD2_90_22.json.dto;

public class GroupRefresh {

    private final String oldName;
    private final String newName;

    public GroupRefresh(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

}
