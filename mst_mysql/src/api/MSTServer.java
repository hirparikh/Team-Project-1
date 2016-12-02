package api;

import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class MSTServer extends Application {

	public static void main(String[] args) throws Exception {
		Component server = new Component();
		server.getServers().add(Protocol.HTTP, 8080);
		server.getDefaultHost().attach(new MSTServer());
		server.start();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/score", ScoreResource.class);
		router.attach("/room", RoomResource.class);
		router.attach("/leaderboard", LeaderBoardResource.class);
		return router;
	}
}
