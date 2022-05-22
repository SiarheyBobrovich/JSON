package org.it_academy.MK_JD2_90_22.json.dao.api;

import org.it_academy.MK_JD2_90_22.json.api.CRUD.ICC;
import org.it_academy.MK_JD2_90_22.json.api.CRUD.IDC;
import org.it_academy.MK_JD2_90_22.json.api.CRUD.IRC;
import org.it_academy.MK_JD2_90_22.json.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group.GroupName;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;

public interface ICRDDao extends ICC<GroupStudentsList>, IDC<GroupStudentsList>, IRC<StudentsInGroup> {
}
