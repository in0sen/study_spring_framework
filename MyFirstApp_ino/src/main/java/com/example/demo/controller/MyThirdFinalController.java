package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyThirdFinalController {
	@RequestMapping(path = "/thirdfinal", method = RequestMethod.GET)
	public String thirdfinal(Model model) {
		String sample = "出来た";
		model.addAttribute("thirdfinal", sample);
		return "mythirdfinal";
	}
}