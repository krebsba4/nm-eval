package com.nm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThingsController {

	@RequestMapping("/getThings")
	public String getThings() {
		return "quick test endpoint";
	}
}
