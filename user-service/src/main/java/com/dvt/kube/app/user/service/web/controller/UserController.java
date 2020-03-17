package com.dvt.kube.app.user.service.web.controller;

import com.dvt.kube.app.user.service.entity.User;
import com.dvt.kube.app.user.service.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("userServiceJpaRepositoryImpl")
//    @Qualifier("userServiceJpaImpl")
//    @Qualifier("userServiceHibernateImpl")s
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model uiModel) {
        List<User> users = userService.findAll();
        uiModel.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") Long id, Model uiModel) {
        User user = userService.findById(id);
        uiModel.addAttribute("users", Arrays.asList(user));
        return "users";
    }
}
