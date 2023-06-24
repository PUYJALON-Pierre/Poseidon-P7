package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

@Controller @RequestMapping("app")
public class LoginController {

  final static Logger logger = LogManager.getLogger(LoginController.class);

  @Autowired
  private UserRepository userRepository;

  @GetMapping("login")
  public ModelAndView login() {
    logger.debug("Getting request login");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login");
    return mav;
  }

  @GetMapping("secure/article-details")
  public ModelAndView getAllUserArticles() {
    logger.debug("Getting request secure/article-details");
    ModelAndView mav = new ModelAndView();
    mav.addObject("users", userRepository.findAll());
    mav.setViewName("user/list");
    return mav;
  }

  @GetMapping("error")
  public ModelAndView error() {
    logger.debug("Getting request error");
    ModelAndView mav = new ModelAndView();
    String errorMessage = "You are not authorized for the requested data.";
    mav.addObject("errorMsg", errorMessage);
    mav.setViewName("403");
    return mav;
  }
}
