package com.nm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class ThingsController {

	@RequestMapping("/getStringThings")
	public List<String> getStringThings() {
		ArrayList<String> thingStringList= new ArrayList<>();
		char [][] pairs = {{'a','z'},{'0','9'}};
		RandomStringGenerator stringGenorator = new RandomStringGenerator.Builder().withinRange(pairs).build();
		for(int i = 0; i < 50; i++) {
			thingStringList.add(stringGenorator.generate(10));
		}
		return thingStringList;
	}
}
