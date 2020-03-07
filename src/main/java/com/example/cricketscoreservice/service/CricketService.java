package com.example.cricketscoreservice.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.cricketscoreservice.dto.MatchDto;
import com.example.cricketscoreservice.dto.ResponseDto;

@Service
public class CricketService {

	public ResponseDto getMatchDetails(String unique_id) throws ResponseStatusException {

		String url = "https://cricapi.com/api/cricketScore?unique_id=" + unique_id;
		HttpHeaders headers = new HttpHeaders();
		headers.set("apiKey", "WmPJrX2s3KMyZVPFwlm1vxXLXKw1");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate restTempalte = new RestTemplate();
		ResponseEntity<MatchDto> responseEntity = restTempalte.exchange(url, HttpMethod.GET, entity, MatchDto.class);

		ResponseDto responseDto = getResponse(responseEntity);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			return responseDto;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No details Found");
		}
	}

	private ResponseDto getResponse(@SuppressWarnings("rawtypes") ResponseEntity response) {
		MatchDto matchDto = (MatchDto) response.getBody();

		String teamName = getWinningTeamByScore(matchDto.getScore());

		String winningTeam = teamName.replaceAll("[^a-zA-Z]", " ");

		String winnerTag = " (winner)";

		String team1 = matchDto.getTeam1();
		String team2 = matchDto.getTeam2();

		if (team1.trim().equals(winningTeam.trim())) {
			team1 = matchDto.getTeam1() + winnerTag;
		} else if (team2.trim().equals(winningTeam.trim())) {
			team2 = matchDto.getTeam2() + winnerTag;
		}

		Matcher m = Pattern.compile("^*\\d*/\\d*").matcher(teamName);

		String winningTeamScore = null;
		while (m.find()) {
			winningTeamScore = m.group(0);
		}

		ResponseDto responseDto = new ResponseDto();

		responseDto.setTeam1(team1);
		responseDto.setTeam2(team2);
		responseDto.setWinningTeamSScore(winningTeamScore);
		responseDto.setRoundRotation(getRotatedValue(winningTeamScore));

		return responseDto;

	}

	private String getRotatedValue(String winningTeamScore2) {
		Matcher m = Pattern.compile("^*\\d*/\\d*").matcher(winningTeamScore2);
		String winningTeamScore = null;
		while (m.find()) {
			winningTeamScore = m.group(0);
		}
		String[] str = winningTeamScore.split("/");
		String test = str[1] + str[0] + "/";
		return test;
	}

	private String getWinningTeamByScore(String score) {
		String[] replacedSpace = score.split("v");
		String winningTeamName = null;
		for (String teamName : replacedSpace) {
			if (teamName.contains("*")) {
				winningTeamName = teamName;
			}
		}

		return winningTeamName;
	}

}
