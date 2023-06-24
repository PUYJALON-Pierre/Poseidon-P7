package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  final static Logger logger = LogManager.getLogger(HomeController.class);

  @RequestMapping("/")
  public String home(Model model) {
    logger.debug("Getting request / ");
    return "home";
  }

  @RequestMapping("/admin/home")
  public String adminHome(Model model) {
    logger.debug("Getting request /admin/home");
    return "redirect:/bidList/list";
  }

}
