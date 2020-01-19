package com.nm.controller;

import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.nm.model.Cat;

public interface CatsController {
	Publisher<Cat> getCatPictures(ServerRequest request);
}
