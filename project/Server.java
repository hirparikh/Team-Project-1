/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import java.util.*;
import org.restlet.representation.*;
import org.restlet.ext.jackson.*;
import org.restlet.resource.*;

public class Server implements IServer{

    private static String serverAddress = "http://35.161.244.30/";

    public Score getScore(Score score) {
        try {
            ClientResource clientresource = new ClientResource(
                    serverAddress + "score?roomId=" + score.getRoomId() + "&isFirst=" + score.isFirst());
            Representation result = clientresource.get();
            JacksonRepresentation<Score> inputRep = new JacksonRepresentation<Score>(result, Score.class);
            score = inputRep.getObject();
            return score;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setScore(Score score) {
        try {
            ClientResource clientresource = new ClientResource(serverAddress + "score");
            Representation result = clientresource.post(new JacksonRepresentation<Score>(score));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public Score reserveRoom(Score score) {
        try {
            ClientResource clientresource = new ClientResource(serverAddress + "room");
            Representation result = clientresource.post(new JacksonRepresentation<Score>(score));
            JacksonRepresentation<Score> inputRep = new JacksonRepresentation<Score>(result, Score.class);
            Score opponent = inputRep.getObject();
            return opponent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Score opponentArrived(int roomId) {
        try {
            ClientResource clientresource = new ClientResource(serverAddress + "room?roomId=" + roomId);
            Representation result = clientresource.get();
            JacksonRepresentation<Score> inputRep = new JacksonRepresentation<Score>(result, Score.class);
            Score score = inputRep.getObject();
            return score;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Score> getLeaderBoard() {
        try {
            ClientResource clientresource = new ClientResource(serverAddress + "leaderboard?limit=5");
            Representation result = clientresource.get();
            JacksonRepresentation<ArrayList> inputRep = new JacksonRepresentation<ArrayList>(result, ArrayList.class);
            ArrayList list = inputRep.getObject();
            List<Score> scoreList = new ArrayList<Score>();
            for (Object obj : list) {
                JacksonRepresentation<Score> scoreRep = new JacksonRepresentation<Score>(new JacksonRepresentation(obj),
                        Score.class);
                scoreList.add(scoreRep.getObject());
            }
            Collections.sort(scoreList, Score.timeComparator);
            return scoreList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
