/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
import java.util.List;

public interface IServer  
{
    Score getScore(Score score);
    void setScore(Score score);
    Score reserveRoom(Score score);
    Score opponentArrived(int roomId);
    List<Score> getLeaderBoard();
    
}
