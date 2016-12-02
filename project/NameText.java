import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class NameText extends UserInput
{
    static final int MAX_LENGTH = 17;
    String name = "_ _ _ _ _";
    
    public NameText(){
        updateImage();
        name = "";
    }
    
    private void updateImage(){
        GreenfootImage image = new GreenfootImage(22 * name.length() + 30, 30);
        image.setFont(new Font("OCR A Extended", Font.BOLD, 35));
        image.setColor(Color.CYAN);
        image.drawString(name, 0, 27);
        setImage(image);
    }

    public void act(){
        String key = Greenfoot.getKey();
        if (key == null) return;
        if ("enter".equals(key) && name.length() > 0)
        {
           return;
        }
        if (key.equals("backspace") && name.length() > 0) name = name.substring(0, name.length() - 1);
        if (key.equals("space")) key = " ";
        if (key.length() == 1 && name.length() < MAX_LENGTH) name += key.toUpperCase();
        updateImage();
    }
   
    public String getText()
    {
        return name;
    }
}
