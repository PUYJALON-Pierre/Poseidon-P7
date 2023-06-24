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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.ICurvePointService;

@Controller @RequestMapping("/curvePoint")
public class CurveController {

  @Autowired
  ICurvePointService iCurvePointService;

  final static Logger logger = LogManager.getLogger(CurveController.class);

  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request curvePoint/list");
    model.addAttribute("curvePoints", iCurvePointService.getCurvePoints());
    return "curvePoint/list";
  }

  @GetMapping("/add")
  public String addBidForm(CurvePoint bid) {
    logger.debug("Getting request curvePoint/add");
    return "curvePoint/add";
  }

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

  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.debug("Getting request curvePoint/update/{id} for curvePoint with id:{}", id);
    CurvePoint newCurvePoint = iCurvePointService.getCurvePointById(id);
    model.addAttribute("curvePoint", newCurvePoint);
    return "curvePoint/update";
  }

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

  @GetMapping("/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) throws Exception {
    logger.debug("Getting request curvePoint/delete/{id} for curvePoint with id:{}", id);
    CurvePoint newCurvePoint = iCurvePointService.getCurvePointById(id);
    iCurvePointService.deleteCurvePoint(newCurvePoint);
    model.addAttribute("curvePoints", iCurvePointService.getCurvePoints());

    return "redirect:/curvePoint/list";
  }
}
