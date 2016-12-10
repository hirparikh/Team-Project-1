/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class Text extends Actor
{
    GreenfootImage image, textImage;
    Color color;
    int size;
    public void act(){
    }
    
    public Text(Color color, int size){
        this.color = color;
        this.size = size;
    }
    
    Text(String text, Color color, int size){
        this.color = color;
        this.size = size;
        GreenfootImage image = new GreenfootImage(22 * text.length() + 30, size);
        image.setFont(new Font("OCR A Extended", Font.BOLD, size));
        image.setColor(color);
        image.drawString(text, 0, size);
        setImage(image);
    }
    
    public void changeText(String text){
        image = new GreenfootImage(10 * text.length() + 50, 60);
        image.setFont(new Font("OCR A Extended", Font.BOLD, size));
        image.setColor(color);
        int x = text.length() == 1 ? 17 : 10;
        image.drawString(text, x, size);
        setImage(image);
    }
}
