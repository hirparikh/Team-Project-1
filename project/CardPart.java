/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CardPart extends SmoothMover
{
    private int destX, destY;
    
    public CardPart(int destX, int destY, String image){
        this.destX = destX;
        this.destY = destY;
        setImage(image);
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
    public int getDestX(){
        return destX;
    }
    
    public int getDestY(){
        return destY;
    }
}
