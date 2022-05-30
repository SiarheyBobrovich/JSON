package org.it_academy.MK_JD2_90_22.json2.service;

import org.it_academy.MK_JD2_90_22.json2.dao.DaoValidator;
import org.it_academy.MK_JD2_90_22.json2.dao.GroupDao;
import org.it_academy.MK_JD2_90_22.json2.dao.api.ICRUDGroupDao;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupId;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Group;
import org.it_academy.MK_JD2_90_22.json2.dao.StudentDao;
import org.it_academy.MK_JD2_90_22.json2.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.exceptions.api.CoursesIllegalStateException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.StudentsInGroupsServiceException;
import org.it_academy.MK_JD2_90_22.json2.dao.StudentsInGroupsDao;
import org.it_academy.MK_JD2_90_22.json2.dao.api.ICrossDaoController;
import org.it_academy.MK_JD2_90_22.json2.service.api.ICrossServiceController;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupStudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.GroupWithStudents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentsInGroupsService implements ICrossServiceController {

    private static final StudentsInGroupsService instance = new StudentsInGroupsService();

    private final ICrossDaoController<Group, Student> dao = StudentsInGroupsDao.getInstance();
    private final ICRUDGroupDao groupDao = GroupDao.getInstance();
    private final ICRUDStudentDao studentDao = StudentDao.getInstance();
    private StudentsInGroupsService() {}

    @Override
    public void save(GroupId groupId, StudentId studentId) {
        long id = studentId.getId();

        try {
            Group group = groupDao.get(groupId.getId());
            Student student = studentDao.get(id);

            if (DaoValidator.getInstance().isExistCrossStudent(id)) {
                throw new StudentsInGroupsServiceException(409, "Conflict");
            }

            dao.save(group, student);
        }catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public void delete(GroupStudentId ids) {
        try {
            long studentId = ids.getStudentId();
            long groupId = ids.getGroupId();

            if (studentId == 0 && groupId == 0) {
                throw new StudentsInGroupsServiceException(415, "Unsupported media type");
            }

            if (studentId != 0) {
                dao.deleteS(studentDao.get(studentId));

            }else {
                dao.deleteG(groupDao.get(groupId));
            }

        } catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public List<GroupWithStudents> getAll() {
        Map<Group, List<Student>> all;

        try {
            all = dao.getAll();

        } catch (CoursesIllegalStateException e) {
            throw new StudentsInGroupsServiceException(e.getStatus(), e.getMessage(), e);
        }

        List<GroupWithStudents> groups = new ArrayList<>(all.size());

        for (Map.Entry<Group, List<Student>> entry : all.entrySet()) {
            Group group = entry.getKey();
            List<Student> students = entry.getValue();

            groups.add(new GroupWithStudents(
                    group.getId(), group.getName(), students)
            );
        }

        return groups;
    }

    @Override
    public List<Student> getG(long id) {
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
