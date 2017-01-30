package com.adc.wins2.controller.pms;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/pms")
public class PmsController {

	@RequestMapping({"/", "{first}"})
	public  String singleDepth(
			@PathVariable(name = "first", required = false) String first,
			Model model) {
		first = StringUtils.defaultString(first, "index");
		return tripleMapping(first, null, null, model) ;
	}

	@RequestMapping("{first}/{second}")
	public  String doubleDepth(
			@PathVariable("first") String first,
			@PathVariable("second") String second,
			Model model) {
		return tripleMapping(first, second, null, model) ;
	}

	@RequestMapping("{first}/{second}/{third}")
	public  String tripleMapping(
			@PathVariable("first") String first,
			@PathVariable("second") String second,
			@PathVariable("third") String third,
			Model model) {
		return "/pms/" + Joiner.on("/").skipNulls().join(first, second, third) ;
	}
}
