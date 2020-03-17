package com.dvt.kube.app.user.service.service;

import com.dvt.kube.app.user.service.entity.Post;
import com.dvt.kube.app.user.service.entity.User;
import com.dvt.kube.app.user.service.exception.UserNotFoundException;
import com.dvt.kube.app.user.service.repository.UserRepository;
import com.dvt.kube.app.user.service.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Repository
@Transactional
public class UserServiceJpaRepositoryImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
//        return userRepository.findById(id.intValue()).orElse(null);
        User user = userRepository.findById(id.intValue()).orElse(null);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (user.getId() != null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findByAge(Long age) {
        return userRepository.findByAge(age.intValue());
    }

    @Override
    public List<User> findByComment(String comment) {
        return userRepository.findByComment(comment);
    }

    public List<Post> findAllUsersPost(Long id) {
        Optional<User> userOptional = userRepository.findById(id.intValue());

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }

        return userOptional.get().getPosts();
    }
}
