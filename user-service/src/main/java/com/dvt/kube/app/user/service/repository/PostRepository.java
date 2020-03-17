package com.dvt.kube.app.user.service.repository;

import com.dvt.kube.app.user.service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.description like %:description%")
    List<Post> findByDescription(String description);
}
