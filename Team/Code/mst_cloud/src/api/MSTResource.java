package api ;

import java.io.IOException;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import java.util.HashMap;

public class MSTResource extends ServerResource {

    MST mst = MST.getInstance();

    @Get
    public Representation get() throws JSONException {
        return new JacksonRepresentation<Map>(mst.getMap());
    }

    @Post
    public Representation post(Representation rep) {
		JacksonRepresentation<Input> inputRep = new JacksonRepresentation<Input> ( rep, Input.class ) ;
        Input input = inputRep.getObject() ;
        String id = input.getId() ;
		int score = input.getScore() ;
		mst.getMap().put(id, score);
        Response response = new Response();
        response.setStatus(200);
        return new JacksonRepresentation<Response>(response) ;
    }
}

