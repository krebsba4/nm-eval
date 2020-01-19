package com.nm.service;

import com.nm.model.Cat;

import reactor.core.publisher.Flux;

public interface CatPictureService {

	Flux<Cat> getRandomCatPictures(int numberOfPictures);
}
