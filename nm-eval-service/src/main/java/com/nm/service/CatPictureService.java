package com.nm.service;

import java.util.List;

import com.nm.model.Cat;

public interface CatPictureService {

	List<Cat> getRandomCatPictures(int numberOfPictures);
}
