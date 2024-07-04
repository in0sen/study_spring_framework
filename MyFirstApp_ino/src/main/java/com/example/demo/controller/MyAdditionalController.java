package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAdditionalController {

	@RequestMapping(path = "/additional", method = RequestMethod.GET)
	public String addget() {
		return "myadditional";
	}

	@RequestMapping(path = "/additional", method = RequestMethod.POST)
	public String addpost(Model model, String q1, String q2, String q3, String q4) {
		String a = q1 + q2 + q3 + q4;
		switch (a) {
		case "istj":
			model.addAttribute("answer1", a.toUpperCase() + "(「義務遂行者」)");
			model.addAttribute("answer2", "実用的で真面目、事実に基づいて行動する。組織と責任を重んじる。");
			break;
		case "isfj":
			model.addAttribute("answer1", a.toUpperCase() + "(「保護者」)");
			model.addAttribute("answer2", "暖かく、寛大、忠実。伝統と安定性を大切にする。");
			break;
		case "infj":
			model.addAttribute("answer1", a.toUpperCase() + "(「カウンセラー」)");
			model.addAttribute("answer2", "洞察力があり、直感的。理想主義的で、他人の成長を助けることに情熱を持つ。");
			break;
		case "intj":
			model.addAttribute("answer1", a.toUpperCase() + "(「戦略家」)");
			model.addAttribute("answer2", "独立心が強く、創造的。複雑な問題解決に優れている。");
			break;
		case "istp":
			model.addAttribute("answer1", a.toUpperCase() + "(「職人」)");
			model.addAttribute("answer2", "現実的で柔軟、効率的。危機管理能力が高い。");
			break;
		case "isfp":
			model.addAttribute("answer1", a.toUpperCase() + "(「アーティスト」)");
			model.addAttribute("answer2", "穏やかで忍耐強い。現在を生きることを楽しむ。");
			break;
		case "infp":
			model.addAttribute("answer1", a.toUpperCase() + "(「仲介者」)");
			model.addAttribute("answer2", "内省的で理想主義的。創造的で情熱的。");
			break;
		case "intp":
			model.addAttribute("answer1", a.toUpperCase() + "(「論理学者」)");
			model.addAttribute("answer2", "非常に知的で理論的。好奇心旺盛で独創的。");
			break;
		case "estp":
			model.addAttribute("answer1", a.toUpperCase() + "(「冒険家」)");
			model.addAttribute("answer2", "行動的でエネルギッシュ。現実的で解決志向。");
			break;
		case "esfp":
			model.addAttribute("answer1", a.toUpperCase() + "(「パフォーマー」)");
			model.addAttribute("answer2", "社交的で活発。楽しいことを見つけ、共有するのが得意。");
			break;
		case "enfp":
			model.addAttribute("answer1", a.toUpperCase() + "(「活動家」)");
			model.addAttribute("answer2", "熱意があり、創造的。可能性を見つけて刺激する。");
			break;
		case "entp":
			model.addAttribute("answer1", a.toUpperCase() + "(「発明家」)");
			model.addAttribute("answer2", "知的で好奇心が強い。新しいことに挑戦するのが好き。");
			break;
		case "estj":
			model.addAttribute("answer1", a.toUpperCase() + "(「実行者」)");
			model.addAttribute("answer2", "組織的で実践的。リーダーシップがあり、物事を成し遂げる。");
			break;
		case "esfj":
			model.addAttribute("answer1", a.toUpperCase() + "(「提供者」)");
			model.addAttribute("answer2", "協力的で調和を重んじる。他人のニーズに敏感。");
			break;
		case "enfj":
			model.addAttribute("answer1", a.toUpperCase() + "(「教師」)");
			model.addAttribute("answer2", "社交的で、カリスマ的。他人を奮い立たせ、導く。");
			break;
		case "entj":
			model.addAttribute("answer1", a.toUpperCase() + "(「指導者」)");
			model.addAttribute("answer2", "決断力があり、自信がある。挑戦を楽しみ、目標を達成する。");
			break;
		default:
			model.addAttribute("answer1", "すべての質問に答えてください");
			break;
		}
		return "myadditional";
	}
}
