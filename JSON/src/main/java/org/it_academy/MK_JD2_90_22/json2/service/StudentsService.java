package org.it_academy.MK_JD2_90_22.json2.service;

import org.it_academy.MK_JD2_90_22.json2.exceptions.StudentsDaoExceptionIllegal;
import org.it_academy.MK_JD2_90_22.json2.dao.StudentDao;
import org.it_academy.MK_JD2_90_22.json2.dao.api.ICRUDStudentDao;
import org.it_academy.MK_JD2_90_22.json2.dao.entity.Student;
import org.it_academy.MK_JD2_90_22.json2.dto.NewStudent;
import org.it_academy.MK_JD2_90_22.json2.dto.StudentId;
import org.it_academy.MK_JD2_90_22.json2.dto.UpdatedStudent;
import org.it_academy.MK_JD2_90_22.json2.exceptions.StudentsServiceException;
import org.it_academy.MK_JD2_90_22.json2.service.api.ICRUDStudentsService;

import java.util.List;

public class StudentsService implements ICRUDStudentsService {

    private static final StudentsService instance = new StudentsService();
    private static final ICRUDStudentDao dao = StudentDao.getInstance();

    private StudentsService() {}

    @Override
    public long save(NewStudent newStudent) {
        String name = newStudent.getName();

        if (name == null || name.isEmpty() || name.length() > 255 || name.matches("[\\s\\p{Alpha}]")) {
            throw new StudentsServiceException(400, "Incorrect name");
        }

        if (newStudent.getAge() == null || newStudent.getAge() < 1) {
            throw new StudentsServiceException(400, "Incorrect age");
        }

        return dao.save(map(newStudent.getName(), newStudent.getAge(),
                newStudent.getScore() == null ? 0.0 : newStudent.getScore(),
                newStudent.getOlympicGamer() != null)
        );
    }

    @Override
    public void delete(StudentId studentId) {
        if (studentId.getId() == null) {
            throw new StudentsServiceException(415, "Unsupported media type");
        }

        Student student;

        try {
            student = dao.get(studentId.getId());

        }catch (StudentsDaoExceptionIllegal e) {
            student = new Student();
            student.setId(studentId.getId());
        }

        try {
            dao.delete(student);

        }catch (StudentsDaoExceptionIllegal e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getAll() {
        try {
            return dao.getAll();

        }catch (StudentsDaoExceptionIllegal e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public Student get(long id) {
        try {
            return dao.get(id);

        }catch (StudentsDaoExceptionIllegal e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    @Override
    public void update(UpdatedStudent updatedStudent) {
        String name = updatedStudent.getName();
        Integer age = updatedStudent.getAge();
        Double score = updatedStudent.getScore();
        Boolean olympicGamer = updatedStudent.getOlympicGamer();

        if (updatedStudent.getId() == null) {
            throw new StudentsServiceException(400, "Incorrect id");
        }

        if (name != null &&
                (name.isEmpty() || name.length() > 255 || name.matches("[\\s\\p{Alpha}]"))) {
            throw new StudentsServiceException(400, "Incorrect name");
        }

        if (age != null && age < 1) {
            throw new StudentsServiceException(400, "Incorrect age");
        }

        if (score != null && score < 0.0) {
            throw new StudentsServiceException(400, "Incorrect score");
        }

        Student student;

        try {
            student = get(updatedStudent.getId());

        }catch (StudentsDaoExceptionIllegal e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }

        if (name != null) {
            student.setName(name);
        }

        if (age != null) {
            student.setAge(age);
        }

        if (score != null) {
            student.setScore(score);
        }

        if (olympicGamer != null) {
            student.setOlympicGamer(olympicGamer);
        }

        try {
            dao.update(student);
        }catch (StudentsDaoExceptionIllegal e) {
            throw new StudentsServiceException(e.getStatus(), e.getMessage(), e);
        }
    }

    private Student map(String name, int age, double score, boolean olympicGamer) {
        Student student = new Student();

        student.setName(name);
        student.setAge(age);
        student.setScore(score);
        student.setOlympicGamer(olympicGamer);

        return student;
    }

    public static StudentsService getInstance() {
        return instance;
    }
}
