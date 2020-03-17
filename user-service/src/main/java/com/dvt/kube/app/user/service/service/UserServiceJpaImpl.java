package com.dvt.kube.app.user.service.service;

import com.dvt.kube.app.user.service.entity.User;
import com.dvt.kube.app.user.service.service.api.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Repository
@Transactional
public class UserServiceJpaImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createNamedQuery(User.NQ_FIND_ALL).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id.intValue());
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User create(User user) {
        em.persist(user);
        // TODO return
        return user;
    }

    @Override
    public User update(User user) {
        if (user.getId() != null) {
            return em.merge(user);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public List<User> findByName(String name) {
//        for SQL injection example
        final Query sqlQuery = em.createNativeQuery("select ut.* from user_table ut where ut.user_name like " + name, User.class);
        return sqlQuery.getResultList();
    }

    @Override
    public List<User> findByAge(Long age) {
        return null;
    }

    public List<User> findByAge(String age) {
//        for SQL injection example
        final Query sqlQuery = em.createNativeQuery("select ut.* from user_table ut where ut.user_age = " + age, User.class);
        return sqlQuery.getResultList();
    }
}
