package br.com.luizalabs.quakelogparser.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.luizalabs.quakelogparser.model.Game;

public class LogParserServiceTest {

	private LogParserService logParserService = new LogParserService();
	
	@Test
	public void parseLog() {
		
		List<Game> games = logParserService.parseLog(null, null);
		
		Assert.assertNotNull(games);
		Assert.assertTrue(!games.isEmpty());
	}
	
	@Test
	public void parseLogByGame() {
	
		Long gameNumber = 1l;
		
		Game game = logParserService.parseLogByGame(gameNumber);
		
		Assert.assertNotNull(game);
		Assert.assertTrue(game.getNome().equals("game_" + gameNumber));
	}
	
	@Test
	public void parseLogByGameInexistente() {
	
		Long gameNumber = 21l;
		
		Game game = logParserService.parseLogByGame(gameNumber);
		
		Assert.assertNull(game);
	}
	
	@Test
	public void parseLogByPlayer() {
	
		String player = "Isgalamido";
		
		List<Game> games = logParserService.parseLogByPlayer(player);
		
		Assert.assertNotNull(games);
		Assert.assertTrue(!games.isEmpty());
		Assert.assertTrue(games.get(0).getPlayers().contains(player));
	}
	
	@Test
	public void parseLogByPlayerInexistente() {
	
		String player = "RumperTumpskin";
		
		List<Game> games = logParserService.parseLogByPlayer(player);
		
		Assert.assertNotNull(games);
		Assert.assertTrue(games.isEmpty());
	}
}
