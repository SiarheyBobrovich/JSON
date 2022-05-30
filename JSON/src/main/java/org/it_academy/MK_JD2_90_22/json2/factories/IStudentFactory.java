package org.it_academy.MK_JD2_90_22.json2.factories;

import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dto.NewStudent;
import org.it_academy.MK_JD2_90_22.json2.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedStudent;

public interface IStudentFactory {

    <C extends UpdatedStudent> Student get(C e);
    <U extends StudentId>  Student  get(U e);
    <D extends NewStudent>  Student  get(D e);
    <N extends Student, O extends Student> void update(N n, O old);
}
