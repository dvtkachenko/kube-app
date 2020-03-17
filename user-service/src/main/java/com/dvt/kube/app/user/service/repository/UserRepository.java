package com.dvt.kube.app.user.service.repository;

import com.dvt.kube.app.user.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.comment like %:comment%")
    List<User> findByComment(String comment);

    List<User> findByName(String name);
    List<User> findByAge(Integer age);
}
