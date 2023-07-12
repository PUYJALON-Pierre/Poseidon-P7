package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for Home view in Trading App UI (Poseidon inc)
 *
 */
@Controller
public class HomeController {
  final static Logger logger = LogManager.getLogger(HomeController.class);

  /**
   * Get Home page model
   *
   * @param model - Model
   * @return home (html template)
   */
  @RequestMapping("/")
  public String home(Model model) {
    logger.debug("Getting request / ");
    return "home";
  }

  /**
   * Redirect to bidList/list page model
   *
   * @param model - Model
   * @return list (html template)
   */
  @RequestMapping("/admin/home")
  public String adminHome(Model model) {
    logger.debug("Getting request /admin/home");
    return "redirect:/bidList/list";
  }

}
