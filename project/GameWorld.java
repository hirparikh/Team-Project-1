import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
