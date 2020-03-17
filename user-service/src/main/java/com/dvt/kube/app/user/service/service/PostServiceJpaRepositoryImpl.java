package com.dvt.kube.app.user.service.service;

import com.dvt.kube.app.user.service.entity.User;
import com.dvt.kube.app.user.service.entity.Post;
import com.dvt.kube.app.user.service.exception.UserNotFoundException;
import com.dvt.kube.app.user.service.repository.PostRepository;
import com.dvt.kube.app.user.service.repository.UserRepository;
import com.dvt.kube.app.user.service.service.api.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Repository
@Transactional
public class PostServiceJpaRepositoryImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post create(Long userId, Post post) {
        Optional<User> userOptional = userRepository.findById(userId.intValue());

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + userId);
        }

        User user = userOptional.get();
        post.setUser(user);

        return postRepository.save(post);
    }
}
