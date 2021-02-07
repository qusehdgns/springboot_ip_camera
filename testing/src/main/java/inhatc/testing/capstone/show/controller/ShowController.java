package inhatc.testing.capstone.show.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import inhatc.testing.capstone.show.dto.ShowDto;
import inhatc.testing.capstone.show.service.ShowService;

@Controller
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@RequestMapping("/show/select")
	public String home(HttpSession session) throws Exception {
		return "show/select";
	}
	
	@RequestMapping("/show/show_1")
	public String show_1(Model model, HttpSession session) throws Exception {
		List<ShowDto> list = showService.ShowList(session.getAttribute("id").toString());
		model.addAttribute("list", list);
		return "show/show_1";
	}
	
	@RequestMapping("/show/show_4")
	public String show_4(Model model, HttpSession session) throws Exception {
		List<ShowDto> list = showService.ShowList(session.getAttribute("id").toString());
		model.addAttribute("list", list);
		return "show/show_4";
	}
	
	@RequestMapping("/show/show_9")
	public String show_9(Model model, HttpSession session) throws Exception {
		List<ShowDto> list = showService.ShowList(session.getAttribute("id").toString());
		model.addAttribute("list", list);
		return "show/show_9";
	}
	
}
