package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyNinthController {

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/ninthpage", method = RequestMethod.GET)
	public String ninthget(Model model) {

		String x = "問題①";
		model.addAttribute("mondai1",x);
		model.addAttribute("datadayo1", "A");
		model.addAttribute("datadayo2", "B");
		model.addAttribute("datadayo3", "C");

		List<String> list = new ArrayList<>();
		list.add("リストの中身1個目はぶどう");
		list.add("リストの中身2個目はゴリラ");
		list.add("リストの中身3個目はラッパ");
		model.addAttribute("resultList", list);

		List<String> rs2 = new ArrayList<>();
		rs2.add("字一色");
		rs2.add("四暗刻単騎");
		rs2.add("大四喜");
		model.addAttribute("rs2", rs2);
		
		List<String> pra2=new ArrayList<>();
		pra2.add("練習");
		pra2.add("問題");
		pra2.add("2問目");
		model.addAttribute("pra2",pra2);
		
		return "myninth";
	}

	//POSTメソッド
	@RequestMapping(path = "/ninthpage", method = RequestMethod.POST)
	public String ninthpost(Model model) {

		return "myninth";
	}
}