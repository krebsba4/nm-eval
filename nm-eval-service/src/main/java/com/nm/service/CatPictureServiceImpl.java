package com.nm.service;

import java.util.Collections;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nm.exceptions.CatStoppedWorkingException;
import com.nm.model.Cat;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service("catPictureService")
@AllArgsConstructor
public class CatPictureServiceImpl implements CatPictureService {

	private final String RANDOM_CAT_PICTURE_URL;
	private final WebClient webClient;

	@Override
	@Cacheable("cats")
	public Flux<Cat> getRandomCatPictures(int numberOfPictures) {
		return Flux.fromIterable(Collections.nCopies(numberOfPictures, 0)).parallel().runOn(Schedulers.boundedElastic())
				.flatMap(this::getRandomCat).sequential();
	}

	private Mono<Cat> getRandomCat(int randomInt) {
		return webClient
				.get()
				.uri(RANDOM_CAT_PICTURE_URL)
				.retrieve()
				.onStatus(HttpStatus::isError, response -> Mono.error(new CatStoppedWorkingException("Cat web service no work.")))
				.bodyToMono(Cat.class)
				.doOnError(error -> log.error("Error calling cat web service {}", error.getLocalizedMessage()));
	}
}
