package ru.dzmakats.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dzmakats.entity.Task;

import java.util.List;

/**
 * Created by Denis Zolotarev on 10.02.2024
 */
@RequiredArgsConstructor
@Repository
public class TaskRepository {

    private final SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Task> getAll(int offset, int limit) {
        Query<Task> query = getSession().createQuery("select t from Task t", Task.class);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public int getAllCount() {
        Query<Long> query = getSession().createQuery("select count(t) from Task t", Long.class);
        return Math.toIntExact(query.getSingleResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Task getById(long id) {
        Query<Task> query = getSession().createQuery("select t from Task t where t.id=:ID", Task.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task task) {
        getSession().persist(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task) {
        getSession().remove(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
