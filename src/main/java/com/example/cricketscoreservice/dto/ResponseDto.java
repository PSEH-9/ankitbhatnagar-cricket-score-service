package com.example.cricketscoreservice.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto implements Serializable
{

    @JsonProperty("Team-1")
    private String team1;
    @JsonProperty("Team-2")
    private String team2;
    @JsonProperty("Winning team's score")
    private String winningTeamSScore;
    @JsonProperty("Round rotation")
    private String roundRotation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -147308610888327564L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseDto() {
    }

    /**
     *
     * @param winningTeamSScore
     * @param team1
     * @param team2
     * @param roundRotation
     */
    public ResponseDto(String team1, String team2, String winningTeamSScore, String roundRotation) {
        super();
        this.team1 = team1;
        this.team2 = team2;
        this.winningTeamSScore = winningTeamSScore;
        this.roundRotation = roundRotation;
    }

    @JsonProperty("Team-1")
    public String getTeam1() {
        return team1;
    }

    @JsonProperty("Team-1")
    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    @JsonProperty("Team-2")
    public String getTeam2() {
        return team2;
    }

    @JsonProperty("Team-2")
    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    @JsonProperty("Winning team's score")
    public String getWinningTeamSScore() {
        return winningTeamSScore;
    }

    @JsonProperty("Winning team's score")
    public void setWinningTeamSScore(String winningTeamSScore) {
        this.winningTeamSScore = winningTeamSScore;
    }

    @JsonProperty("Round rotation")
    public String getRoundRotation() {
        return roundRotation;
    }

    @JsonProperty("Round rotation")
    public void setRoundRotation(String roundRotation) {
        this.roundRotation = roundRotation;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "ResponseDto [team1=" + team1 + ", team2=" + team2 + ", winningTeamSScore=" + winningTeamSScore
				+ ", roundRotation=" + roundRotation + ", additionalProperties=" + additionalProperties + "]";
	}

}