package com.nm.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nm.controller.CatsController;
import com.nm.model.Cat;

@Configuration
public class CatsRouter {

	@Autowired
	private CatsController catsController;
	
	@Bean
	public RouterFunction<ServerResponse> route() {
		return RouterFunctions.route(RequestPredicates.GET("/getCats"), request -> ServerResponse.ok()
				.body(catsController.getCatPictures(request), Cat.class));
	}
}
