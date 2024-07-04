package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class SheetReservController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//一覧、登録画面表示用
	@RequestMapping(path = "/sheetreserv", method = RequestMethod.GET)
	public String sheet1(HttpSession session, Model model) {

		//検索処理
		System.out.println();
		if (session.getAttribute("id") != null) {
			List<Map<String, Object>> kensakukekka = jdbcTemplate
					.queryForList("SELECT yoyakubango, yoyakubi, yoyakuname FROM sheetreserve");

			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);

			return "sheetInsSer";
		} else {
			return "redirect:/sheetLogin";
		}
	}

	//編集、削除画面表示用
	@RequestMapping(path = "/reservmodify", method = RequestMethod.GET)
	public String sheet11(HttpSession session) {

		/**returnの後ろのhtml名以外は変更の必要無し**/

		if (session.getAttribute("id") != null) {
			return "sheetUpdDel";
		} else {
			return "redirect:/sheetLogin";
		}
	}

	//ログイン画面表示用
	@RequestMapping(path = "/sheetLogin", method = RequestMethod.GET)
	public String sheet21(HttpSession session) {

		return "sheetLogin";
	}

	//マイページ画面表示用
	@RequestMapping(path = "/sheetmypage", method = RequestMethod.GET)
	public String sheet31(HttpSession session, Model model) {
		String rank;
		List<Map<String, Object>> yoyakurireki = jdbcTemplate.queryForList(
				"SELECT yoyakubango, yoyakubi FROM sheetreserve WHERE yoyakuname = ?",
				(String) session.getAttribute("id"));
		if (yoyakurireki.size() == 0) {
			rank = "ひよこユーザ";
		} else if (yoyakurireki.size() <= 3) {
			rank = "初心者ユーザ";
		} else if (yoyakurireki.size() <= 7) {
			rank = "ベテランユーザ";
		} else if (yoyakurireki.size() <= 11) {
			rank = "大御所ユーザ";
		} else {
			rank = "石油王";
		}
		model.addAttribute("yoyakurireki", yoyakurireki);
		model.addAttribute("count", yoyakurireki.size());
		model.addAttribute("rank", rank);
		return "sheetmypage";
	}

	//ランキング画面表示用
	@RequestMapping(path = "/sheetranking", method = RequestMethod.GET)
	public String sheet41(Model model) {
		List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList(
				"SELECT yoyakuname,count(yoyakuname) AS cou FROM sheetreserve GROUP BY yoyakuname ORDER BY cou DESC");
		Map<String, Integer> rankingnumber = new HashMap<>();
		for (Map<String, Object> value : kensakukekka) {
			int count = Integer.parseInt(String.valueOf(value.get("cou")));

			if (!rankingnumber.containsKey("iti")) {
				rankingnumber.put("iti", count);

			} else if (!rankingnumber.containsKey("nii") && rankingnumber.get("iti") != count) {
				rankingnumber.put("nii", count);

			} else if (!rankingnumber.containsKey("sani") && rankingnumber.get("iti") != count
					&& rankingnumber.get("nii") != count) {
				rankingnumber.put("sani", count);
				break;
			}
		}
		List<Map<String, String>> iti = new ArrayList<>();
		List<Map<String, String>> nii = new ArrayList<>();
		List<Map<String, String>> sani = new ArrayList<>();
		int a = 0;
		for (Map<String, Object> data : kensakukekka) {
			Map<String, String> name = new HashMap<>();
			if (rankingnumber.containsKey("iti") && Integer.parseInt(String.valueOf(data.get("cou"))) == Integer
					.parseInt(String.valueOf(rankingnumber.get("iti")))) {
				name.put("name", String.valueOf(kensakukekka.get(a++).get("yoyakuname")));
				iti.add(name);
				model.addAttribute("iti", iti);
			} else if (rankingnumber.containsKey("nii")
					&& Integer.parseInt(String.valueOf(data.get("cou"))) == Integer
							.parseInt(String.valueOf(rankingnumber.get("nii")))) {
				name.put("name", String.valueOf(kensakukekka.get(a++).get("yoyakuname")));
				nii.add(name);
				model.addAttribute("nii", nii);

			} else if (rankingnumber.containsKey("sani")
					&& Integer.parseInt(String.valueOf(data.get("cou"))) == Integer
							.parseInt(String.valueOf(rankingnumber.get("sani")))) {
				name.put("name", String.valueOf(kensakukekka.get(a++).get("yoyakuname")));
				sani.add(name);
				model.addAttribute("sani", sani);

			}
		}
		model.addAttribute("rn", rankingnumber);
		return "sheetranking";
	}

	//登録用
	@RequestMapping(path = "/sheetIns", method = RequestMethod.POST)
	public String sheet2(HttpSession session, String number, String day, String name) {

		//登録処理
		if (!number.equals("") && !day.equals("") && !name.equals("")) {
			jdbcTemplate.update("INSERT INTO sheetreserve VALUES(?,?,?)", number, day, name);
		}
		return "redirect:/sheetreserv";
	}

	//検索用
	@RequestMapping(path = "/sheetSer", method = RequestMethod.POST)
	public String sheet3(Model model, String name) {
		if (!name.equals("")) {
			List<Map<String, Object>> kensakukekka = jdbcTemplate.queryForList(
					"SELECT yoyakubango, yoyakubi, yoyakuname FROM sheetreserve WHERE yoyakuname like ?",
					"%" + name + "%");

			//検索結果のリストをmodelに格納
			model.addAttribute("kensakupra", kensakukekka);
		}
		return "sheetInsSer";
	}

	//更新用(ヒント無し)
	@RequestMapping(path = "/sheetUpd", method = RequestMethod.POST)
	public String sheet12(String number, String name) {
		// 更新処理
		if (!number.equals("") && !name.equals("")) {
			//yoyakubango, yoyakubi, yoyakuname
			jdbcTemplate.update("UPDATE sheetreserve SET yoyakuname = ? WHERE yoyakubango = ?", name, number);
		}
		return "redirect:/sheetreserv";
	}

	//削除用(ヒント無し)
	@RequestMapping(path = "/sheetDel", method = RequestMethod.POST)
	public String sheet13(String number) {

		//削除処理
		if (!number.equals("")) {
			jdbcTemplate.update("DELETE FROM sheetreserve WHERE yoyakubango = ?", number);
		}
		return "redirect:/sheetreserv";
	}

	//ログイン用
	@RequestMapping(path = "/sheetLogin", method = RequestMethod.POST)
	public String sheet22(HttpSession session, String id, String pass) {
		if (!id.equals("") && !pass.equals("")) {
			List<Map<String, Object>> login = jdbcTemplate
					.queryForList("SELECT userid,userpass FROM sheetuser WHERE userid = ? AND userpass = ?;", id, pass);
			if (login.size() >= 1) {
				session.setAttribute("id", (String) login.get(0).get("userid"));
				return "redirect:/sheetreserv";
			}
		}
		return "sheetLogin";
	}

	@RequestMapping(path = "/sheetLoginIns", method = RequestMethod.POST)
	public String sheet23(Model model, String id, String pass) {
		if (!id.equals("") && !pass.equals("")) {
			boolean logtf = false;
			List<Map<String, Object>> login = jdbcTemplate
					.queryForList("SELECT userid,userpass FROM sheetuser WHERE userid = ? AND userpass = ?", id, pass);
			if (login.size() == 0) {
				jdbcTemplate.update("INSERT INTO sheetuser VALUES(?,?)", id, pass);
			} else {
				logtf = true;
			}
			model.addAttribute("logtf", logtf);
		}
		return "redirect:/sheetLogin";
	}
}