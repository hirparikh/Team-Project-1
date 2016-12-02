import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends UserInput
{
    private GameWorld world;
    
    public StartButton(GameWorld world){
        this.world = world;
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 112 && mouseX < getX() + 112 && mouseY > getY() - 45 && mouseY < getY() + 45){
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
