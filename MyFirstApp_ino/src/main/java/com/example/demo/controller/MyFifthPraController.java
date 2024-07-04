package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyFifthPraController {
	@RequestMapping(path = "/fifthpra", method = RequestMethod.GET)
	public String fifthpraget(Model model) {
		model.addAttribute("modelpra", "さんもんめ");
		return "myfifthpra";
	}
	@RequestMapping(path ="/fifthpra" , method=RequestMethod.POST)
	public String fifthprapost(String likefood,Model model) {
		System.out.println(likefood);
		if(likefood.equals("お寿司")) {
			likefood="私もお寿司が好きです";
		}
		model.addAttribute("modelpra",likefood);
		return "myfifthpra";
	}

}