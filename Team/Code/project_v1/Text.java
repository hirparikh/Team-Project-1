import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    GreenfootImage image, textImage;
    public void act(){
    }
    
    public Text(){}
    
    Text(String text){
        GreenfootImage image = new GreenfootImage(22 * text.length() + 30, 30);
        image.setFont(new Font("OCR A Extended", Font.BOLD, 35));
        image.setColor(Color.CYAN);
        image.drawString(text, 0, 27);
        setImage(image);
        
        /*
        textImage = new GreenfootImage(text, 20, Color.CYAN, new Color(0,0,0,0)); 
        image = new GreenfootImage(textImage.getWidth() + 40, textImage.getHeight() + 20);
        image.drawRect(0, 0, image.getWidth(), image.getHeight());
        image.drawImage(textImage, (image.getWidth() - textImage.getWidth()) / 2, (image.getHeight() - textImage.getHeight()) / 2);
        setImage(image);*/
    }
    
    public void changeText(String text){
        image = new GreenfootImage(10 * text.length() + 50, 60);
        image.setFont(new Font("OCR A Extended", Font.BOLD, 40));
        image.setColor(Color.CYAN);
        int x = text.length() == 1 ? 17 : 10;
        image.drawString(text, x, 40);
        setImage(image);
    }
}
