package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class ZasekiGouketuController {
	@RequestMapping(path = "/zasekiyoyakugou", method = RequestMethod.GET)
	public String zasekiget() {
		return "zasekigouketu";
	}

	@RequestMapping(path = "/zasekiyoyakugou", method = RequestMethod.POST)
	public String zasekipost(HttpSession session, Model model, String sousa, String zaseki1, String month1,
			String zaseki2, String month2, String delete, String sousinn, String ID, String PW) {

		//IDの初期化とsessionに追加
		ID = ID != null ? ID : (String) session.getAttribute("ID");
		session.setAttribute("ID", ID);
		boolean notdelete = false;

		//予約してある座席の初期化
		/*List<String[]> zaseki = session.getAttribute("yoyaku") != null ? (List<String[]>) session.getAttribute("yoyaku")
				: new ArrayList<>();*/
		//左：ID、右：番号と月
		List<Map<String, List<String[]>>> zaseki = session.getAttribute("yoyaku") != null
				? (List<Map<String, List<String[]>>>) session.getAttribute("yoyaku")
				: new ArrayList<>();

		//予約してある座席の初期化（権限付き）
		if (sousa != null && sousa.equals("すべて削除") && ID.equals("delete")) {
			zaseki.removeAll(zaseki);

			//ログアウトの操作
		} else if (sousa != null && sousa.equals("ログアウト")) {
			session.removeAttribute("ID");
		}
		//座席がすでに予約してあるか（ＨＴＭＬで比較する'tr'ならjs起動）
		String yoyakuzumi = "fal";

		model.addAttribute("notdelete", notdelete);
		model.addAttribute("yoyakuzumi", yoyakuzumi);
		session.setAttribute("yoyaku", zaseki);
		model.addAttribute("sousa", sousa);

		return "zasekigouketu";
	}
}