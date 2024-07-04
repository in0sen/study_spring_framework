package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFourthPraController {

	@RequestMapping(path="/fourthprapra" ,method =RequestMethod.GET)
	public String fourthgetpra() {
		return "myfourthpra";
	}
	
	@RequestMapping(path="fourthprapra",method=RequestMethod.POST)
	public String fourhpostpra(String dekita,Model model) {
		model.addAttribute("dekita",dekita);
		return "myfourthpra";
	}
}