package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

/**
 * Controller class for Login view in Trading App UI (Poseidon inc)
 *
 */
@Controller @RequestMapping("app")
public class LoginController {

  final static Logger logger = LogManager.getLogger(LoginController.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * Get Login page model
   *
   * @param model - Model
   * @return login
   */
  @GetMapping("login")
  public ModelAndView login() {
    logger.debug("Getting request login");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login");
    return mav;
  }

  /**
   * Get user/list page model
   *
   * @param model - Model
   * @return list (html template)
   */
  @GetMapping("secure/article-details")
  public ModelAndView getAllUserArticles() {
    logger.debug("Getting request secure/article-details");
    ModelAndView mav = new ModelAndView();
    mav.addObject("users", userRepository.findAll());
    mav.setViewName("user/list");
    return mav;
  }

  /**
   * Get error 403 page model
   *
   * @param model - Model
   * @return 403 (html template)
   */
  @GetMapping("error")
  public ModelAndView error() {
    logger.debug("Getting request error");
    ModelAndView mav = new ModelAndView();
    String errorMessage = "You are not authorized for the requested data.";
    mav.addObject("errorMsg", errorMessage);
    mav.setViewName("../static/error/403");
    return mav;
  }
}
