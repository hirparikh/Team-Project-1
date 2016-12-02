import greenfoot.*;
import java.awt.Color;

public class BackGround extends World
{
    //  Alter the parameters in the following statement to suit your needs
    //  The first string is like a title, the second is the units used in the bar
    //  The first integer is the initial value and the second is the maximum value
    public Bar bar = new Bar("Player 1", "Health Points", 25, 100);
    
    public BackGround()
    {    
        super(500, 520, 1);
        addObject(bar, 250, 40);
        GreenfootImage bg = getBackground();
        bg.setColor(Color.BLACK);
//   Un-comment the next to lines, as well as the commented lines in Bar class for a different view
//         bg.fill();
//         bg.setColor(Color.WHITE);
//   Do not un-comment this line.  If the two lines above are active, activate those commented in Bar class
        bg.drawString("SAMPLE PROGRESS BAR/HEALTH METER", 140, 12);
        bg.drawString("Use left/right arrow keys to change the value (color change at 20 percent)", 50, 80);
       
        addObject(new Smiley(), 30, 30);
    }
    
    public void act()
    {
        if (bar.getValue() == bar.getMinimumValue())
        {
            if (getObjects(GameOver.class).isEmpty()) showGameOver();
            return;
        }
        String key = Greenfoot.getKey();
        if ("left".equals(key)) bar.subtract(1);
        if ("right".equals(key)) bar.add(1);
    }
    
    private void showGameOver()
    {
        addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
    }
}
