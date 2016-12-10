/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

public enum State  
{
    NOT_SELECTED("Not Selected"), SELECTED("Selected"), SUGGESTED("Suggested");
    
    private String value;
    
    private State(String value){
        this.value = value;
    }
}
