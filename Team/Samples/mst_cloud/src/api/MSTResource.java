package api ;

import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;
import java.util.HashMap;
import java.io.IOException;
import org.restlet.ext.jackson.* ;

public class MSTResource extends ServerResource {

    MST mst = MST.getInstance();

    @Get
    public Representation get() throws JSONException {
        //JSONObject json = new JSONObject(mst.getMap());
        //return new JsonRepresentation (json);
		return new JacksonRepresentation<HashMap>(mst.getMap());
    }

    @Post
    public Representation postData(Representation jsonRep) throws IOException {
		JacksonRepresentation<Score> inputRep = new JacksonRepresentation<Score> ( jsonRep, Score.class ) ;
        Score score = inputRep.getObject();
		mst.getMap().put(score.getId(), score);
        JSONObject response = new JSONObject() ;
        response.put("status", 200);
        return new JsonRepresentation (response) ;
    }
}

