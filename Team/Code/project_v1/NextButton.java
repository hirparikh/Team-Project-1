import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NextButton extends UserInput
{
    private GameWorld world;
    
    public NextButton(GameWorld world){
        this.world = world;
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 54 && mouseX < getX() + 54 && mouseY > getY() - 25 && mouseY < getY() + 25){
                this.setImage("next-btn2.png");
                if(Greenfoot.mouseClicked(this)){
                    world.getScreen().setNextScreen(null);
                }
            } else {
                this.setImage("next-btn.png");
            }
        }
    }
}
