package com.example.cricketscoreservice.contoller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.cricketscoreservice.dto.ResponseDto;
import com.example.cricketscoreservice.exception.ResourceNotFoundException;
import com.example.cricketscoreservice.service.CricketService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScoreControllerIntegrationTest {

	@Mock
	CricketService cricketServices;

	@InjectMocks
	ScoreController scoreController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getMatchDetailsSuccess() throws ResourceNotFoundException {
		ResponseDto responseModel = new ResponseDto("team1:Lahore Qalandars (winner)", "team2: Peshawar Zalmi", "116/6",
				"6116/");
		Mockito.when(cricketServices.getMatchDetails(Mockito.any(String.class))).thenReturn(responseModel);

		ResponseDto resultModel = scoreController.getMatchDetails("12345");

		Assert.assertNotNull(resultModel);

	}

}
