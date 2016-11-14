package api ;

public class Score  
{
   
    private String id;
    private int score;
    private int percentage;
    private boolean isFinished;
    private boolean isJoined;
    

    /**
     * Constructor for objects of class Score
     */
    public Score()
    {
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }

    public void setPercentage(int percentage){
        this.percentage = percentage;
    }
    
    public int getPercentage(){
        return percentage;
    }
    
    public void setIsFinished(boolean isFinished){
        this.isFinished = isFinished;
    }
    
    public boolean isIsFinished(){
        return isFinished;
    }
    
    
    public boolean isIsJoined() {
	return isJoined;
    }

    public void setIsJoined(boolean isJoined) {
	this.isJoined = isJoined;
    }
    
    public String toString(){
        return id+":"+score;
    }
    
}
