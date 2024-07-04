package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyThirdController {

	//チュートリアル
	//まずは「return」の上の行にslackのコードを追記してみよう。
	@RequestMapping(path = "/thirdtutorial", method = RequestMethod.GET)
	public String third(Model model) {

		String practice = "testだよ";
		String sample1="慣れた！";
		
		model.addAttribute("practice", practice);
		model.addAttribute("sample2",sample1);
		return "mythird";
	}

}