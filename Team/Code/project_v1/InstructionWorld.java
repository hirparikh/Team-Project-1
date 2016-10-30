import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class InstructionWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionWorld extends World
{
    
    private Button goButton;
    private GreenfootSound sound;
    private String playerName;
    
    /**
     * Constructor for objects of class InstructionWorld.
     * 
     */
    public InstructionWorld(String name)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground(new GreenfootImage("instruction_background.jpg"));
        playerName=name;
        sound = new GreenfootSound("sounds/GameStart.wav");
        sound.playLoop();
        loadInstruction(playerName);
        
    }
    
    private void loadInstruction(String name){
    
        
        InstructionBoard ib = new InstructionBoard(name);
        addObject(ib,getWidth()/2,getHeight()/2);
        //ib.setInstruction();
        
        goButton = new Button("Play", new Point(100, 30));
        addObject(goButton, 400, 550);
    
    }
    
    @Override
    public void act(){
        
        if (goButton.wasClicked())
        {
            
            sound.stop();
            Greenfoot.playSound("sounds/notification_bhopu.wav");
            World w = new MyWorld(playerName);
            Greenfoot.setWorld(w);
        }

    }
}
