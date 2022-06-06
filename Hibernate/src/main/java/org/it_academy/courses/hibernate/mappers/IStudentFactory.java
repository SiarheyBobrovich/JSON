package org.it_academy.courses.hibernate.mappers;

import org.it_academy.courses.hibernate.dao.entity.Student;
import org.it_academy.courses.hibernate.dto.NewStudent;
import org.it_academy.courses.hibernate.dto.StudentId;
import org.it_academy.courses.hibernate.dto.UpdatedStudent;

public interface IStudentFactory {

    <C extends UpdatedStudent> Student get(C e);
    <U extends StudentId>  Student  get(U e);
    <D extends NewStudent>  Student  get(D e);
    <N extends Student, O extends Student> void update(N n, O old);
}
