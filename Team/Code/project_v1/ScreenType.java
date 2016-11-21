
public enum ScreenType  
{
   START("Start Screen"), STORY("Story Screen"), INSTRUCTION("Instruction Screen"), WAIT("Wait Screen"),
   GAME("Game Screen"), WIN("Win Screen"), LOSE("Lose Screen"), LEADERBOARD("Leaderboard Screen");
   
   private String name;
   
    private ScreenType(String name)
    {
        this.name = name;
    }
    
    public String toString(){
        return name;
    }
}
