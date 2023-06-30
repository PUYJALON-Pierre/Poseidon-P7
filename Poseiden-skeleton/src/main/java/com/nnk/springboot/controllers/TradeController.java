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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.ITradeService;



/**
 * Controller class for Trade view in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Controller @RequestMapping("/trade")
public class TradeController {
  final static Logger logger = LogManager.getLogger(TradeController.class);

  @Autowired
  ITradeService iTradeService;

  /**
   * Get trade/list page model
   * 
   * @param model - Model
   * @return list (html template)
   */
  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request trade/list");
    model.addAttribute("trades", iTradeService.getAllTrades());
    return "trade/list";
  }

  /**
   * Get trade/add page model
   * 
   * @param bidList
   * @return add (html template)
   */
  @GetMapping("/add")
  public String addUser(Trade bid) {
    logger.debug("Getting request trade/add");
    return "trade/add";
  }

  /**
   * Creating a new Trade
   * 
   * @param trade - Trade
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/trade/list or trade/add
   * @throws Exception
   */
  @PostMapping("/validate")
  public String validate(@Valid Trade trade, BindingResult result, Model model) throws Exception {

    logger.debug("Posting request trade/validate for trade with id:{}", trade.getTradeId());
    if (!result.hasErrors()) {
      iTradeService.saveTrade(trade);
      model.addAttribute("trades", iTradeService.getAllTrades());
      return "redirect:/trade/list";
    }
    logger.error("Error during saving trade : {}", result.getFieldError());

    return "trade/add";
  }

  /**
   * Get trade/update page model
   * 
   * @param id - int
   * @param model - Model
   * @return trade/update
   */
  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    logger.debug("Getting request trade/update/{id} for trade with id:{}", id);
    Trade newTrade = iTradeService.getTradeById(id);
    model.addAttribute("trade", newTrade);
    return "trade/update";
  }

  /**
   * Post request for updating a trade by his id
   * 
   * @param id - int
   * @param trade - Trade
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/trade/list or trade/update
   * @throws Exception
   */
  @PostMapping("/update/{id}")
  public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
      BindingResult result, Model model) throws Exception {

    logger.debug("Posting request trade/update/{id} for trade with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating trade : {}", result.getFieldError());
      return "trade/update";
    }
    trade.setTradeId(id);
    iTradeService.updateTrade(trade);
    model.addAttribute("trades", iTradeService.getAllTrades());
    return "redirect:/trade/list";
  }

  /**
   * Delete request for deleting a Trade by his id
   * 
   * @param id - int
   * @param model - Model
   * @return redirect:/trade/list
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteTrade(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request trade/delete/{id} for trade with id:{}", id);
    Trade newTrade = iTradeService.getTradeById(id);
    iTradeService.deleteTrade(newTrade);
    model.addAttribute("trades", iTradeService.getAllTrades());

    return "redirect:/trade/list";
  }
}
