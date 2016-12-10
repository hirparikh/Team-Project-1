/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NextButton extends UserInput
{
    public NextButton(GameWorld world){
        super(world);
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 54 && mouseX < getX() + 54 && mouseY > getY() - 25 && mouseY < getY() + 25){
                this.setImage("next-btn.png");
                if(Greenfoot.mouseClicked(this)){
                    world.getScreen().setNextScreen(null);
                }
            } else {
                this.setImage("next-btn2.png");
            }
        }
    }
}
