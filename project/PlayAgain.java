import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayAgain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayAgain extends UserInput
{
    private GameWorld world;
    
    public PlayAgain(GameWorld world){
        this.world = world;
    }
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo(); 
        if(mouse!=null){
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();
            if(mouseX > getX() - 62 && mouseX < getX() + 62 && mouseY > getY() - 25 && mouseY < getY() + 25){
                this.setImage("play-btn2.png");
                if(Greenfoot.mouseClicked(this)){
                    world.getScreen().setPreviousScreen();
                }
            } else {
                this.setImage("replay-btn.png");
            }
        }
    }
}
