import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.util.*;
import java.io.*;
import java.lang.*;
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
    private boolean opponentJoined = false;
    
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
            //Notify serveice that the user has joined the game
               notifyServerForJoiningStatus();
            //check if the opponent has joined
            if(!hasOpponentJoined()){
                
                //add popup here
                
                //continuously check for opponent status if he has joined
                while(true){
                    if(hasOpponentJoined()){
                        break;
                    }
                    //don't remove the popup
                }
            }
            
            
            
            
            sound.stop();
            Greenfoot.playSound("sounds/notification_bhopu.wav");
            World w = new MyWorld(playerName);
            Greenfoot.setWorld(w);
        }

    }
    
    
    private void notifyServerForJoiningStatus(){
        
            Score score =new Score();
            score.setId(this.playerName);
            score.setScore(0);
            score.setPercentage(0);
            score.setIsFinished(false);
            score.setIsJoined(true);
            ScoreUpdate.setScore(score);
    }
    
    
    private boolean hasOpponentJoined(){
        boolean opponentStatus =false;
        boolean opponentJoined = false;
        Score opponentDtls = null;
        HashMap<String,Score> playerDtls = ScoreUpdate.getScore();
        System.out.println(playerDtls);
        for(String key : playerDtls.keySet()){
            
            if(!key.equals(this.playerName)){
               // opponentJoined = true;
                opponentDtls = playerDtls.get(key);
                break;
            }
        }
        if(opponentDtls!=null){
            opponentStatus = opponentDtls.isIsJoined();
        }
        
        return opponentStatus;
    }
}
