package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MySecondController {

	@RequestMapping(path = "/secondpagepra", method = RequestMethod.GET)
	public String secondpra() {
		return "mysecond";
	}
	@RequestMapping(path = "/secondpagepra/chall", method = RequestMethod.GET)
	public String secondchall() {
		return "mysecondchall";
	}
	
}