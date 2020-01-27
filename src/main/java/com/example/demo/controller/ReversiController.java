package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ReversiController {
  static List<Integer> data = new ArrayList<Integer>();

  @RequestMapping("/")
  public String index(Model model) {
    for (int i = 0; i < 4; i++) {
      data.add(0);
    }
    model.addAttribute("data", data);
    model.addAttribute("msg", 123);
    return "index";
  }
}