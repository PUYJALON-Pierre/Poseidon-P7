package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Controller @RequestMapping("/user")
public class UserController {
  final static Logger logger = LogManager.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request user/list");
    model.addAttribute("users", userRepository.findAll());
    return "user/list";
  }

  @GetMapping("/add")
  public String addUser(User bid) {
    logger.debug("Getting request user/add");
    return "user/add";
  }

  @PostMapping("/validate")
  public String validate(@Valid User user, BindingResult result, Model model) {
    logger.debug("Posting request user/validate for user with id:{}",user.getId());
    if (!result.hasErrors()) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user);
      model.addAttribute("users", userRepository.findAll());
      return "redirect:/user/list";
    }
    logger.error("Error during saving user : {}", result.getFieldError());
    return "user/add";
  }

  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.debug("Getting request user/update/{id} for user with id:{}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    user.setPassword("");
    model.addAttribute("user", user);
    return "user/update";
  }

  @PostMapping("/update/{id}")
  public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result,
      Model model) {
    logger.debug("Posting request user/update/{id} for user with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating user : {}", result.getFieldError());
      return "user/update";
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));
    user.setId(id);
    userRepository.save(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/user/list";
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") Integer id, Model model) {
    logger.debug("Getting request user/delete/{id} for user with id:{}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/user/list";
  }
}
