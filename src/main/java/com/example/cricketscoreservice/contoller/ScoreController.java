package com.example.cricketscoreservice.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricketscoreservice.dto.ResponseDto;
import com.example.cricketscoreservice.exception.ResourceNotFoundException;
import com.example.cricketscoreservice.service.CricketService;

@RestController
@RequestMapping("/api/")
public class ScoreController {
	
	@Autowired
	private CricketService cricketService;
	
	@RequestMapping("/v1/cricket/score")
	public ResponseDto getMatchDetails(@RequestParam("unique_id") String uniqueId ) throws ResourceNotFoundException{
		
		return cricketService.getMatchDetails(uniqueId);
	}
}
