package com.nm.controller;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.nm.model.Cat;
import com.nm.service.CatPictureService;

@RunWith(EasyMockRunner.class)
public class CatsControllerTest {

	@TestSubject
	private CatsController catsController = new CatsController();
	
	@Mock
	private CatPictureService catPictureServiceMock;
	
	@Test
	public void getCatPictures() {
		List<Cat> catsList = new ArrayList<>();
		Cat tinyCat = new Cat();
		tinyCat.setFile("anotherTestFile");
		catsList.add(tinyCat);
		
		expect(catPictureServiceMock.getRandomCatPictures(1)).andReturn(catsList).times(2);
		replay(catPictureServiceMock);
		
		assertEquals(1, catsController.getCatPictures(1).size());
		assertEquals("anotherTestFile", catsController.getCatPictures(1).get(0).getFile());
		verify(catPictureServiceMock);
	}
	
	@Test
	public void getCatPictures_RuntimeException() {
		List<Cat> catsList = new ArrayList<>();
		Cat tinyCat = new Cat();
		tinyCat.setFile("anotherTestFile");
		catsList.add(tinyCat);
		
		expect(catPictureServiceMock.getRandomCatPictures(1)).andThrow(new RuntimeException());
		replay(catPictureServiceMock);
		
		List<Cat> returnCatList = catsController.getCatPictures(1);
		assertNotNull(returnCatList);
		assertTrue(returnCatList.isEmpty());
		verify(catPictureServiceMock);
	}
}
