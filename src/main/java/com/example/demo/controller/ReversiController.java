package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ReversiController {
  static List<Integer> data = new ArrayList<Integer>();

  @Autowired
  ReversiService rs;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("msg", 123);
    return "index";
  }
}