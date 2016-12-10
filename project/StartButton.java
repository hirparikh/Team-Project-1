/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartButton extends UserInput
{
    public StartButton(GameWorld world){
        super(world);
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 60 && mouseX < getX() + 60 && mouseY > getY() - 25 && mouseY < getY() + 25){
                this.setImage("start-btn2.png");
                if(Greenfoot.mouseClicked(this)){
                    world.getScreen().setNextScreen(null);
                }
            } else {
                this.setImage("start-btn.png");
            }
        }
    }
}
