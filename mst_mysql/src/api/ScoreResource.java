package api;

import org.json.*;
import org.restlet.representation.*;
import org.restlet.resource.*;
import java.io.IOException;
import org.restlet.ext.jackson.*;

public class ScoreResource extends ServerResource {

	@Get
	public Representation get() throws JSONException {
		try {
			int roomId = Integer.parseInt(getQueryValue("roomId"));
			boolean isFirst = Boolean.parseBoolean(getQueryValue("isFirst"));
			return new JacksonRepresentation<Score>(MySQL.getScore(roomId, isFirst));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Post
	public void postData(Representation jsonRep) throws IOException {
		JacksonRepresentation<Score> inputRep = new JacksonRepresentation<Score>(jsonRep, Score.class);
		Score score = inputRep.getObject();
		MySQL.setScore(score);
	}
}
