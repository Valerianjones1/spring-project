package com.example.springproject.controller;

import java.security.Principal;
import java.util.List;

import com.example.springproject.dto.UserDto;
import com.example.springproject.entity.Gruz;
import com.example.springproject.entity.User;
import com.example.springproject.service.GruzService;
import com.example.springproject.service.UserService;
import com.example.springproject.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @Autowired
    private GruzService gruzService;

    private UserService userService;

    public AppController(GruzService gruzService, UserService userService) {
        this.gruzService = gruzService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Gruz> listGruzs = gruzService.listAll(keyword);
        model.addAttribute("listGruzs", listGruzs);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/auth")
    public String viewAuthPage() {
        return "auth";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String showNewGruzForm(Model model) {
        Gruz gruz = new Gruz();
        model.addAttribute("gruz", gruz);
        return "new_gruz";
    }

    @PostMapping("/save")
    public String saveGruz(@Valid @ModelAttribute("gruz") Gruz gruz, BindingResult result, Model model) {
        boolean isThereAreErrors = result.hasErrors();
        if (isThereAreErrors) {
            if (gruz.getId() != null) {
                model.addAttribute("gruz", gruz);
                return "edit_gruz";
            }
            model.addAttribute("gruz", gruz);
            return "new_gruz";
        }
        gruzService.save(gruz);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditGruzForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_gruz");
        Gruz gruz = gruzService.get(id);
        mav.addObject("gruz", gruz);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteGruz(@PathVariable(name = "id") Long id) {
        gruzService.delete(id);
        return "redirect:/";
    }
}
