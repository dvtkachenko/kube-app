package com.dvt.kube.app.user.service.web.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.dvt.kube.app.user.service.entity.GCPMessage;
import com.dvt.kube.app.user.service.entity.Post;
import com.dvt.kube.app.user.service.entity.User;
import com.dvt.kube.app.user.service.service.api.PostService;
import com.dvt.kube.app.user.service.service.UserServiceJpaImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.dvt.kube.app.user.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MainRestController {

    private static Logger logger = LoggerFactory.getLogger(MainRestController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
//    @Qualifier("userServiceJpaRepositoryImpl")
    @Qualifier("userServiceJpaImpl")
//    @Qualifier("userServiceHibernateImpl")
//    private UserService userService;
    private UserServiceJpaImpl userService;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index() {
        return "It is main Spring boot rest controller!";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
//        exception trigger
//        int result = 1/0;
//        logger.error("result of division " + result);
        return userService.findAll();
    }

    @GetMapping("/users/by")
    public List<User> getUserByName(@RequestParam Map<String, String> allParams) {
        if(allParams.get("name") != null) {
            return userService.findByName(allParams.get("name"));
        }
        if(allParams.get("age") != null) {
//            return userService.findByAge(Long.valueOf(allParams.get("age")));
            return userService.findByAge(allParams.get("age"));
        }
        if(allParams.get("comment") != null) {
            return userService.findByComment(allParams.get("comment"));
        }
        return null;
    }

//    @GetMapping("/users/by")
//    public List<User> getUserByName(@RequestParam(name="name", required = false) String name) {
//        return userService.findByName(name);
//    }

//    @GetMapping("/users")
//    public List<User> getUserByAge(@RequestParam(name="age", defaultValue = "18", required = false) long age) {
//        return userService.findByAge(age);
//    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        final User user = userService.findById(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    // HATEOAS example
    @GetMapping("/hateoas/users/{id}")
    public Resource<User> getHateoasUserById(@PathVariable("id") long id) {
        final User user = userService.findById(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    // test Google Cloud Platform Pub/Sub
    @PostMapping("/message")
    public void createMessage(@RequestBody GCPMessage message) {
        System.out.println("Google Cloud Platform message received -> " + message.toString());
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//        userService.save(user);
        User createdUser = userService.create(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
//        final User deletedUser = userService.findById(id);

        final User deletedUser = userService.findById(id);
        if(deletedUser == null) {
            throw new UserNotFoundException("id-" + id);
        }
        userService.delete(deletedUser);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable("id") long userId, @RequestBody Post post) {
        Post createdPost = postService.create(userId, post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

//    @GetMapping("/greeting-i18n")
//    public String greetingInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message", null, locale);
//    }

    @GetMapping("/greeting-i18n")
    public String greetingInternationalized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
    //    @ExceptionHandler annotated method is only active for that particular Controller, not globally for the entire application.
//    @ExceptionHandler({Exception.class})
    public String handleException() throws Exception {
        logger.error("Exception was handled !!");
//        throw new Exception("Something must have gone wrong on server !");
        return "Something must have gone wrong on server side !";
    }

}
