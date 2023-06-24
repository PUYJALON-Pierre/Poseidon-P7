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

@Controller @RequestMapping("/trade")
public class TradeController {
  final static Logger logger = LogManager.getLogger(TradeController.class);

  @Autowired
  ITradeService iTradeService;

  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request trade/list");
    model.addAttribute("trades", iTradeService.getAllTrades());
    return "trade/list";
  }

  @GetMapping("/add")
  public String addUser(Trade bid) {
    logger.debug("Getting request trade/add");
    return "trade/add";
  }

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

  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    logger.debug("Getting request trade/update/{id} for trade with id:{}", id);
    Trade newTrade = iTradeService.getTradeById(id);
    model.addAttribute("trade", newTrade);
    return "trade/update";
  }

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

  @GetMapping("/delete/{id}")
  public String deleteTrade(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request trade/delete/{id} for trade with id:{}", id);
    Trade newTrade = iTradeService.getTradeById(id);
    iTradeService.deleteTrade(newTrade);
    model.addAttribute("trades", iTradeService.getAllTrades());

    return "redirect:/trade/list";
  }
}
