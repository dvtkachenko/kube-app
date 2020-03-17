package com.dvt.kube.app.user.service.service.api;

import com.dvt.kube.app.user.service.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    User create(User user);
    User update(User user);
    void delete(User user);
    List<User> findByName(String name);
    List<User> findByAge(Long age);
//    List<User> findByAge(String age);
    default List<User> findByComment(String comment) { return null; };
}
