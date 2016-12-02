public enum ElementType  
{
   NODE("Node"), EDGE("Edge");
   
   private String name;
   
    private ElementType(String name)
    {
        this.name = name;
    }
    
    public String toString(){
        return name;
    }
}