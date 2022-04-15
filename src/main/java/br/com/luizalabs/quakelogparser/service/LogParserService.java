package br.com.luizalabs.quakelogparser.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.luizalabs.quakelogparser.model.Game;

public class LogParserService {

	private static final String RESOURCE_NAME = "games.log";
	
	private static final String KEY_LOG_INIT_GAME = "InitGame:";
	private static final String KEY_LOG_KILL = "Kill:";
	private static final String KEY_LOG_SHUTDOWN = "ShutdownGame:";
	private static final String KEY_LOG_WORLD = "<world>";
	private static final String KEY_LOG_SEARCH_KILL_BEFORE = ": ";
	private static final String KEY_LOG_SEARCH_KILL_AFTER = " killed";
	private static final String KEY_LOG_SEARCH_KILLED_BEFORE = "killed ";
	private static final String KEY_LOG_SEARCH_KILLED_AFTER = " by";
	
	public List<Game> parseLog(final Long gameNumber, final String player) {
		
		List<Game> games = new ArrayList<>();
		Game game = null;
		Long gameNumberLog = 0l;
		
		for (String line : getLines()) {
			if (line.contains(KEY_LOG_INIT_GAME)) {
			
				if (isInvalidGameNumber(gameNumber, gameNumberLog)) {
					continue;
				}
				
				game = new Game();
				game.setNome("game_" + gameNumberLog);
				
			} else if (line.contains(KEY_LOG_KILL)) {
				
				if (isInvalidGameNumber(gameNumber, gameNumberLog)) {
					continue;
				}
				
				final String playerKill = getPlayerKill(line);
				final String playerKilled = getPlayerKilled(line);
				
				addListPlayers(game.getPlayers(), playerKill, playerKilled);
				
				game.setTotalKills(game.getTotalKills() + 1);
				
				if (!playerKill.equals(KEY_LOG_WORLD)) {
					addKill(game.getKills(), playerKill, 1l);
				} else {
					addKill(game.getKills(), playerKilled, -1l);
				}
				
			} else if (line.contains(KEY_LOG_SHUTDOWN)) {
				
				if (gameNumber != null && gameNumberLog.equals(gameNumber)) {
					games.add(game);
				}
				
				if (gameNumber == null) {
					if (player == null || (player != null && game.getPlayers().contains(player))) {
						games.add(game);
					}
				}
				
				gameNumberLog = gameNumberLog + 1;
			}
		}
		
		return games;
	}

	public Game parseLogByGame(final Long gameNumber) {
		
		final List<Game> games = parseLog(gameNumber, null);
		
		if (!games.isEmpty()) {
			return games.get(0);
		}
		
		return null;
	}
	
	public List<Game> parseLogByPlayer(final String player) {
		
		return parseLog(null, player);
	}
	
	private List<String> getLines() {
		
		try {
			return Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(RESOURCE_NAME).toURI()));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler o arquivo.", e);
		}
	}
	
	private boolean isInvalidGameNumber(final Long gameNumber, final Long gameNumberLog) {
		
		return (gameNumber != null && !gameNumberLog.equals(gameNumber));
	}
	
	private String getPlayerKill(final String line) {

		final String[] split = line.split(KEY_LOG_SEARCH_KILL_BEFORE);
		
		return split[2].split(KEY_LOG_SEARCH_KILL_AFTER)[0];
	}
	
	private String getPlayerKilled(final String line) {
	
		final String[] split = line.split(KEY_LOG_SEARCH_KILLED_BEFORE);
		
		return split[1].split(KEY_LOG_SEARCH_KILLED_AFTER)[0];
	}
	
	private void addListPlayers(final List<String> players, final String playerKill, final String playerKilled) {
		
		addListPlayer(players, playerKill);
		addListPlayer(players, playerKilled);
	}
	
	private void addListPlayer(final List<String> players, final String player) {
		
		if (!players.contains(player) && !player.equals(KEY_LOG_WORLD)) {
			players.add(player);
		}
	}

	private void addKill(final Map<String, Long> kill, final String player, final Long qtd) {
		
		Long qtdKillPlayer = kill.get(player);
		
		if (qtdKillPlayer == null) {
			qtdKillPlayer = 0l;
		}
		
		kill.put(player, qtdKillPlayer + qtd);
	}
	
}
