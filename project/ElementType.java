/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

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