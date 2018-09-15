package com.nm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nm.model.Cat;

@Service("catPictureService")
public class CatPictureServiceImpl implements CatPictureService {

	private final String RANDOM_CAT_PICTURE_URL = "https://aws.random.cat/meow";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Cacheable("cats")
	public List<Cat> getRandomCatPictures(int numberOfPictures) {
		ArrayList<Cat> catPictureList = new ArrayList<>();
		for (int i = 0; i < numberOfPictures; i++) {
			catPictureList.add(restTemplate.getForObject(RANDOM_CAT_PICTURE_URL, Cat.class));
		}
		return catPictureList;
	}
}
