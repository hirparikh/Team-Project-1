import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class StartWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartWorld extends World
{

    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    private TextBox txtB;
    private Button btnClick;
    private GreenfootSound gfs; 
    
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
       super(800, 600, 1); 
       gfs = new GreenfootSound("sounds/GameStart.wav");
       gfs.playLoop();
       Font font = new Font("serif",Font.BOLD,20);
       txtB = new TextBox(new Point(150,40),"",font);
       txtB.setMaxLength(30);
       txtB.setMessage("name");
       addObject(txtB, 385,325);
       
       
       btnClick = new Button("Start game", new Point(100, 30));
       addObject(btnClick, 388, 419);
       prepare();
    }
    
    @Override
    public void act(){
        
        if (btnClick.wasClicked())
        {
            // Add 1 to the number already in my lblCounter
            
            gfs.stop();
            String name = txtB.getText();
            System.out.println(name);
            World w = new InstructionWorld(name);
            //World w = new GameWorld(name);
            Greenfoot.setWorld(w);
        }

    }
    
    public void prepare(){

        
        
    }
}
