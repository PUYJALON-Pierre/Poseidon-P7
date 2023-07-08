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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.ICurvePointService;



/**
 * Controller class for Curvepoint view in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Controller @RequestMapping("/curvePoint")
public class CurvePointController {

  @Autowired
  ICurvePointService iCurvePointService;

  final static Logger logger = LogManager.getLogger(CurvePointController.class);

  
  /**
   * Get curvePoint/list page model
   *
   * @param model - Model
   * @return list (html template)
   */
  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request curvePoint/list");
    model.addAttribute("curvePoints", iCurvePointService.getCurvePoints());
    return "curvePoint/list";
  }

  
 /**
  * Get curvePoint/add page model
  * 
  * @param curvePoint - CurvePoint
  * @return add (html template)
  */
  @GetMapping("/add")
  public String addBidForm(CurvePoint curvePoint) {
    logger.debug("Getting request curvePoint/add");
    return "curvePoint/add";
  }

  /**
   * Creating a new CurvePoint
   * 
   * @param curvePoint - CurvePoint
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/curvePoint/list or curvePoint/add
   * @throws Exception
   */
  @PostMapping("/validate")
  public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model)
      throws Exception {
    logger.debug("Posting request curvePoint/validate for curvePoint with id:{}",
        curvePoint.getId());
    if (!result.hasErrors()) {
      iCurvePointService.saveCurvePoint(curvePoint);
      model.addAttribute("curvePoints", iCurvePointService.getCurvePoints());
      return "redirect:/curvePoint/list";
    }
    logger.error("Error during saving curvePoint : {}", result.getFieldError());
    return "curvePoint/add";
  }

  
  /**
   * Get curvePoint/update page model
   * 
   * @param id - int
   * @param model - Model
   * @return curvePoint/update
   */
  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.debug("Getting request curvePoint/update/{id} for curvePoint with id:{}", id);
    CurvePoint newCurvePoint = iCurvePointService.getCurvePointById(id);
    model.addAttribute("curvePoint", newCurvePoint);
    return "curvePoint/update";
  }

  /**
   * Post request for updating a curvePoint by his id
   * 
   * @param id - int
   * @param curvePoint - CurvePoint
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/curvePoint/list or curvePoint/update
   * @throws Exception
   */
  @PostMapping("/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
      BindingResult result, Model model) throws Exception {
    logger.debug("Posting request curvePoint/update/{id} for curvePoint with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating curvePoint : {}", result.getFieldError());
      return "curvePoint/update";
    }
    curvePoint.setId(id);
    iCurvePointService.updateCurvePoint(curvePoint);
    model.addAttribute("curvePoints", iCurvePointService.getCurvePoints());

    return "redirect:/curvePoint/list";
  }

  /**
   * Delete request for deleting a curvePoint by his id
   * 
   * @param id - int
   * @param model - Model
   * @return redirect:/curvePoint/list
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) throws Exception {
    logger.debug("Getting request curvePoint/delete/{id} for curvePoint with id:{}", id);
    CurvePoint newCurvePoint = iCurvePointService.getCurvePointById(id);
    iCurvePointService.deleteCurvePoint(newCurvePoint);
    model.addAttribute("curvePoints", iCurvePointService.getCurvePoints());

    return "redirect:/curvePoint/list";
  }
}
