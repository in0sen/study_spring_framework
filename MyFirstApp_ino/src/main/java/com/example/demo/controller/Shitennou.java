package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Shitennou {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/classupd", method = RequestMethod.GET)
	public String clupdget() {
		return "shitennou";
	}

	@RequestMapping(path = "/classupd", method = RequestMethod.POST)
	public String clupdpost(Model model, String beforeclass, String afterclass) {
		if (!beforeclass.equals("") && !afterclass.equals("")) {
			boolean js = false;
			List<Map<String, Object>> value = jdbcTemplate.queryForList(
					"SELECT user_id, user_name, password, authority, class, student_no, school FROM m_user WHERE class = ?",
					beforeclass);
			List<Map<String, Object>> hantei = new ArrayList<>();
			String[] classlist = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "N" };
			if (!value.equals(hantei)
					&& (((afterclass.length() == 2) && Arrays.asList(classlist).contains(afterclass.substring(1))
							&& Integer.parseInt(afterclass.substring(0, 1)) >= 1
							&& Integer.parseInt(afterclass.substring(0, 1)) <= 4) || afterclass.equals("卒業生"))) {
				jdbcTemplate.update("UPDATE m_user SET class = ? WHERE class = ?", afterclass, beforeclass);
				model.addAttribute("value", value);
				model.addAttribute("afterclass", afterclass);
			} else {
				js = true;
			}
			model.addAttribute("js", js);
		}
		return "shitennou";
	}
}