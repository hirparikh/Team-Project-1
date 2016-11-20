package api;

import java.util.List;

import org.json.JSONException;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class LeaderBoardResource extends ServerResource {
	@Get
	public Representation get(JsonRepresentation jsonRep) throws JSONException {
		try {
			int limit = Integer.parseInt(getQueryValue("limit"));
			return new JacksonRepresentation<List<Score>>(MySQL.getLeaderBoard(limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
