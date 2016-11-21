import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Edge extends Element implements IGameSubject, IGameObserver
{
    private int weight;
    private Node n1, n2;
    private GameScreen gameScreen;
    private boolean scaleImage;
    private String[] alignArr;
    
    public Edge(Node n1, Node n2, int weight, String[] alignArr, GameScreen gameScreen) {
         super(State.NOT_SELECTED);
         this.weight = weight;
         this.n1 = n1;
         this.n2 = n2;
         this.alignArr = alignArr;
         this.gameScreen = gameScreen;
         setImage(alignArr[0]);
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this) && !gameScreen.isFirstNode()){
            //Greenfoot.playSound("sounds/edge_click.mp3");
            pickEdge();            
        }
    }
    
    public void pickEdge() {
        if (state.equals(State.SUGGESTED)) {
            setState(State.SELECTED);
            Node other;
            if (getN1().getState().equals(State.SELECTED)) {
                notifyObservers(getN1(), true);
            } else {
                notifyObservers(getN2(), true);
            }
            // checking if entire graph is traversed
            gameScreen.checkResult();
        }
    }
    
    public void update(Element element, boolean doChain){
        switch(state){
            case NOT_SELECTED:
                setState(State.SUGGESTED);
                if (getN1().getState() == State.SELECTED) {
                    notifyObservers(getN1(), doChain);
                } else {
                    notifyObservers(getN2(), doChain);
                }
            break;
            case SELECTED:
            //do nothing
            break;
            case SUGGESTED:
                Node node = (Node)element;
                if ((getN1() == node && getN2().getState() == State.SELECTED)
                        || (getN2() == node && getN1().getState() == State.SELECTED)) {
                    setState(State.NOT_SELECTED);
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
    
    public void setState(State state) {
         this.state = state;
         switch(state){
             case NOT_SELECTED :
                setImage(alignArr[0]);
                break;
            case SUGGESTED :
                setImage(alignArr[1]);
                break;
            case SELECTED :
                setImage(alignArr[2]);
                break;
        }
    }
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getN1() {
        return n1;
    }

    public void setN1(Node n1) {
        this.n1 = n1;
    }

    public Node getN2() {
        return n2;
    }

    public void setN2(Node n2) {
        this.n2 = n2;
    }
}
