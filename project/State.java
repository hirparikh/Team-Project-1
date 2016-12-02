
public enum State  
{
    NOT_SELECTED("Not Selected"), SELECTED("Selected"), SUGGESTED("Suggested");
    
    private String value;
    
    private State(String value){
        this.value = value;
    }
}
