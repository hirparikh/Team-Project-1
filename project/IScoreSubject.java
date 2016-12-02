
public interface IScoreSubject  
{
    public abstract void attach(IScoreObserver obj);
	public abstract void detach(IScoreObserver obj);
	public abstract void notifyObservers(Score opponent);
}
