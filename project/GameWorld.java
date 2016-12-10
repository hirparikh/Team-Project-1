/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameWorld extends World
{

    private IScreen screen;
    private Score score;
    
    public GameWorld()
    {
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1);
        screen = ScreenFactory.getScreen(ScreenType.START, this);
        screen.displayScreen();
        score = new Score();
    }
    
    public IScreen getScreen(){
        return screen;
    }
    
    public void setScreen(IScreen screen){
        this.screen = screen;
    }
    
    public Score getScore(){
        return score;
    }
    
    public void setScore(Score score){
        this.score = score;
    }
    
}
