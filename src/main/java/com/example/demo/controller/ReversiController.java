package com.example.demo.controller;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ReversiController {
  @Autowired
  ReversiService rs;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String top(Model model) {
    rs.start();
    model.addAttribute("d", rs.getData());
    model.addAttribute("msg", rs.getMessage());
    return "index";
  }

  @RequestMapping(value = "/update*", method = RequestMethod.GET)
  @ResponseBody
  public String update(int xAxis, int yAxis, Model model) {
    rs.putPiece(xAxis, yAxis);
    model.addAttribute("d", rs.getData());
    model.addAttribute("msg", rs.getMessage());
    return "index";
  }

  @RequestMapping(value = "/message", method = RequestMethod.GET)
  @ResponseBody
  public String getMessage() {
    return rs.getMessage();
  }
}
