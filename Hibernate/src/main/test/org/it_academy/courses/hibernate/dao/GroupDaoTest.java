package org.it_academy.courses.hibernate.dao;

import org.it_academy.courses.hibernate.dao.entity.Group;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupDaoTest {

    private static final HibernateDao<Group> dao = GroupDao.getInstance();

    @Test
    void save() {
        Group group = new Group();
        group.setName("Олег");

        dao.save(group);
    }

    @Test
    void getAll() {
        List<Group> all = dao.getAll();
        all.forEach(x-> System.out.println(x.getStudents()));

    }
}