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

import com.nnk.springboot.service.IBidListService;

@Controller @RequestMapping("/bidList")
public class BidListController {

  final static Logger logger = LogManager.getLogger(BidListController.class);

  @Autowired
  IBidListService iBidListService;

  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request bidList/list");
    model.addAttribute("bidLists", iBidListService.getAllBidLists());

    return "bidList/list";
  }

  @GetMapping("/add")
  public String addBidForm(BidList bid) {
    logger.debug("Getting request bidList/add");
    return "bidList/add";
  }

  @PostMapping("/validate")
  public String validate(@Valid BidList bid, BindingResult result, Model model) throws Exception {
    logger.debug("Posting request bidList/validate for bid with id:{}", bid.getBidListId());
    if (!result.hasErrors()) {
      iBidListService.saveBidList(bid);
      model.addAttribute("bidLists", iBidListService.getAllBidLists());
      return "redirect:/bidList/list";
    }
    logger.error("Error during saving bid : {}", result.getFieldError());
    return "bidList/add";
  }

  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request bidList/update/{id} for bid with id:{}", id);
    BidList newBidList = iBidListService.getBidListByBidListId(id);
    model.addAttribute("bidList", newBidList);

    return "bidList/update";

  }

  @PostMapping("/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
      BindingResult result, Model model) throws Exception {
    logger.debug("Posting request bidList/update/{id} for bid with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating bid : {}", result.getFieldError());
      return "bidList/update";
    }
    bidList.setBidListId(id);
    iBidListService.updateBidList(bidList);
    model.addAttribute("bidLists", iBidListService.getAllBidLists());

    return "redirect:/bidList/list";

  }

  @GetMapping("/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) throws Exception {
    logger.debug("Getting request bidList/delete/{id} for bid with id:{}", id);
    BidList newBidList = iBidListService.getBidListByBidListId(id);
    iBidListService.deleteBidList(newBidList);
    model.addAttribute("bidLists", iBidListService.getAllBidLists());

    return "redirect:/bidList/list";

  }
}
