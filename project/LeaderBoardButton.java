/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LeaderBoardButton extends UserInput
{
    public LeaderBoardButton(GameWorld world){
        super(world);
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 100 && mouseX < getX() + 100 && mouseY > getY() - 25 && mouseY < getY() + 25){
                this.setImage("leaderboard-btn2.png");
                if(Greenfoot.mouseClicked(this)){
                    world.getScreen().setNextScreen(null);
                }
            } else {
                this.setImage("leaderboard-btn.png");
            }
        }
    }
}
