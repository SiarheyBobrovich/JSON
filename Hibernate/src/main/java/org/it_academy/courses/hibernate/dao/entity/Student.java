package org.it_academy.courses.hibernate.dao.entity;

import jakarta.persistence.*;
import org.it_academy.courses.hibernate.dao.api.IEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 	1.1 Порядковый номер (int)
 * 	1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 	1.3 возраст (7-17)
 * 	1.4 оценка(0.0-10.0)
 * 	1.5 признак участия в олимпиадах (bool).
 */

@Entity
@Table(name = "students")
public class Student implements IEntity, Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            targetEntity = Group.class
    )
    @JoinTable(name = "students_in_groups", joinColumns = @JoinColumn(name = "student_id"))
    private Group group;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "score")
    private double score;

    @Column(name = "olympic_gamer")
    private boolean olympicGamer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public void setOlympicGamer(boolean olympicGamer) {
        this.olympicGamer = olympicGamer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return age == student.age &&
                olympicGamer == student.olympicGamer &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, olympicGamer);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", olympicGamer=" + olympicGamer +
                '}';
    }
}
