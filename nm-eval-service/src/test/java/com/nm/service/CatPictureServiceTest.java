package com.nm.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.nm.model.Cat;

@RunWith(EasyMockRunner.class)
public class CatPictureServiceTest {


	@TestSubject
	private CatPictureService catPictureService = new CatPictureServiceImpl();
	
	@Mock
	private RestTemplate restTemplateMock;
	
	@Test
	public void getRandomCatPictures_1() {
		Cat fluffyTestCat = new Cat();
		fluffyTestCat.setFile("testFileName");
		expect(restTemplateMock.getForObject("https://aws.random.cat/meow", Cat.class)).andReturn(fluffyTestCat);
		replay(restTemplateMock);
		
		assertEquals(fluffyTestCat.getFile(), catPictureService.getRandomCatPictures(1).get(0).getFile());
		assertNotNull(fluffyTestCat.getId());
		verify(restTemplateMock);
	}
	
	@Test
	public void getRandomCatPictures_many() {
		for(int i = 0; i < 5; i++) {
			Cat fluffyTestCat = new Cat();
			fluffyTestCat.setFile("testFileName");
			expect(restTemplateMock.getForObject("https://aws.random.cat/meow", Cat.class)).andReturn(fluffyTestCat);	
		}
		replay(restTemplateMock);
		assertEquals(5, catPictureService.getRandomCatPictures(5).size());
		verify(restTemplateMock);
	}
	
	@Test(expected=RestClientException.class)
	public void getRandomCatPictures_RestClientException() {
		expect(restTemplateMock.getForObject("https://aws.random.cat/meow", Cat.class)).andThrow(new RestClientException("testException"));
		replay(restTemplateMock);
		catPictureService.getRandomCatPictures(1);
		verify(restTemplateMock);
	}
}
