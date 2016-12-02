import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

/**
 * Button
 * <p>
 * GUI item that is a box that captures click events on itself.<p>
 * <p>
 * Action listener: wasClicked()
 * 
 * @author Taylor Born
 * @version November 2010 - April 2014
 */
public class Button extends WindowComponent
{
    private Point size;
    private String text;
    private boolean hover;
    private boolean pressed;
    private GreenfootImage icon = new GreenfootImage(1, 1);
    private boolean iconVisible;
    private boolean clicked;
    private boolean enabled = true;
    
    private Color pressColor = new Color(100, 100, 100, 180);
    
    private boolean acceptByEnterKey;
    private boolean enterKeyPressed;
    
    private boolean continuePress;
    private int pressCount;
    
    private GreenfootImage image;

    /**
     * Create a new Button.
     * @param text The text the Button is to display.
     * @param size The width and height of the Button.
     */
    public Button(String text, Point size)
    {
        this.size = size;
        image = new GreenfootImage((int)size.getX(), (int)size.getY());
        setImage(image);
        this.text = text;
        draw();
    }
    
    @Override
    public void act() 
    {
        super.act();
        if (!enabled)
            return;
        
        boolean lastHover = hover;
        boolean lastPressed = pressed;
        
        hover = mouseOverThis();
        
        if (Greenfoot.mousePressed(this))
            pressed = true;
        
        if (pressed) {
            if (continuePress && ++pressCount == 5) {
                clicked = true;
                pressCount = 0;
            }
            if (Greenfoot.mouseClicked(this))
                clicked = true;
            if (Greenfoot.mouseClicked(null) || Greenfoot.mouseDragEnded(null))
            {
                pressed = false;
                pressCount = 0;
            }
        }
        if (lastHover != hover || lastPressed != pressed)
            draw();
        
        if (acceptByEnterKey) {
            boolean eKeyDown = Greenfoot.isKeyDown("enter");
            if (!enterKeyPressed && eKeyDown)
                clicked = true;
            enterKeyPressed = eKeyDown;
        }
    }
    
    @Override
    protected void redraw()
    {
        draw();
    }
    
    private void draw()
    {
        image.setColor(pressed ? pressColor : hover ? hoverColor : backColor);
        if (image.getColor().getAlpha() != 255)
            image.clear();
        image.fill();
        
        Graphics2D g = image.getAwtImage().createGraphics();
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(enabled ? textColor : disableColor);
        g.drawString(text, ((int)size.getX() - g.getFontMetrics().stringWidth(text)) / 2 + (int)size.getX() % 2, ((int)size.getY() + image.getFont().getSize()) / 2 - 1);
        g.dispose();
        
        image.setColor(borderColor);
        image.drawRect(0, 0, (int)size.getX() - 1, (int)size.getY() - 1);
        image.setColor(new Color((backColor.getRed() + borderColor.getRed()) / 2, (backColor.getGreen() + borderColor.getGreen()) / 2, (backColor.getBlue() + borderColor.getBlue()) / 2));
        image.drawRect(1, 1, (int)size.getX() - 3, (int)size.getY() - 3);
        
        if (iconVisible)
            image.drawImage(icon, 3, 4);
    }
    
    /**
     * The action listener for the Button.
     * @return Whether the Button was clicked or not.
     */
    public boolean wasClicked()
    {
        boolean c = clicked;
        clicked = false;
        return c;
    }
    
    @Override
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        pressCount = 0;
        pressed = false;
        hover = false;
        draw();
    }
    
    /**
     * Set if the Button is enabled.
     * @param e Whether the Button will be enabled or not.
     */
    public void setEnable(boolean e)
    {
        if (enabled != e)
            hover = false;
        enabled = e;
        draw();
    }
    
    /**
     * Set if the Button is to display its icon.
     * @param show Whether the Button will display its icon.
     */
    public void showIcon(boolean show)
    {
        iconVisible = show;
        draw();
    }
    
    /**
     * Get the text the Button is displaying.
     * @return The text the Button is displaying.
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * Set what text the Button is displaying.
     * @param text The text the Button will display.
     */
    public void setText(String text)
    {
        this.text = text;
        draw();
    }
    
    public void setContinuePress(boolean cont)
    {
        continuePress = cont;
    }
    
    public void setAcceptByEnterKey(boolean accept)
    {
        acceptByEnterKey = accept;
    }
    
    @Override
    public boolean mousePressedOnThisOrComponents()
    {
        return false;
    }
}