package org.it_academy.MK_JD2_90_22.json.services;

import org.it_academy.MK_JD2_90_22.json.dao.GroupSaveDeleteStudentsDao;
import org.it_academy.MK_JD2_90_22.json.dao.ValidationDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.ICDDao;
import org.it_academy.MK_JD2_90_22.json.dao.api.IValidationDao;
import org.it_academy.MK_JD2_90_22.json.dto.group_student.GroupStudentsList;
import org.it_academy.MK_JD2_90_22.json.dto.student.StudentId;
import org.it_academy.MK_JD2_90_22.json.services.api.ICDService;

import java.util.List;
import java.util.Map;

public class GroupSaveDeleteStudentsService implements ICDService {

    private static final IValidationDao validator = ValidationDao.getInstance();
    private static final ICDDao dao =
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
            throw new IllegalArgumentException("Данные не переданы");
        }

        check(list);

        dao.save(list);
    }

    @Override
    public void delete(GroupStudentsList list) {

        if (list == null) {
            throw new IllegalArgumentException("Данные не переданы");
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

            if (!validator.isExistGroup(groupName)) {
                //Изменить
                throw new RuntimeException("Такая группа уже существует");
            }

            for (StudentId studentId : entry.getValue()) {
                if (!validator.isExistStudent(studentId.getId())) {
                    //Изменить
                    throw new RuntimeException("Такой студент уже существует");
                }
            }
        }
    }

}
