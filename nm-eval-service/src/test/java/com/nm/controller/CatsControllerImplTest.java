package com.nm.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nm.model.Cat;
import com.nm.router.CatsRouter;
import com.nm.service.CatPictureServiceImpl;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CatsControllerImpl.class, CatsRouter.class })
public class CatsControllerImplTest {

	private WebTestClient webTestClient;

	@MockBean
	private CatPictureServiceImpl catPictureServiceMock;
	
	@Autowired
	private RouterFunction<ServerResponse> route;

	
	@BeforeEach
	public void before() {	
		webTestClient = WebTestClient.bindToRouterFunction(route).build();
	}

	@Test
	public void getCatPictures_happyPath() {
		Cat tinyCat = Cat.builder().file("anotherTestFile").build();
		Mockito.when(catPictureServiceMock.getRandomCatPictures(1)).thenReturn(Flux.just(tinyCat));

		webTestClient.get().uri("/getCats?numberOfPictures=1").exchange().expectStatus().isOk().expectBody().consumeWith(result -> {
			String stringResult = new String(result.getResponseBody());
			assertTrue(stringResult.contains("\"file\":\"anotherTestFile\""));
		});

		verify(catPictureServiceMock).getRandomCatPictures(1);
	}

	@Test
	public void getCatPictures_NumberFormatException() {
		webTestClient.get().uri("/getCats?numberOfPictures=one").exchange().expectStatus().isOk().expectBody().consumeWith(result -> {
			String stringResult = new String(result.getResponseBody());
			assertTrue(stringResult.contains("\"file\":\"https://i.kym-cdn.com/entries/icons/original/000/008/589/558.jpg\""));
		});
	}
	
	@Test
	public void getCatPictures_NoSuchElementException() {
		webTestClient.get().uri("/getCats?testParam=one").exchange().expectStatus().isOk().expectBody().consumeWith(result -> {
			String stringResult = new String(result.getResponseBody());
			assertTrue(stringResult.contains("\"file\":\"https://i.kym-cdn.com/entries/icons/original/000/008/589/558.jpg\""));
		});
	}
}
