import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Node extends Element implements IGameSubject, IGameObserver
{
    private Set<Edge> edgeSet;
    private GameScreen gameScreen;
    private CardPart cardPart;
    
    public Node(GameScreen gameScreen, CardPart cardPart) {
        super(State.SUGGESTED);
        this.gameScreen = gameScreen;
        edgeSet = new HashSet<Edge>();
        this.cardPart = cardPart;
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
           //Greenfoot.playSound("sounds/node_click.mp3");
           pickNode();  
       }
    }
    
    public void pickNode() {
        if (gameScreen.isFirstNode() && state.equals(State.SUGGESTED)) {
            setState(State.SELECTED);
            gameScreen.setFirstNode(false);
            for (Node n : gameScreen.getNodeSet()) {
                if (n != this) {
                    n.setState(State.NOT_SELECTED);
                }
            }
            notifyObservers(null, false);
            moveToDestination();
            //just to update current scoreboard
            gameScreen.checkResult();
        }
    }
    
    public void update(Element element, boolean doChain){
        switch(state){
            case NOT_SELECTED:
                setState(State.SUGGESTED);
            break;
            case SELECTED:
                //do nothing
            break;
            case SUGGESTED:
                if(doChain){
                    setState(State.SELECTED);
                    notifyObservers(element, false);
                    moveToDestination();
                }
            break;
        }
    }
    
    
    public void notifyObservers(Element element, boolean doChain){
        for(IGameObserver observer: observers){
            if(element != observer){
                observer.update(this, doChain);
            }
        } 
    }
    
    public void setState(State state){ 
        this.state = state;
        switch(state){
            case NOT_SELECTED :
                setImage("node1.png");
            break;
            case SELECTED :
                setImage("node3.png");
            break;
            case SUGGESTED :
                setImage("node2.png");
            break;
        }
    }
    
    public void moveToDestination(){
        int initX = cardPart.getX();
        int initY = cardPart.getY();
        int destX = cardPart.getDestX();
        int destY = cardPart.getDestY();
        cardPart.turnTowards(destX, destY);
        double distance = Math.sqrt(Math.pow(Math.abs(destX - initX),2) + Math.pow(Math.abs(destY - initY),2));
        distance /= 20;
        for(int i = 0; i < 20; i++)
        {
            cardPart.move(distance);
            Greenfoot.delay(2); 
        }
        cardPart.setRotation(0);
        Greenfoot.delay(2);
        cardPart.setLocation(destX, destY);
    }
    
    public Set<Edge> getEdgeSet() {
        return edgeSet;
    }

    public void setEdgeSet(Set<Edge> edgeSet) {
        this.edgeSet = edgeSet;
    }
}
