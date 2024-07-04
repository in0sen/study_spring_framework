package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLoginController {

	@RequestMapping(path = "/userLogin", method = RequestMethod.GET)
	public String userlogin() {
		return "userlogin";
	}

	@RequestMapping(path = "/userHome", method = RequestMethod.POST)
	public String userhome(Model model, String ID, String PASS, String text) {
		ID = ID != null ? ID : text != null ? text : "";
		PASS = PASS != null ? PASS : text != null ? text : "";
		text = text != null ? text : " ";
		if ("userlogin".equals(ID) && "userpass".equals(PASS)) {
			model.addAttribute("ID","ようこそ"+ID+"さん");
			return "userhome";
		} else if (text.equals(ID) && text.equals(PASS)) {
			model.addAttribute("text", text);
			return "userhome";
		} else if ("".equals(ID) && "".equals(PASS)) {
			return "userlogin";
		} else {
			model.addAttribute("false0","失敗");
			if (PASS.length() < 8) {
				model.addAttribute("shortpass","パスワードが短い");
				return "userlogin";
			} else if ("userpass".equals(PASS)) {
				model.addAttribute("loginmiss","ログインに失敗しました");
				return "userfalseID";
			} else {
				return "userlogin";
			}
		}
	}
}