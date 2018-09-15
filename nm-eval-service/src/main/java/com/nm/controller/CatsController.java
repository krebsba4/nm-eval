package com.nm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nm.model.Cat;
import com.nm.service.CatPictureService;
import com.nm.service.CatPictureServiceImpl;

@RestController
@CrossOrigin
public class CatsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatPictureServiceImpl.class);

	@Autowired
	private CatPictureService catPictureService;

	@RequestMapping("/getCats")
	public List<Cat> getCatPictures(@RequestParam int numberOfPictures) {
		LOGGER.debug(String.format("Recieved call to get [%s] cats.", numberOfPictures));
		try {
			return catPictureService.getRandomCatPictures(numberOfPictures);
		} catch (RuntimeException re) {
			LOGGER.error("Caught exception on getCatPicture endpoint.", re);
		}
		return new ArrayList<>();
	}
}
