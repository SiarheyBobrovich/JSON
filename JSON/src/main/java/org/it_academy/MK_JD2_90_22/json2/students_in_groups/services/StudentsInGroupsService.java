package org.it_academy.MK_JD2_90_22.json2.students_in_groups.services;

import org.it_academy.MK_JD2_90_22.json2.group.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.group.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.group.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.student.dao.StudentDao;
import org.it_academy.MK_JD2_90_22.json2.student.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json2.student.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.student.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.student.exceptions.api.CoursesIllegalStateException;
import org.it_academy.MK_JD2_90_22.json2.student.exceptions.studentsInGroups.StudentsInGroupsServiceException;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.StudentsInGroupsDao;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api.ICrossDaoController;
import org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.api.ICrossServiceController;

import java.util.Map;
import java.util.Set;

public class StudentsInGroupsService implements ICrossServiceController {

    private static final StudentsInGroupsService instance = new StudentsInGroupsService();

    private final ICrossDaoController<Group, Student> dao = StudentsInGroupsDao.getInstance();
    private final ICRUDGroupDao groupDao = GroupDao.getInstance();
    private final ICRUDStudentDao studentDao = StudentDao.getInstance();
    private StudentsInGroupsService() {}

    @Override
    public void save(GroupId groupId, StudentId studentId) {
        try {
            Group group = groupDao.get(groupId.getId());
            Student student = studentDao.get(studentId.getId());

            dao.save(group, student);
        }catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public void deleteG(GroupId groupId) {
        try {
            dao.deleteG(groupDao.get(groupId.getId()));

        }catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public void deleteS(StudentId studentId) {
        try {
            dao.deleteS(studentDao.get(studentId.getId()));

        } catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public Map<Group, Set<Student>> getAll() {
        try {
            return dao.getAll();

        } catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public Map.Entry<Group, Set<Student>> getG(long id) {
        try {
            return dao.getG(id);

        } catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public Group getS(long id) {
        try {
            return dao.getS(id);

        } catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    public static StudentsInGroupsService getInstance() {
        return instance;
    }
}
