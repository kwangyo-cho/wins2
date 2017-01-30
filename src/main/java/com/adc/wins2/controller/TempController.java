package com.adc.wins2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/temp")
public class TempController {

	@RequestMapping("/test")
	public  String test(Model model, Principal principal) {
		model.addAttribute("test", principal);
		model.addAttribute("principal", principal);
		return "test";
	}

	@RequestMapping("/remove")
	public  String remove(HttpSession session, Model model, Authentication authentication) {
		model.addAttribute("authentication", authentication);
		session.invalidate();
		return "remove";
	}

}
