package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.boot.SpringApplication;

@Controller
public class DemoController {
  @RequestMapping("/")
  public String index() {
    // model.addAttribute("msg", 111);
    return "index";
  }
}