import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
import java.io.IOException;
import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.jackson.* ;
import org.restlet.resource.* ;
import org.restlet.ext.json.* ;

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
    Score score;
  
    Text scoreText, percText, oppScoreText, oppPercText;
    World w;
    static String ipAddress = "10.0.0.175";
    public ScoreBoard(World w){
         this.w = w;
         setImage("images/score_board.png");
         score = new Score();
         score.setId(((MyWorld)w).name);
        
    }   
    public void act() 
    {
        // Add your action code here.
    }    

    public void printScore(){
        try{
        ServerConnection sc = new ServerConnection();
        ClientResource clientResourceObj = sc.getServerConnection("http://"+ipAddress+":8080/mst");
        
         Representation result = clientResourceObj.get();
        JacksonRepresentation<HashMap> inputRep = new JacksonRepresentation<HashMap> ( result, HashMap.class ) ;
         HashMap mp = inputRep.getObject();
         
         String[] players_name = new String[2];
         int i=0;
         
        Iterator it = mp.entrySet().iterator();
        if(it.hasNext()){
                if(i<2){
                Map.Entry entry = (Map.Entry)it.next();
                players_name[i++]=(String)entry.getKey(); 
            }
        }
        
        
        
        String player_id =players_name[0];
        String opponent_id = players_name[1];
        ScoreUpdate su = new ScoreUpdate();
        
        //HashMap<String,HashMap<String,Object>> scores = su.getScore();
        HashMap<String,Score> scores = su.getScore();
        if(scoreText != null){
            w.removeObject(scoreText);
            
        }
        if(percText != null){
            w.removeObject(percText);
        }
        
         if(oppScoreText != null){
            w.removeObject(oppScoreText);
            
        }
        if(oppPercText != null){
            w.removeObject(oppPercText);
        }
        System.out.println(scores.toString());
        scoreText = new Text("Score : "+player_id+":"+scores.get(player_id).getScore(), Color.black);
        w.addObject(scoreText,getX()-10,getY()-25);
        percText = new Text("Completed : "+scores.get(player_id).getPercentage()+"% ", Color.black);
        w.addObject(percText,getX(),getY()-10);
        oppScoreText = new Text("Opp Score : "+opponent_id+":"+scores.get(opponent_id).getScore(), Color.black);
        w.addObject(oppScoreText,getX(),getY()+5);
        oppPercText = new Text("Opp Completed : "+scores.get(opponent_id).getPercentage()+"% ", Color.black);
        w.addObject(oppPercText,getX(),getY()+20);
    }catch (Exception e){
        e.printStackTrace();
    }
        
        
    } 
    
    public void updateScore(int score,int perc, boolean isFinished,boolean isJoined){
        this.score.setScore(score);
        this.score.setPercentage(perc);
        this.score.setIsFinished(isFinished);
        this.score.setIsFinished(isJoined);
        ScoreUpdate.setScore(this.score);
       
        
       /* if(scoreText != null){
            w.removeObject(scoreText);
        }
        if(percText != null){
            w.removeObject(percText);
        }*/
       // scoreText = new Text("Score : "+String.valueOf(score), Color.black);
       // w.addObject(scoreText,getX()-10,getY()-10);
       // percText = new Text("Completed : "+String.valueOf(perc)+"% ", Color.black);
       // w.addObject(percText,getX(),getY()+10);

    }   
}
