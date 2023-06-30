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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.IRatingService;



/**
 * Controller class for Rating view in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Controller @RequestMapping("/rating")
public class RatingController {
  final static Logger logger = LogManager.getLogger(RatingController.class);

  @Autowired
  IRatingService iRatingService;

  /**
   * Get rating/list page model
   * 
   * @param model - Model
   * @return list (html template)
   */
  @GetMapping("/list")
  public String home(Model model) {

    logger.debug("Getting request rating/list");
    model.addAttribute("ratings", iRatingService.getAllRatings());
    return "rating/list";
  }

  /**
   * Get rating/add page model
   * 
   * @param rating - Rating
   * @return add (html template)
   */
  @GetMapping("/add")
  public String addRatingForm(Rating rating) {
    logger.debug("Getting request rating/add");
    return "rating/add";
  }

  /**
   * Creating a new Rating
   * 
   * @param rating - Rating
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/rating/list or rating/add
   * @throws Exception
   */
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

  /**
   * Get rating/update page model
   * 
   * @param id - int
   * @param model - Model
   * @return rating/update
   */
  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.debug("Getting request rating/update/{id} for rating with id:{}", id);
    Rating newRating = iRatingService.getRatingById(id);
    model.addAttribute("rating", newRating);
    return "rating/update";
  }

  /**
   * Post request for updating a Rating by his id
   * 
   * @param id - int
   * @param rating - Rating
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/rating/list or rating/update
   * @throws Exception
   */
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

  /**
   * Delete request for deleting a CurvePoint by his id
   * 
   * @param id - int
   * @param model - Model
   * @return redirect:/rating/list
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteRating(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request rating/delete/{id} for rating with id:{}", id);
    Rating newRating = iRatingService.getRatingById(id);
    iRatingService.deleteRating(newRating);
    model.addAttribute("ratings", iRatingService.getAllRatings());

    return "redirect:/rating/list";
  }
}
