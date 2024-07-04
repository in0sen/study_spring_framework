package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyDbSecondSouController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/secondsou", method = RequestMethod.GET)
	public String souget() {
		return "mydbsecondsou";
	}

	@RequestMapping(path = "secondsou", method = RequestMethod.POST)
	public String soupost(Model model, String gaiyo, String setumei) {
		if (gaiyo != null && setumei != null) {
			jdbcTemplate.update("INSERT INTO secondsoutable VALUES(?,?)", gaiyo, setumei);
		} else if (gaiyo != null) {
			List<Map<String, Object>> kensaku;
			kensaku = jdbcTemplate.queryForList("SELECT gaiyo,setumei FROM secondsoutable WHERE gaiyo=?", gaiyo);
			model.addAttribute("kensaku", kensaku);

		}
		return "mydbsecondsou";
	}
}