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
public class MyDbFourthSoloController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/fourthsolo", method = RequestMethod.GET)
	public String dbfg(Model model) {

		//検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT compname,compemp,compgyoshu FROM fourthsolotable");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfourthsolo";
	}

	//練習問題②用
	@RequestMapping(path = "/fourthsoloIns", method = RequestMethod.POST)
	public String dbfpi(Model model, String compname, String compemp, String compgyoshu) {

		//データ更新
		jdbcTemplate.update("INSERT INTO fourthsolotable VALUES(?,?,?)", compname, compemp, compgyoshu);

		return "redirect:/fourthsolo";
	}

	//練習問題③用
	@RequestMapping(path = "/fourthsoloUpd", method = RequestMethod.POST)
	public String dbfpu(Model model, String compname, String compemp, String compgyoshu) {

		//データ更新
		jdbcTemplate.update("UPDATE fourthsolotable SET compemp = ? , compgyoshu = ? WHERE compname = ?", compemp,
				compgyoshu, compname);//compname,compemp,compgyoshu

		return "redirect:/fourthsolo";
	}

	//練習問題④用
	@RequestMapping(path = "/fourthsoloDel", method = RequestMethod.POST)
	public String dbfpd(Model model, String compname) {
		//compname, compemp, compgyoshu

		//データ更新
		jdbcTemplate.update("DELETE FROM fourthsolotable WHERE compname = ?", compname);

		return "redirect:/fourthsolo";
	}

	//練習問題⑤用
	@RequestMapping(path = "/fourthsoloSer", method = RequestMethod.POST)
	public String dbfps(Model model, String compname ,String compgyoshu) {

		//データ検索処理
		List<Map<String, Object>> kensakukekka = jdbcTemplate
				.queryForList("SELECT compname,compemp,compgyoshu FROM fourthsolotable WHERE compname like ? and compgyoshu like ?",
						"%" + compname + "%","%" + compgyoshu + "%");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbfourthsolo";
	}

}