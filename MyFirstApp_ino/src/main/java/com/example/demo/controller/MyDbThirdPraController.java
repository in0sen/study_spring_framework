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
public class MyDbThirdPraController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//画面を表示するためのGETメソッド
	@RequestMapping(path = "/mydbthirdpra", method = RequestMethod.GET)
	public String dbsg2(Model model) {

		//検索処理(secondtable用)
		List<Map<String, Object>> kensakukekkaSecond;
		kensakukekkaSecond = jdbcTemplate.queryForList("SELECT itemname, itemprice FROM thirdtable;");

		//検索結果のリストをmodelに格納
		model.addAttribute("kensakupra", kensakukekkaSecond);

		return "mydbthirdpra";
	}

	/*****データ更新用******/
	@RequestMapping(path = "/mydbthirdpraUpd", method = RequestMethod.POST)
	public String dbspupd(Model model, String beforeprice, String afterprice) {

		// データ更新
		jdbcTemplate.update("UPDATE thirdtable SET itemprice = ? WHERE itemprice = ?;", afterprice, beforeprice);

		//データ登録後、全件検索
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT itemname, itemprice FROM thirdtable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbthirdpra";
	}

	/**************************************************************************/

	/*****データ削除用******/
	@RequestMapping(path = "/mydbthirdpraDel", method = RequestMethod.POST)
	public String dbspdel(Model model, String itemname) {

		// データ削除
		jdbcTemplate.update("DELETE FROM thirdtable where itemname = ?;", itemname);

		//データ登録後、全件検索
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT itemname, itemprice FROM thirdtable");
		//検索結果のリストをmodelに格納する。
		model.addAttribute("kensakupra", kensakukekka);

		return "mydbthirdpra";
	}

	/**************************************************************************/

	@RequestMapping(path = "/mydbthirdpraSer", method = RequestMethod.POST)
	public String dbspSer(Model model, String itemname) {
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT itemname,itemprice FROM thirdtable WHERE itemname = ?;",
				itemname);
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbthirdpra";
	}

	@RequestMapping(path = "/mydbthirdpraIns", method = RequestMethod.POST)
	public String dbspIns(Model model, String itemname, String itemprice) {
		jdbcTemplate.update("INSERT INTO thirdtable(itemname,itemprice) VALUES(?,?);", itemname, itemprice);
		jdbcTemplate.update("DELETE FROM thirdtable where itemname = '' OR itemprice = '';");
		List<Map<String, Object>> kensakukekka;
		kensakukekka = jdbcTemplate.queryForList("SELECT itemname,itemprice FROM thirdtable;");
		model.addAttribute("kensakupra", kensakukekka);
		return "mydbthirdpra";
	}
}