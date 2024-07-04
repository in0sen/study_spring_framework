package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyEleventhController {

	@RequestMapping(path = "/elevenpageone", method = RequestMethod.GET)
	public String elevenget(Model model, HttpSession session) {

		//モデルにデータを格納
		model.addAttribute("modelData", "→モデルにしまったデータです←");

		//sessionにデータを格納
		session.setAttribute("sessionData", "→sessionにしまったデータです←");

		session.setAttribute("sessionData2", "練習問題①クリア");
		return "myeleventhone";
	}

	static String array[];

	@RequestMapping(path = "elevenpageone", method = RequestMethod.POST)
	public String elevenpost(Model model, HttpSession session, String ID, String PW) {
		array = new String[array.length+1];
		for (int index = array.length; index > 0; index++) {
			array[index] = index == 0 ? (String) session.getAttribute("userDataId") : array[index - 1];
		}
		if (ID != null && PW != null) {
			if ("elevenpw".equals(PW)) {
				session.setAttribute("userDataId", ID);
			}
		}
		return "myeleventhtwo";
	}

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/elevenpagetwo", method = RequestMethod.GET)
	public String elevenget2(Model model, HttpSession session) {

		//セッションからデータを取り出して適当な文字列をくっつける。

		String x = (String) session.getAttribute("sessionData");
		String y = (String) session.getAttribute("sessionData2");

		x = x + "だよだよ";
		y += ",練習問題②もクリア";

		//sessionにデータを格納
		session.setAttribute("sessionData", x);
		session.setAttribute("sessionData2", y);
		return "myeleventhtwo";
	}

	@RequestMapping(path = "/elevenpagethree", method = RequestMethod.GET)
	public String elevenget3(HttpSession session) {
		session.setAttribute("sessionData3", "練習問題③だよおお");
		return "myelevenththree";
	}

}