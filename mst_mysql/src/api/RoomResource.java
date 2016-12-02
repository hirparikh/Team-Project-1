package api;

import java.io.IOException;
import org.json.JSONException;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class RoomResource extends ServerResource {
	@Get
	public Representation get(JsonRepresentation jsonRep) throws JSONException {
		try {
			int roomId = Integer.parseInt(getQueryValue("roomId"));
			return new JacksonRepresentation<Score>(MySQL.getOpponent(roomId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Post
	public Representation postData(Representation jsonRep) throws IOException {
		JacksonRepresentation<Score> inputRep = new JacksonRepresentation<Score>(jsonRep, Score.class);
		Score score = inputRep.getObject();
		return new JacksonRepresentation<Score>(MySQL.setRoom(score));
	}
}
