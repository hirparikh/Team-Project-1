import java.util.*;

public class ScoreUpdate extends Thread implements IScoreSubject
{
    private ArrayList<IScoreObserver> observers = new ArrayList<IScoreObserver>() ;
    private GameWorld world;
    private GameScreen screen;

    public ScoreUpdate(GameWorld world, GameScreen screen)
    {
        this.world = world;
        this.screen = screen;
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
        while(!screen.isFinished()){
            Score opponent = Server.getScore(world.getScore());
            notifyObservers(opponent);
            try{
                Thread.sleep(1000);//1 sec
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
