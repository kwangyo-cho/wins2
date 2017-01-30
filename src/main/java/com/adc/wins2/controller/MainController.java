package com.adc.wins2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Slf4j
public class MainController {

	@RequestMapping({"/", "/index"})
	public  String main(Model model, Principal principal) {
		log.debug("멍멍");
		model.addAttribute("test", this);
		model.addAttribute("principal", principal);
		return "index";
	}

	@RequestMapping("/test")
	public  String test(Model model, Principal principal) {
		model.addAttribute("test", principal);
		model.addAttribute("principal", principal);
		return "test";
	}


}
