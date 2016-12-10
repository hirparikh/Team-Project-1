/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Element extends Actor implements IElement
{
    State state;
    List<IGameObserver> observers = new ArrayList<IGameObserver>();
    
    public Element(State state){
        this.state = state;
    }
    
    public State getState(){
        return state;
    }
    
    public void attach(IGameObserver obj){
        observers.add(obj);
    }
    public void detach(IGameObserver obj){
        observers.remove(obj);
    }
}
