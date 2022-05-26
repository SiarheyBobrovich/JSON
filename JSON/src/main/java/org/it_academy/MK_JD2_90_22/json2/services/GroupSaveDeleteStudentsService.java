package org.it_academy.MK_JD2_90_22.json2.services;

import org.it_academy.MK_JD2_90_22.json2.dao.GroupSaveDeleteStudentsDao;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.ICRDDao;
import org.it_academy.MK_JD2_90_22.json2.group.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json2.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json2.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.GroupSaveDeleteStudentNullException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.GroupSaveDeleteStudentsIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json2.exceptions.service.GroupSaveDeleteStudentsIllegalIDException;
import org.it_academy.MK_JD2_90_22.json2.services.api.ICDService;
import org.it_academy.MK_JD2_90_22.json2.dao.ValidationDao;

import java.util.List;
import java.util.Map;

public class GroupSaveDeleteStudentsService implements ICDService {

    private static final IValidationDao validator = ValidationDao.getInstance();
    private static final ICRDDao dao;
    private static final GroupSaveDeleteStudentsService instance;

    static {
        instance = new GroupSaveDeleteStudentsService();
        dao = GroupSaveDeleteStudentsDao.getInstance();
    }

    private GroupSaveDeleteStudentsService() {
    }

    @Override
    public void save(GroupStudentsList list) {
        delete(list);

        dao.save(list);
    }

    @Override
    public List<StudentsInGroup> getAll() {
        return dao.getAll();
    }

    @Override
    public StudentsInGroup get(long id) {
        if (id < 1) {
            throw new GroupSaveDeleteStudentsIllegalIDException();
        }
        return dao.get(id);
    }

    @Override
    public void delete(GroupStudentsList list) {
        check(list);

        dao.delete(list);
    }

    private void check(GroupStudentsList list) {

        for (Map.Entry<String, List<StudentId>> entry : list) {
            String groupName = entry.getKey();

            if (groupName == null) {
                throw new GroupSaveDeleteStudentNullException();
            }

            if (!validator.isExistGroup(groupName)) {
                throw new GroupSaveDeleteStudentsIllegalArgumentException("Такой группы не существует");
            }

            for (StudentId studentId : entry.getValue()) {
                if (!validator.isExistStudent(studentId.getId())) {
                    throw new GroupSaveDeleteStudentsIllegalArgumentException("Такого студента не существует");
                }
            }
        }
    }

    public static GroupSaveDeleteStudentsService getInstance() {
        return instance;
    }
}
