package com.nm.controller;

import java.util.NoSuchElementException;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.nm.exceptions.CatStoppedWorkingException;
import com.nm.model.Cat;
import com.nm.service.CatPictureService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component("catsController")
public class CatsControllerImpl implements CatsController {

	private static final String DEFAULT_CAT_URL = "https://i.kym-cdn.com/entries/icons/original/000/008/589/558.jpg";

	@Autowired
	private CatPictureService catPictureService;

	public Publisher<Cat> getCatPictures(ServerRequest request) {
		log.debug(String.format("Recieved call to get [%s] cats.", request));
		Integer howManyCats;

		try {
			howManyCats = Integer.valueOf(request.queryParam("numberOfPictures").get());
		} catch (NumberFormatException | NoSuchElementException e) {
			log.error(String.format("Input params not working."), e);
			return Flux.just(Cat.builder().file(DEFAULT_CAT_URL).build());
		}

		return catPictureService.getRandomCatPictures(howManyCats).onErrorReturn(CatStoppedWorkingException.class,
				Cat.builder().file(DEFAULT_CAT_URL).build());
	}

}
