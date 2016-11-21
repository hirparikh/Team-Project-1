import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BackButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackButton extends UserInput
{
    private GameWorld world;
    
    public BackButton(GameWorld world){
        this.world = world;
    }
    
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 62 && mouseX < getX() + 62 && mouseY > getY() - 25 && mouseY < getY() + 25){
                this.setImage("back-btn2.png");
                if(Greenfoot.mouseClicked(this)){
                    world.getScreen().setPreviousScreen();
                }
            } else {
                this.setImage("back-btn.png");
            }
        }
    }
}
