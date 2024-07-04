package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class ZasekiyoyakuController {
	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.GET)
	public String zasekiget() {
		return "zasekiyoyaku";
	}

	@RequestMapping(path = "/zasekiyoyaku", method = RequestMethod.POST)
	public String zasekipost(HttpSession session, Model model, String sousa, String zaseki1, String month1,
			String zaseki2, String month2, String delete, String sousinn, String ID, String PW) {

		//IDの初期化とsessionに追加
		ID = ID != null ? ID : (String) session.getAttribute("ID");
		session.setAttribute("ID", ID);
		boolean notdelete = false;

		//予約してある座席の初期化
		List<String[]> zaseki = session.getAttribute("yoyaku") != null ? (List<String[]>) session.getAttribute("yoyaku")
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

		//予約 OR まとめて予約の送信を押したときのみ
		if (sousinn != null && sousinn.equals("送信")) {

			//まとめて予約のときに番号が被ってないか比較する
			if (zaseki1 != null && zaseki2 != null && zaseki1.equals(zaseki2)) {
				yoyakuzumi = "tr";
				zaseki2 = null;
			}

			for (String[] tuika : zaseki) {
				//すでに追加してあるかの比較
				if (zaseki1 != null && tuika[0].equals(zaseki1)) {
					yoyakuzumi = "tr";
					zaseki1 = null;
				}
				if (zaseki2 != null && tuika[0].equals(zaseki2)) {
					yoyakuzumi = "tr";
					zaseki2 = null;
				}

			}
			//座席予約するString配列
			String[] za1 = { zaseki1, month1, ID };
			String[] za2 = { zaseki2, month2, ID };

			//番号、月どちらかが入力されていなければ追加しない
			if (za1[0] != null && za1[1] != null) {
				zaseki.add(za1);
			}

			if (za2[0] != null && za2[1] != null) {
				zaseki.add(za2);
			}

			//まとめて予約の追加送信を押したとき
		} else if (sousinn != null && sousinn.equals("追加送信")) {

			//番号１と２、月１が入力されているか
			if (zaseki1 != null && zaseki2 != null && month1 != null) {

				//座席予約するための追加用座席リストの作成
				List<String[]> TuikaZaseki = new ArrayList<>();

				//座席番号１～座席番号２まで回す
				br: for (int a = Integer.parseInt(zaseki1); a <= Integer.parseInt(zaseki2); a++) {

					for (int b = 0; b < zaseki.size(); b++) {

						//すでに追加してある場所があったらfor文の終了
						if ((a + "").equals(zaseki.get(b)[0])) {
							yoyakuzumi = "tr";
							break br;
						}
					}

					//追加用座席リストに追加
					String[] za = { a + "", month1, ID };
					TuikaZaseki.add(za);
				}

				//途中でfor文が終了した場合追加しない
				if (!(yoyakuzumi.equals("tr"))) {
					for (String[] tuika : TuikaZaseki) {
						zaseki.add(tuika);
					}
				}
			}
		}

		if (delete != null) {

			//座席予約リストの中に取消番号があった場合その配列の削除＆for分終了
			for (int dl = 0; dl < zaseki.size(); dl++) {
				notdelete = true;
				if (zaseki.get(dl)[0].equals(delete) && (zaseki.get(dl)[2].equals((String) session.getAttribute("ID"))
						|| "delete".equals((String) session.getAttribute("ID")))) {
					zaseki.remove(dl);
					notdelete = false;
					break;
				}
			}
		}
		model.addAttribute("notdelete", notdelete);
		model.addAttribute("yoyakuzumi", yoyakuzumi);
		session.setAttribute("yoyaku", zaseki);
		model.addAttribute("sousa", sousa);

		return "zasekiyoyaku";
	}
}
/*
//追加した座席の中に番号もしくは月に空白が入力されていた場合削除
//多分必要ない
for (int a = zaseki.size() - 1; a >= 0; a--) {
	if (zaseki.get(a)[0].equals("") || zaseki.get(a)[1].equals("")) {
		zaseki.remove(a);
	}
}

//追加仕様なかったら必要
//予約数が10を超えた場合最初の11以降を消す
//多分必要ない
if (zaseki.size() > 10) {
	zaseki = zaseki.subList(0, 10);
}
*/