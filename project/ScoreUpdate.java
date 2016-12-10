/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import java.util.*;

public class ScoreUpdate extends Thread implements IScoreSubject
{
    private ArrayList<IScoreObserver> observers = new ArrayList<IScoreObserver>() ;
    private GameWorld world;
    private boolean isFinished;
    private IServer server = new Server();

    public ScoreUpdate(GameWorld world)
    {
        this.world = world;
    }
    
    public void attach(IScoreObserver obj) {
        observers.add(obj) ;
    }

    public void detach(IScoreObserver obj) {
        observers.remove(obj) ;
    }

    public void notifyObservers(Score opponent) {
        for (IScoreObserver obj  : observers)
        {
            obj.updateOpponentScore(opponent);
        }
    }

    public void run(){
        do{
            Score opponent = server.getScore(world.getScore());
            notifyObservers(opponent);
            try{
                Thread.sleep(500);//1 sec
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }while(!isFinished);
    }
    
    public boolean isFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}
