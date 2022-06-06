package org.it_academy.courses.hibernate.mappers;

import org.it_academy.courses.hibernate.dao.entity.Student;
import org.it_academy.courses.hibernate.dto.NewStudent;
import org.it_academy.courses.hibernate.dto.StudentId;
import org.it_academy.courses.hibernate.dto.UpdatedStudent;
import org.it_academy.courses.hibernate.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class StudentFactory implements IStudentFactory {
    private final List<String> errors = new ArrayList<>();
    private boolean isInvalid;

    private Long id;
    private String name;
    private Integer age;
    private Double score;
    private Boolean olympicGamer;

    @Override
    public <D extends NewStudent> Student get(D newStudent) {
        isInvalid = false;
        name = newStudent.getName();
        age = newStudent.getAge();
        score = newStudent.getScore();
        olympicGamer = newStudent.getOlympicGamer();

        checkName();
        checkAge();
        checkScore();
        checkOlympicGamer();

        if (isInvalid) {
            sendError();
        }

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setScore(score);
        student.setOlympicGamer(olympicGamer);

        return student;
    }

    @Override
    public <C extends UpdatedStudent> Student get(C updatedStudent) {
        id = updatedStudent.getId();
        name = updatedStudent.getName();
        age = updatedStudent.getAge();
        score = updatedStudent.getScore();
        olympicGamer = updatedStudent.getOlympicGamer();

        checkId();
        checkName();
        checkAge();
        checkScore();
        checkOlympicGamer();

        if (isInvalid) {
            sendError();
        }

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setScore(score);
        student.setOlympicGamer(olympicGamer);

        return student;
    }

    @Override
    public <U extends StudentId> Student get(U studentId) {
        id = studentId.getId();

        checkId();

        Student student = new Student();
        student.setId(studentId.getId());

        return student;
    }

    @Override
    public <N extends Student, O extends Student> void update(N n, O old) {

        if (n.getScore() == 0.0) {
            n.setScore(old.getScore());
        }

        if (!n.isOlympicGamer()) {
            n.setOlympicGamer(old.isOlympicGamer());
        }
    }

    private void checkId() {
        if (id == null || id < 1) {
            errors.add("id");
            isInvalid = true;
        }
    }

    private void checkName() {
        if (name == null || name.isEmpty() || name.length() > 255 ||
                name.matches("[\\s\\p{Alpha}]")) {
            errors.add("name");
            isInvalid = true;
        }
    }

    private void checkAge() {
        if (age == null || age < 1) {
            errors.add("age");
            isInvalid = true;
        }
    }

    private void checkScore() {
        if (score != null && (score <= 0.0 || score > 10.0)) {
            errors.add("score");
            isInvalid = true;

        }else if (score == null) {
            score = 0.0;
        }
    }

    private void checkOlympicGamer() {
        if (olympicGamer == null) {
            olympicGamer = false;
        }
    }

    private void sendError() {
        StringBuilder message = new StringBuilder("Incorrect");

        for (int i = 0; i < errors.size(); i++) {
            message.append(" ")
                    .append(errors.get(i))
                    .append(i != errors.size() - 1 ? "," : "");
        }

        throw new ValidationException(415, message.toString());
    }

}
