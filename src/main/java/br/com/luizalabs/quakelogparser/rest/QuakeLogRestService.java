package br.com.luizalabs.quakelogparser.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.luizalabs.quakelogparser.model.Game;
import br.com.luizalabs.quakelogparser.service.LogParserService;
import br.com.luizalabs.quakelogparser.util.JsonUtils;

@Path("/quakelog")
@Produces(MediaType.APPLICATION_JSON)
public class QuakeLogRestService {

	private LogParserService logParserService = new LogParserService();
	
	@GET
	public String getLog() {

		List<Game> games = logParserService.parseLog(null, null);

		return JsonUtils.toJson(games);
	}
	
	@GET
	@Path("game/{gameNumber}")
	public String getLogByGame(@PathParam("gameNumber") final Long gameNumber) {
		
		Game game = logParserService.parseLogByGame(gameNumber);

		if (game != null) {
			return JsonUtils.toJson(game);
		} else {
			return JsonUtils.toJson("");
		}
	}
	
	@GET
	@Path("player/{player}")
	public String getLogByPlayer(@PathParam("player") final String player) {
		
		List<Game> games = logParserService.parseLogByPlayer(player);

		if (!games.isEmpty()) {
			return JsonUtils.toJson(games);
		} else {
			return JsonUtils.toJson("");
		}
	}

}