package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ReversiController {
  @Autowired
  ReversiService rs;

  @RequestMapping("/")
  public String index(Model model) {
    rs.init();
    model.addAttribute("d", rs.getData());
    model.addAttribute("msg", 123);
    return "index";
  }
}