package br.com.luizalabs.quakelogparser.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

	private String nome;
	
	private Long totalKills = 0l;
	
	private List<String> players = new ArrayList<>();
	
	private Map<String, Long> kills = new HashMap<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTotalKills() {
		return totalKills;
	}

	public void setTotalKills(Long totalKills) {
		this.totalKills = totalKills;
	}

	public Map<String, Long> getKills() {
		return kills;
	}

	public void setKills(Map<String, Long> kills) {
		this.kills = kills;
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

}
