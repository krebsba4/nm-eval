package com.nm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nm.model.Cat;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.test.StepVerifier;

public class CatPictureServiceImplTest {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static MockWebServer mockWebServer;
	private static String ENDPOINT;
	private WebClient webClient;

	private CatPictureService catPictureService;

	@BeforeEach
	public void beforeEach() throws IOException {
		mockWebServer = new MockWebServer();
		ENDPOINT = mockWebServer.url("/").toString();
		webClient = WebClient.create(ENDPOINT);

		catPictureService = new CatPictureServiceImpl("/", webClient);
	}

	@AfterEach
	public void after() throws IOException {
		mockWebServer.shutdown();
	}

	@Test
	public void getRandomCatPictures_1() throws JsonProcessingException {
		Cat fluffyTestCat = Cat.builder().file("testFileName").build();

		mockWebServer.enqueue(
				new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
						.setBody(objectMapper.writeValueAsString(fluffyTestCat)));

		assertEquals(fluffyTestCat.getFile(), catPictureService.getRandomCatPictures(1).blockFirst().getFile());
		assertNotNull(fluffyTestCat.getId());

	}

	@Test
	public void getRandomCatPictures_many() throws JsonProcessingException {
		Cat fluffy = Cat.builder().file("testFileName").build();
		Cat boomer = Cat.builder().file("boomcatFile").build();
		Cat leeroy = Cat.builder().file("leeroyFile").build();

		mockWebServer.enqueue(
				new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
						.setBody(objectMapper.writeValueAsString(fluffy)));
		mockWebServer.enqueue(
				new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
						.setBody(objectMapper.writeValueAsString(boomer)));
		mockWebServer.enqueue(
				new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
						.setBody(objectMapper.writeValueAsString(leeroy)));

		StepVerifier.create(catPictureService.getRandomCatPictures(3)).assertNext(aCat -> {
			catEquals(aCat);
		}).assertNext(aCat -> {
			catEquals(aCat);
		}).assertNext(aCat -> {
			catEquals(aCat);
		}).expectComplete().verify();
	}

	private void catEquals(Cat aCat) {
		assertTrue(aCat.getFile().equals("testFileName") || aCat.getFile().equals("boomcatFile")
				|| aCat.getFile().equals("leeroyFile"));

	}

	@Test
	public void getRandomCatPictures_exception() throws JsonProcessingException, InterruptedException {
		mockWebServer.enqueue(new MockResponse().setResponseCode(500));
		StepVerifier.create(catPictureService.getRandomCatPictures(3)).expectError().verify();
	}
}
