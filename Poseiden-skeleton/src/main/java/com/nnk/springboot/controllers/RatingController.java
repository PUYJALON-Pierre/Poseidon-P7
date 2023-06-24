package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.IRatingService;

@Controller @RequestMapping("/rating")
public class RatingController {
  final static Logger logger = LogManager.getLogger(RatingController.class);

  @Autowired
  IRatingService iRatingService;

  @GetMapping("/list")
  public String home(Model model) {

    logger.debug("Getting request rating/list");
    model.addAttribute("ratings", iRatingService.getAllRatings());
    return "rating/list";
  }

  @GetMapping("/add")
  public String addRatingForm(Rating rating) {
    logger.debug("Getting request rating/add");
    return "rating/add";
  }

  @PostMapping("/validate")
  public String validate(@Valid Rating rating, BindingResult result, Model model) throws Exception {
    logger.debug("Posting request rating/validate for rating with id:{}", rating.getId());
    if (!result.hasErrors()) {
      iRatingService.saveRating(rating);
      model.addAttribute("ratings", iRatingService.getAllRatings());
      return "redirect:/rating/list";
    }
    logger.error("Error during saving rating : {}", result.getFieldError());
    return "rating/add";
  }

  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.debug("Getting request rating/update/{id} for rating with id:{}", id);
    Rating newRating = iRatingService.getRatingById(id);
    model.addAttribute("rating", newRating);
    return "rating/update";
  }

  @PostMapping("/update/{id}")
  public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
      BindingResult result, Model model) throws Exception {
    logger.debug("Posting request rating/update/{id} for rating with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating rating : {}", result.getFieldError());
      return "rating/update";
    }
    rating.setId(id);
    iRatingService.updateRating(rating);
    model.addAttribute("ratings", iRatingService.getAllRatings());

    return "redirect:/rating/list";
  }

  @GetMapping("/delete/{id}")
  public String deleteRating(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request rating/delete/{id} for rating with id:{}", id);
    Rating newRating = iRatingService.getRatingById(id);
    iRatingService.deleteRating(newRating);
    model.addAttribute("ratings", iRatingService.getAllRatings());

    return "redirect:/rating/list";
  }
}
