package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class DemoController {
  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("msg", 111);
    return "index";
  }
}