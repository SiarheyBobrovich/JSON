package org.it_academy.courses.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.it_academy.courses.hibernate.dao.entity.Group;

import java.util.List;

public class GroupDao extends HibernateDao<Group>{

    private static final GroupDao instance = new GroupDao();

    private GroupDao() {
    }

    @Override
    public long save(Group group) {
        return super.save(group);
    }

    @Override
    public void delete(Group group) {
        super.delete(group);
    }

    @Override
    public List<Group> getAll() {
        EntityManager manager = getFactory().getManager();
        manager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Group> query = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = query.from(Group.class);
        query.select(root);

        List<Group> resultList = manager.createQuery(query).getResultList();
        manager.getTransaction().commit();
        manager.close();

        return resultList;
    }

    @Override
    public Group get(long id) {
        EntityManager manager = getFactory().getManager();
        manager.getTransaction().begin();

        Group group = manager.find(Group.class, id);

        manager.getTransaction().commit();
        manager.close();

        return group;
    }

    @Override
    public void update(Group group) {
        EntityManager manager = getFactory().getManager();

        manager.getTransaction().begin();

        Group currentGroup = manager.find(Group.class, group.getId(), LockModeType.OPTIMISTIC);

        currentGroup.setName(group.getName());

        manager.getTransaction().commit();
        manager.close();
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
