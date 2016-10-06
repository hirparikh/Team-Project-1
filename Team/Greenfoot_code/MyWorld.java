import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800*600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
    }
    
    
    public void prepare(){

        
        ScoreBoard scoreboard = new ScoreBoard();
        addObject(scoreboard,298,178);
        removeObject(scoreboard);
        ScoreBoard scoreboard2 = new ScoreBoard();
        addObject(scoreboard2,139,80);
        TreasureChest treasurechest = new TreasureChest();
        addObject(treasurechest,609,90);
    }
}
