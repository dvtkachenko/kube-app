package com.dvt.kube.app.user.service.service;

import com.dvt.kube.app.user.service.entity.User;
import com.dvt.kube.app.user.service.service.api.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

//@Service
//@Transactional
public class UserServiceHibernateImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceHibernateImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    private void init() {

        if (sessionFactory != null) {
            logger.info("UserServiceHibernateImpl is created, sessionFactory is injected ", sessionFactory);
        } else {
            logger.error("UserServiceHibernateImpl is created, sessionFactory is NOT injected ");
        }
    }

    @Override
    public List<User> findAll() {
        return getCurrentSession().createNamedQuery(User.NQ_FIND_ALL).list();
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void save(User user) {
        getCurrentSession().persist(user);
    }

    @Override
    public User create(User user) {
        getCurrentSession().persist(user);
        // TODO return
        return user;
    }

    @Override
    public User update(User user) {
        return (User)getCurrentSession().merge(user);
    }

    @Override
    public void delete(User user) {
        getCurrentSession().remove(user);
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public List<User> findByAge(Long age) {
        return null;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
