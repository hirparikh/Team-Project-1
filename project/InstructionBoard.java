import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class InstructionBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionBoard extends Actor
{
    private String instructions;
    /**
     * Act - do whatever the InstructionBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public InstructionBoard(String name){
        
        instructions = "Welcome, " + name + "!!" + System.getProperty("line.separator") 
                        + "Please follow the instructions given below to start the game"
                        + System.getProperty("line.separator") + System.getProperty("line.separator")
                        + System.getProperty("line.separator")
                        + "To start with the game, pick any one bulb" +System.getProperty("line.separator")
                        + "After that, select the highlighted route such that you light the bulb in minimum time "+ 
                        System.getProperty("line.separator")
                        + "Follow the giuded path until you light up every bulb" +
                        System.getProperty("line.separator")
                        +"This way you get enough light to identify which jewels are inside the treasurebox ;)"
                        +System.getProperty("line.separator")
                        +System.getProperty("line.separator")
                        +" Keep in mind that you have to cover minimum miles and that too faster than your opponent"
                        +System.getProperty("line.separator")
                        +System.getProperty("line.separator")
                        +" Click Play to start the Treasure Hunt!! All the best!!";
        GreenfootImage ins = new GreenfootImage(instructions,20,Color.orange,new Color(0, 0, 0, 0));
        //ins.setColor(java.awt.Color.white);
        //ins.drawString("Hey there!!",200,200);
        //setImage(ins);
        setImage(ins);
    }
}
