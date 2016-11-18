import greenfoot.*;
import java.awt.Color;

public class GameOver extends Actor
{
    Color[] colors = { Color.ORANGE, Color.RED };
    int colorNum = 0;
    int counter = 1;

    public void act()
    {
        if (--counter == 0)
        {
            colorNum = ++colorNum % 2;
            updateImage();
            counter = 50;
        }
    }
    
    public void updateImage()
    {
        setImage(new GreenfootImage("GAME\nOVER", 96, colors[colorNum], new Color(0, 0, 0, 0)));
    }
}
