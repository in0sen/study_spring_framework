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
public class DomeyoyakuController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "domeitirann", method = RequestMethod.GET)
	public String itirannget(Model model) {
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT yoyakubi, yoyakuclass, yoyakuname, yoyakucoat FROM domeyoyaku;");
		model.addAttribute("kensakukekka", kensakukekka);
		return "domeyoyakuitirann";
	}

	@RequestMapping(path = "domedata", method = RequestMethod.GET)
	public String dataget() {
		return "domeyoyakudata";
	}

	@RequestMapping(path = "domedataIns", method = RequestMethod.POST)
	public String dataIns(Model model, String yoyakubi, String yoyakuclass, String yoyakuname, String yoyakucoat) {
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList(
				"SELECT yoyakubi, yoyakuclass, yoyakuname, yoyakucoat FROM domeyoyaku where yoyakubi = ? and yoyakucoat =?;",
				yoyakubi, yoyakucoat);
		boolean notins = false;
		if (kensakukekka.size() == 0 && yoyakucoat != null && (yoyakucoat.equals("バスケ") || yoyakucoat.equals("バレー"))) {
			jdbcTemplate.update("INSERT INTO domeyoyaku VALUES(?,?,?,?);", yoyakubi, yoyakuclass, yoyakuname,
					yoyakucoat);
		} else {
			notins = true;
		}
		model.addAttribute("notins", notins);
		return "domeyoyakudata";
	}

	@RequestMapping(path = "domedataUpd", method = RequestMethod.POST)
	public String dataUpd(String yoyakuname, String beforeday, String afterday) {
		jdbcTemplate.update("UPDATE domeyoyaku SET yoyakubi = ? WHERE yoyakubi = ? and yoyakuname = ?;", afterday,
				beforeday, yoyakuname);
		return "domeyoyakudata";
	}

	@RequestMapping(path = "domedataDel", method = RequestMethod.POST)
	public String dataDel(String yoyakubi, String yoyakuname, String yoyakucoat) {
		jdbcTemplate.update("DELETE FROM domeyoyaku WHERE yoyakubi=? and yoyakuname=? and yoyakucoat=?;",
				yoyakubi, yoyakuname, yoyakucoat);
		return "domeyoyakudata";
	}
}