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
public class MyDbFourthController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fourthpra", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT sikakuname, sikakurank FROM fourthtable ");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfourth";
	}

	@RequestMapping(path = "/foruthpraIns", method = RequestMethod.POST)
	public String foins(Model model, String cername, String cerrank) {

		jdbcTemplate.update("INSERT INTO fourthtable VALUES(?,?)", cername, cerrank);

		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT sikakuname, sikakurank FROM fourthtable ");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbfourth";
	}

	@RequestMapping(path = "/fourthpraUpd", method = RequestMethod.POST)
	public String foupd(Model model, String cername, String cerrank) {

		jdbcTemplate.update("UPDATE fourthtable SET sikakurank = ? WHERE sikakuname = ?", cerrank, cername);
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT sikakuname, sikakurank FROM fourthtable ");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbfourth";
	}

	@RequestMapping(path = "/fourthpraDel", method = RequestMethod.POST)
	public String fodel(Model model, String cername) {
		jdbcTemplate.update("DELETE FROM fourthtable WHERE sikakuname = ?", cername);
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT sikakuname, sikakurank FROM fourthtable ");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbfourth";
	}

}