package com.homunculum.webSpringTrial.controller;

import com.homunculum.webSpringTrial.module.User;
import com.homunculum.webSpringTrial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegisterPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "register";
        }else {
            userService.saveUser(user);
        }
        return "index";
    }

}
