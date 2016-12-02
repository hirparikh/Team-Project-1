/**
 * Write a description of class IElement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IElement  
{
   State getState();
   void attach(IGameObserver obj);
   void detach(IGameObserver obj);
   
}