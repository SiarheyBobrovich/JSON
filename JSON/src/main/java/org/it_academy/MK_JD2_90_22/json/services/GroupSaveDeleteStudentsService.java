package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.GroupSaveDeleteStudentsDao;
import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.ICRDDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.entity.StudentsInGroup;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.GroupSaveDeleteStudentNullException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.GroupSaveDeleteStudentsIllegalArgumentException;
import org.it_academy.MK_JD2_90_22.json.exceptions.service.GroupSaveDeleteStudentsIllegalIDException;
import org.it_academy.MK_JD2_90_22.json.services.api.ICDService;

import java.util.List;
import java.util.Map;

public class GroupSaveDeleteStudentsService implements ICDService {

    private static final IValidationDao validator = ValidationDao.getInstance();
    private static final ICRDDao dao =
            GroupSaveDeleteStudentsDao.getInstance();
    private static final GroupSaveDeleteStudentsService instance;

    static {
        instance = new GroupSaveDeleteStudentsService();
    }

    private GroupSaveDeleteStudentsService() {
    }

    @Override
    public void save(GroupStudentsList list) {
        if (list == null) {
            throw new GroupSaveDeleteStudentNullException();
        }

        check(list);

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

        if (list == null) {
            throw new GroupSaveDeleteStudentNullException();
        }

        check(list);

        dao.delete(list);
    }

    public static GroupSaveDeleteStudentsService getInstance() {
        return instance;
    }

    private void check(GroupStudentsList list) {
        for (Map.Entry<String, List<StudentId>> entry : list) {
            String groupName = entry.getKey();

            if (validator.isExistGroup(groupName)) {
                throw new GroupSaveDeleteStudentsIllegalArgumentException("Такая группа уже существует");
            }

            for (StudentId studentId : entry.getValue()) {
                if (validator.isExistStudent(studentId.getId())) {
                    throw new GroupSaveDeleteStudentsIllegalArgumentException("Такой студент уже существует");
                }
            }
        }
    }
}
