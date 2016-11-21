import greenfoot.*;

public interface IGameSubject  
{
     public abstract void attach(IGameObserver obj);
     public abstract void detach(IGameObserver obj);
     public abstract void notifyObservers(Element element, boolean doChain);
}
