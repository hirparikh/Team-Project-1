import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor
{
    /**
     * Act - do whatever the ScoreBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int score;
    int perc;
    Text scoreText, percText;
    World w;
    public ScoreBoard(World w){
         this.w = w;
         setImage("images/score_board.png");
    }   
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void updateScore(int score,int perc){
        this.score = score;
        this.perc = perc;
       
        if(scoreText != null){
            w.removeObject(scoreText);
        }
        if(percText != null){
            w.removeObject(percText);
        }
        scoreText = new Text("Score : "+String.valueOf(score), Color.black);
        w.addObject(scoreText,getX()-10,getY()-10);
        percText = new Text("Completed : "+String.valueOf(perc)+"% ", Color.black);
        w.addObject(percText,getX(),getY()+10);
    }    
}
