package api ;

import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;
import java.util.HashMap;

public class MSTResource extends ServerResource {

    MST mst = MST.getInstance();

    @Get
    public Representation get() throws JSONException {
        JSONObject json = new JSONObject(mst.getMap());
        return new JsonRepresentation (json);
    }

    @Post
    public Representation post(JsonRepresentation jsonRep) {
        JSONObject json = jsonRep.getJsonObject() ;
        String id = json.getString("id") ;
	int score = json.getInt("score") ;
	mst.getMap().put(id, score);
        JSONObject response = new JSONObject() ;
        response.put("status", 200);
        return new JsonRepresentation (response) ;
    }
}

