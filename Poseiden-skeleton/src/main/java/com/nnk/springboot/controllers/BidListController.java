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

/**
 * Controller class for BidList view in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Controller @RequestMapping("/bidList")
public class BidListController {

  final static Logger logger = LogManager.getLogger(BidListController.class);

  @Autowired
  IBidListService iBidListService;

  /**
   * Get bidList/list page model
   *
   * @param model - Model
   * @return list (html template)
   */
  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request bidList/list");
    model.addAttribute("bidLists", iBidListService.getAllBidLists());

    return "bidList/list";
  }

  /**
   * Get bidList/add page model
   * 
   * @param bidList - BidList
   * @return list (html template)
   */
  @GetMapping("/add")
  public String addBidForm(BidList bidList) {
    logger.debug("Getting request bidList/add");
    return "bidList/add";
  }

  /**
   * Creating a new BidList
   * 
   * @param bidList - BidList
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/bidList/list or bidList/add
   * @throws Exception
   */
  @PostMapping("/validate")
  public String validate(@Valid BidList bidList, BindingResult result, Model model)
      throws Exception {
    logger.debug("Posting request bidList/validate for bidList with id:{}", bidList.getBidListId());
    if (!result.hasErrors()) {
      iBidListService.saveBidList(bidList);
      model.addAttribute("bidLists", iBidListService.getAllBidLists());
      return "redirect:/bidList/list";
    }
    logger.error("Error during saving bidList : {}", result.getFieldError());
    return "bidList/add";
  }

  /**
   * Get bidList/update page model
   * 
   * @param id - int
   * @param model - Model
   * @return udpate (html template)
   * @throws Exception
   */
  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request bidList/update/{id} for bidList with id:{}", id);
    BidList newBidList = iBidListService.getBidListByBidListId(id);
    model.addAttribute("bidList", newBidList);

    return "bidList/update";

  }

  /**
   * Post request for updating a bidList by his id
   *
   * @param id _ int
   * @param bidList - BidList
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/bidList/list or bidList/update
   * @throws Exception
   */
  @PostMapping("/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
      BindingResult result, Model model) throws Exception {
    logger.debug("Posting request bidList/update/{id} for bidList with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating bidList : {}", result.getFieldError());
      return "bidList/update";
    }
    bidList.setBidListId(id);
    iBidListService.updateBidList(bidList);
    model.addAttribute("bidLists", iBidListService.getAllBidLists());

    return "redirect:/bidList/list";

  }

  /**
   * Delete request for deleting a bidList by his id
   * 
   * @param id - int
   * @param model - Model
   * @return redirect:/bidList/list
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) throws Exception {
    logger.debug("Getting request bidList/delete/{id} for bidList with id:{}", id);
    BidList newBidList = iBidListService.getBidListByBidListId(id);
    iBidListService.deleteBidList(newBidList);
    model.addAttribute("bidLists", iBidListService.getAllBidLists());

    return "redirect:/bidList/list";

  }
}
