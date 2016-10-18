import greenfoot.MouseInfo;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.core.WorldHandler;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Point;

/**
 * TextBox
 * <p>
 * A container for an editable String.<br>
 * Works with different Fonts and sizes.<br>
 * <p>
 * Has caret position to input at particular index.<br>
 * Can move caret around by clicking at some position or by using arrow keys.<br>
 * <p>
 * Editable if has focus. Click on TextBox to give it focus. Loses focus when clicked off of it.<br>
 * Scrollable (by mouse wheel or click and dragging scroll bar) when String not all viewable at once.<br>
 * Auto scrolls while you type to stay with the blinking cursor.<br>
 * <p>
 * Accepts by default:<br>
 * All lower- and upper- case letters, all standard punctuation, new lines.<br>
 * <p>
 * Includes text selection which can be done by:<br>
 * 1. Click and drag.<br>
 * 2. Shift + click.<br>
 * 3. Shift + arrow keys.<br>
 * 4. Ctrl+a.<br>
 * 5. Double/Triple click.<br>
 * <p>
 * Includes copy/cut/paste functionality with ctrl+c / ctrl+x / ctrl+v<br>
 * 
 * @author Taylor Born
 * @version November 2010 - April 2014
 */
public class TextBox extends WindowComponent
{
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERS = LOWERCASE_LETTERS + UPPERCASE_LETTERS;
    public static final String NUMBERS = "0123456789";
    
    /**
     * Determine if there exists a TextBox that has focus, within the World.
     * @return Whethor or not there exists a TextBox that has focus, within the World.
     */
    public static boolean textBoxHasFocus()
    {
        for (Object o : WorldHandler.getInstance().getWorld().getObjects(TextBox.class))
            if (((TextBox)o).hasFocus())
                return true;
        return false;
    }

    private String text = "";
    private Point size;
    private Point clickedAt;
    private int clickCount;
    private int oldCaret;
    private int caret;
    private int blink;
    private ArrayList<String> strs = new ArrayList<String>();
    private int scrollValue;
    private boolean draggingScrollBar;
    private int[] presses = new int[4];
    private String accept = "";
    private String dontAccept = "";
    private int maxLength = -1;
    private boolean readOnly = false;
    private boolean focusable = true;
    
    private Point lastMouse = new Point(-1, -1);
    private ScrollingListener scroller = initializeScroller();
    private int scrollBar = 40;
    private int mouseDrag_yOffsetOnScrollBar;
    
    private Color blinkColor = Color.BLUE;
    private Color activeColor = Color.RED;
    private Color scrollColor = Color.BLUE;
    
    private final int SCROLL_WIDTH = 9;
    
    private Integer lastCursorX = null;
    
    private String message;
    private Color messageColor = Color.GRAY;
    private boolean password = false;
    
    private boolean pressedC, pressedV, pressedX;
    
    private boolean selecting;
    private int selectingPivot = -1;
    
    private boolean changed;
    
    private boolean treatAllAsUpperCase;
    public void readLowerAsUpperCase(boolean bool)
    {
        treatAllAsUpperCase = bool;
    }
    
    private boolean ctrTab;
    
    private DrawFunction drawFunction;
    
    /**
     * Create a new TextBox.
     * @param size The width and height of the TextBox.
     * @param text The initial text to display in the TextBox.
     * @param font The Font for the text.
     */
    public TextBox(Point size, String text, Font font)
    {
        if (font != null)
            this.font = font;
        if (size.getX() < 15)
            size = new Point(15, (int)size.getY());
        if (size.getY() < this.font.getSize() + 6)
            size = new Point((int)size.getX(), this.font.getSize() + 6);
        this.size = size;
        this.text = text;
        caret = text.length();
        drawFunction = new DrawFunction();
        drawFunction.paint();
    }
    
    /**
     * Create a new TextBox.
     * @param size The width and height of the TextBox.
     * @param text The initial text to display in the TextBox.
     */
    public TextBox(Point size, String text)
    {
        this(size, text, null);
    }
    
    @Override
    public void act() 
    {
        super.act();
        if (!focusable && hasFocus())
            removeFocus();
        
        oldCaret = caret;
        
        handleMouse();
        
        if (hasFocus())
        {
            // Progress caret blinking animation.
            if (++blink == 20)
                blink = -20;
            
            
            handleCopyCutPaste();
        
            if (Greenfoot.isKeyDown("control") && Greenfoot.isKeyDown("t"))
            {
                if (!ctrTab)
                {
                    deleteSelected();
                    text = text.substring(0, caret) + "    " + text.substring(caret);
                    changed = true;
                    // Increment caret pass inserted character.
                    caret += 4;
                    ctrTab = true;
                }
            }
            else
                ctrTab = false;
            
            if (Greenfoot.isKeyDown("control") && Greenfoot.isKeyDown("a"))
            {
                selectingPivot = 0;
                caret = text.length();
            }
            
            handleInput();
            
            handleArrowKeys();
        }
        
        if (oldCaret != caret)
            lastCursorX = null;
        
        drawFunction.paint();
    }
    
    private void handleCopyCutPaste()
    {
        if (!password)
        {
            boolean cDown = Greenfoot.isKeyDown("c");
            if (!cDown)
                pressedC = false;
            boolean vDown = Greenfoot.isKeyDown("v");
            if (!vDown)
                pressedV = false;
            boolean xDown = Greenfoot.isKeyDown("x");
            if (!xDown)
                pressedX = false;
            
            if (hasFocus())
                if (Greenfoot.isKeyDown("control"))
                {
                    if (cDown && !pressedC)
                    {
                        if (selectingPivot != -1 && selectingPivot != caret)
                            textTransfer.setClipboardContents(text.substring(Math.min(caret, selectingPivot), Math.max(caret, selectingPivot)));
                        pressedC = true;
                    }
                    if (!readOnly)
                    {
                        if (vDown && !pressedV)
                        {
                            deleteSelected();
                            String s = textTransfer.getClipboardContents();
                            for (int i = 0; i < s.length(); i++)
                                if ((s.charAt(i) == '\n' || (s.charAt(i) >= 32 && s.charAt(i) < 127)) && (maxLength == -1 || text.length() < maxLength) && (accept.equals("") || accept.contains(s.substring(i, i + 1)) && !dontAccept.contains(s.substring(i, i + 1))))
                                {
                                    text = text.substring(0, caret) + s.charAt(i) + text.substring(caret);
                                    changed = true;
                                    // Increment caret pass inserted character.
                                    caret++;
                                }
                            pressedV = true;
                        }
                        if (xDown && !pressedX)
                        {
                            if (selectingPivot != -1 && selectingPivot != caret)
                            {
                                textTransfer.setClipboardContents(text.substring(Math.min(caret, selectingPivot), Math.max(caret, selectingPivot)));
                                deleteSelected();
                            }
                            pressedX = true;
                        }
                    }
                }
        }
    }
    
    private void handleMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mousePressed(this))
            // Pressed somewhere in the text.
            if (mouse.getX() - (getX() - size.getX() / 2) < size.getX() - (strs.size() * font.getSize() > size.getY() ? SCROLL_WIDTH - 3 : 0))
            {
                if (focusable) {
                    clickedAt = new Point(mouse.getX() - (getX() - (int)size.getX() / 2), mouse.getY() - (getY() - (int)size.getY() / 2));
                    
                    handleForSelecting();
                    
                    lastCursorX = null;
                    
                    selecting = true;
                }
            }
            else
            {
                int scroll = (int)(scrollValue / (double)(font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3) * (size.getY() - scrollBar));
                // Pressed on scroll bar.
                if (strs.size() * font.getSize() > (int)size.getY() && mouse.getX() - (getX() - size.getX() / 2) > (int)size.getX() - 15 && mouse.getY() - (getY() - (int)size.getY() / 2) > scroll && mouse.getY() - (getY() - (int)size.getY() / 2) < scroll + scrollBar)
                {
                    draggingScrollBar = true;
                    mouseDrag_yOffsetOnScrollBar = mouse.getY() - (getY() - (int)size.getY() / 2) - scroll;
                }
            }
        if (focusable && Greenfoot.mouseClicked(this)) {
            if ((clickCount = mouse.getClickCount()) >= 2)
                clickedAt = new Point(mouse.getX() - (getX() - (int)size.getX() / 2), mouse.getY() - (getY() - (int)size.getY() / 2));
        }
        else
            clickCount = 0;
        
        if (Greenfoot.mouseMoved(null) || Greenfoot.mouseDragged(null))
            lastMouse.setLocation(mouse.getX(), mouse.getY());
        
        // Listen to end dragging of scroll bar and selecting.
        if (Greenfoot.mouseClicked(null) || Greenfoot.mouseDragEnded(null))
        {
            draggingScrollBar = false;
            selecting = false;
        }
        
        if (selecting && clickedAt == null && blink % 6 == 0)
        {
            clickedAt = new Point((int)lastMouse.getX() - (getX() - (int)size.getX() / 2), (int)lastMouse.getY() - (getY() - (int)size.getY() / 2));
            if (selectingPivot == -1)
                selectingPivot = caret;
        }
        
        if (Greenfoot.mouseDragged(null))
            if (draggingScrollBar)
            {
                int topOfScrollBar = (mouse.getY() - (getY() - (int)size.getY() / 2)) - mouseDrag_yOffsetOnScrollBar;
                if (topOfScrollBar < 0)
                    topOfScrollBar = 0;
                else if (mouse.getY() - (getY() - (int)size.getY() / 2) + (scrollBar - mouseDrag_yOffsetOnScrollBar) > size.getY())
                    topOfScrollBar = (int)size.getY() - scrollBar;
                scrollValue = (int)(topOfScrollBar / (double)(size.getY() - scrollBar) * (font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3));
            }
    }
    
    private boolean deleteSelected()
    {
        if (selectingPivot != -1)
        {
            text = text.substring(0, Math.min(caret, selectingPivot)) + text.substring(Math.max(caret, selectingPivot));
            changed = true;
            if (selectingPivot < caret)
                caret = selectingPivot;
            selectingPivot = -1;
            return true;
        }
        return false;
    }
    
    private void handleInput()
    {
        String s = Greenfoot.getKey();
        if (s != null && s.length() == 1 && treatAllAsUpperCase)
            s = s.toUpperCase();
        
        if (s != null && !readOnly && s.charAt(0) >= 32 && s.charAt(0) <= 127)
        {
            if (s.charAt(0) == 127)
            {
                if (!deleteSelected())
                    if (caret < text.length())
                    {
                        // Remove character to left of caret position.
                        text = text.substring(0, ++caret - 1) + text.substring(caret);
                        changed = true;
                        // Decrement caret to position of removed character.
                        caret--;
                        // Reset caret blinking, to show solid.
                        blink = 1;
                    }
            }
            else if (s.equals("backspace"))// || s.equals("\\"))
            {
                if (!deleteSelected())
                    // If there is something to the left of the caret.
                    if (caret > 0)
                    {
                        // Remove character to left of caret position.
                        text = text.substring(0, caret - 1) + text.substring(caret);
                        changed = true;
                        // Decrement caret to position of removed character.
                        caret--;
                        // Reset caret blinking, to show solid.
                        blink = 1;
                    }
            }
            else if (s.equals("escape"))// || s.equals("`"))
                removeFocus();
            else
            {
                // Substitute names for their characters.
                if (s.equals("space"))
                    s = " ";
                else if (s.equals("enter") && !password)
                    s = "\n";
                
                int newLength = text.length();
                if (selectingPivot != -1)
                    newLength -= Math.abs(caret - selectingPivot);
                
                if (!Greenfoot.isKeyDown("control")) // Attempt to accomodate for behavior on Gallery.
                // Insert character, if it is a character, text is not at max length, the character is among accepted, and the character is not among unaccepted.
                if (s.length() == 1 && (maxLength == -1 || newLength < maxLength) && (accept.equals("") || accept.contains(s)) && !dontAccept.contains(s))
                {
                    if (selectingPivot != -1)
                    {
                        text = text.substring(0, Math.min(caret, selectingPivot)) + text.substring(Math.max(caret, selectingPivot));
                        
                        if (selectingPivot < caret)
                            caret = selectingPivot;
                        selectingPivot = -1;
                    }
                    
                    // At index of caret.
                    text = text.substring(0, caret) + s + text.substring(caret);
                    changed = true;
                    // Increment caret pass inserted character.
                    caret++;
                }
            }
        }
    }
    
    private void handleArrowKeys()
    {
        Graphics2D g = (new GreenfootImage(1, 1)).getAwtImage().createGraphics();
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        
        if (Greenfoot.isKeyDown("up"))
        {
            if (++presses[0] == 25)
            {
                if (caret >= strs.get(0).length())
                {
                    int l = 0;
                    for (int i = 0; i < strs.size(); i++)
                    {
                        if (l + strs.get(i).length() > caret)
                        {
                            int cur;
                            if (lastCursorX == null)
                            {
                                cur = 4 + fm.stringWidth(strs.get(i).substring(0, caret - l));
                                lastCursorX = cur;
                            }
                            else
                                cur = lastCursorX;
                            clickedAt = new Point(cur, i * font.getSize() - scrollValue);
                            handleForSelecting();
                            
                            blink = 1;
                            break;
                        }
                        l += strs.get(i).length();
                    }
                }
                else
                    caret = 0;
                presses[0] = 20;
            }
        }
        else
        {
            if (presses[0] > 0 && presses[0] < 20)
            {
                if (!strs.isEmpty() && caret >= strs.get(0).length())
                {
                    int l = 0;
                    for (int i = 0; i < strs.size(); i++)
                    {
                        if (l + strs.get(i).length() > caret)
                        {
                            int cur;
                            if (lastCursorX == null)
                            {
                                cur = 4 + fm.stringWidth(strs.get(i).substring(0, caret - l));
                                lastCursorX = cur;
                            }
                            else
                                cur = lastCursorX;
                            clickedAt = new Point(cur, i * font.getSize() - scrollValue);
                            handleForSelecting();
                            
                            blink = 1;
                            break;
                        }
                        l += strs.get(i).length();
                    }
                }
                else
                    caret = 0;
            }
            presses[0] = 0;
        }
        
        if (Greenfoot.isKeyDown("down"))
        {
            if (++presses[1] == 25)
            {
                int l = 0;
                for (int i = 0; i < strs.size(); i++)
                {
                    if (l + strs.get(i).length() > caret)
                    {
                        int cur;
                        if (lastCursorX == null)
                        {
                            cur = 4 + fm.stringWidth(strs.get(i).substring(0, caret - l));
                            lastCursorX = cur;
                        }
                        else
                            cur = lastCursorX;
                        clickedAt = new Point(cur, (i + 2) * font.getSize() - scrollValue - 2);
                        handleForSelecting();
                        blink = 1;
                        break;
                    }
                    l += strs.get(i).length();
                }
                presses[1] = 20;
            }
        }
        else
        {
            if (presses[1] > 0 && presses[1] < 20)
            {
                int l = 0;
                for (int i = 0; i < strs.size(); i++)
                {
                    if (l + strs.get(i).length() > caret)
                    {
                        int cur;
                        if (lastCursorX == null)
                        {
                            cur = 4 + fm.stringWidth(strs.get(i).substring(0, caret - l));
                            lastCursorX = cur;
                        }
                        else
                            cur = lastCursorX;
                        clickedAt = new Point(cur, (i + 2) * font.getSize() - scrollValue - 2);
                        handleForSelecting();
                        blink = 1;
                        break;
                    }
                    l += strs.get(i).length();
                }
            }
            presses[1] = 0;
        }
        g.dispose();
        
        if (Greenfoot.isKeyDown("left"))
        {
            if (++presses[2] == 25)
            {
                if (caret > 0)
                {
                    handleForSelecting();
                    caret--;
                }
                blink = 1;
                presses[2] = 20;
            }
        }
        else
        {
            if (presses[2] > 0 && presses[2] < 20)
            {
                if (caret > 0)
                {
                    handleForSelecting();
                    caret--;
                }
                blink = 1;
            }
            presses[2] = 0;
        }
        
        if (Greenfoot.isKeyDown("right"))
        {
            if (++presses[3] == 25)
            {
                if (caret < text.length())
                {
                    handleForSelecting();
                    caret++;
                }
                blink = 1;
                presses[3] = 20;
            }
        }
        else
        {
            if (presses[3] > 0 && presses[3] < 20)
            {
                if (caret < text.length())
                {
                    handleForSelecting();
                    caret++;
                }
                blink = 1;
            }
            presses[3] = 0;
        }
    }
    
    private void handleForSelecting()
    {
        if (Greenfoot.isKeyDown("shift"))
        {
            if (selectingPivot == caret)
                selectingPivot = -1;
            else if (selectingPivot == -1)
                selectingPivot = caret;
        }
        else
            selectingPivot = -1;
    }
    
    /**
     * Called when gains focus.<p>
     * Releases Greenfoot.getKey().
     */
    protected void gainedFocus()
    {
        Greenfoot.getKey();
    }
    
    /**
     * Get the contents of this TextBox.
     * @return The text in this TextBox.
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * Set the text for this TextBox.
     * @param str The text to be in this TextBox.
     */
    public void setText(String str)
    {
        text = str;
        changed = true;
        caret = str.length();
        selectingPivot = -1;
        lastCursorX = null;
        scrollValue = 0;
        drawFunction.paint();
    }
    
    /**
     * Set the text for this TextBox to "".
     */
    public void clear()
    {
        setText("");
    }
    
    /**
     * Set String of characters that will be allowed as input.
     * @param s String of characters that will be allowed as input. Having an empty String will allow all characters to be inputed.
     */
    public void acceptOnly(String s)
    {
        accept = s;
    }
    
    /**
     * Set String of characters that will not be allowed as input.
     * @param s String of characters that will not be allowed as input.
     */
    public void dontAccept(String s)
    {
        dontAccept = s;
    }
    
    /**
     * Set a limit for how long the text can be.
     * @param m The limit for how many characters the text can include. -1 will allow unlimited.
     */
    public void setMaxLength(int m)
    {
        maxLength = m;
    }
    
    /**
     * Set the width and height of this TextBox.
     * @param s The width and height for this TextBox.
     */
    public void setSize(Point s)
    {
        size = s;
        drawFunction.resized();
    }
    
    /**
     * Set whether the TextBox will accept input.
     * @param readOnly Whether the TextBox will accept input.
     */
    public void setReadOnly(boolean readOnly)
    {
        this.readOnly = readOnly;
    }
    
    public void setFocusable(boolean focusable)
    {
        this.focusable = focusable;
    }
    
    /**
     * Determine whether this TextBox will accept input.
     * @return Whether this TextBox accepts input.
     */
    public boolean isReadOnly()
    {
        return readOnly;
    }
    
//     /**
//      * Get the Color this TextBox will be painting its text as.
//      * @return The Color this TextBox will be painting its text as.
//      */
//     public Color getTextColor()
//     {
//         return textColor;
//     }
//     
//     /**
//      * Change the Color this TextBox will be painting its text as.
//      * @param c The Color this TextBox will be painting its text as.
//      */
//     public void setTextColor(Color c)
//     {
//         textColor = c;
//     }
    
    /**
     * Get the Color of the blinking cursor.
     * @return The Color of the blinking cursor.
     */
    public Color getCursorColor()
    {
        return blinkColor;
    }
    
    /**
     * Change the Color of the blinking cursor.
     * @param c The new Color of the blinking cursor.
     */
    public void setCursorColor(Color c)
    {
        blinkColor = c;
    }
    
    /**
     * Get the Color outlining the TextBox when it has focus.
     * @return The Color outlining the TextBox when it has focus.
     */
    public Color getOutlineColor()
    {
        return activeColor;
    }
    
    /**
     * Change the Color outlining the TextBox when it has focus.
     * @param c The new Color outlining the TextBox when it has focus.
     */
    public void setOutlineColor(Color c)
    {
        activeColor = c;
    }
    
    /**
     * Get the Color of this TextBox's scroll bar.
     * @return The Color of this TextBox's scroll bar.
     */
    public Color getScrollBarColor()
    {
        return scrollColor;
    }
    
    /**
     * Change the Color of this TextBox's scroll bar.
     * @param c The new Color of this TextBox's scroll bar.
     */
    public void setScrollBarColor(Color c)
    {
        scrollColor = c;
    }
    
    /**
     * Get the Color of this TextBox's background.
     * @return The Color of this TextBox's background.
     */
    public Color getBackgroundColor()
    {
        return backColor;
    }
    
    /**
     * Change the Color of this TextBox's background.
     * @param c The new Color of this TextBox's background.
     */
    public void setBackgroundColor(Color c)
    {
        backColor = c;
    }
    
    /**
     * Get the Color that is used to display message. Default: Color.GRAY
     * @return The Color that is used to display message.
     */
    public Color getMessageColor()
    {
        return messageColor;
    }
    
    /**
     * Change the Color that is used to display message.
     * @param c The new Color that is used to display message.
     * @see setMessage(String)
     */
    public void setMessageColor(Color c)
    {
        if (c != null)
            messageColor = c;
    }
    
    /**
     * Sets a message to display when this TextBox is not active and has no text.
     * <p>
     * Will be shown in the Color designated for message.
     * @param m The message to display.
     * @see setMessageColor(Color)
     */
    public void setMessage(String m)
    {
        if (m != null)
            message = m;
    }
    
    /**
     * Get the message that gets displayed when this TextBox is not active and has no text.
     * @return The message that gets displayed when this TextBox is not active and has no text.
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * Set if this TextBox will display its text as password characters.
     * @param p If this TextBox will display its text as password characters.
     */
    public void setAsPassword(boolean p)
    {
        password = p;
    }
    
    /**
     * Check if this TextBox will display its text as password characters.
     * @return Whether this TextBox will display its text as password characters.
     */
    public boolean isPassword()
    {
        return password;
    }
    
    public boolean hasChanged()
    {
        if (changed)
        {
            changed = false;
            return true;
        }
        return false;
    }
    
    private class DrawFunction
    {
        private FontMetrics fm;
        
        // How many characters have been drawn so far.
        private int count;
            
        // Line where caret is within.
        private int caretLine;
        
        // Current constructed line of text to be drawn.
        private String current = "";
        
        // Pixel x coordinate for caret.
        private int caretX = 0;
        
        private GreenfootImage pic;
        
        public DrawFunction()
        {
            resized();
        }
        
        public void paint()
        {
            pic.clear();
            pic.setColor(backColor);
            pic.fill();
            
            pic.setFont(font);
            Graphics2D g = pic.getAwtImage().createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setFont(font);
            fm = g.getFontMetrics();
            
            // Clear lines.
            strs.clear();
            
            // Make copy of text.
            String remainingBeforeN = text + " ";
            
            // If password status, substitute characters for password character.
            if (password)
            {
                String s_password = "";
                for (int i = 0; i < text.length(); i++)
                    s_password += "" + (char)8226;
                remainingBeforeN = s_password + " ";
            }
            
            // If text is empty and do not have focus, will draw message (if it contains anything).
            boolean showingMessage = message != null && text.isEmpty();// && !hasFocus();
            if (showingMessage)
                remainingBeforeN = message + " ";
            
            // Break up text according to new line characters.
            String remainingAfterN = "";
            if (remainingBeforeN.indexOf("\n") != -1)
            {
                remainingAfterN = remainingBeforeN.substring(remainingBeforeN.indexOf("\n") + 1);
                remainingBeforeN = remainingBeforeN.substring(0, remainingBeforeN.indexOf("\n")) + " ";
            }
            
            // Current constructed line of text to be drawn.
            current = "";
            
            // How many characters have been drawn so far.
            count = 0;
            
            // Line where caret is within.
            caretLine = -1;
            
            caretX = 0;
            
            // While still have text left.
            while (!remainingBeforeN.isEmpty() || !remainingAfterN.isEmpty())
            {
                // While have text left before a new line character.
                while (!remainingBeforeN.isEmpty())
                {
                    // Width of the currently looked at String.
                    int currentWidth = fm.stringWidth(current);
                    
                    // The next string to be looked at, to try to add to current, and its width.
                    String next = remainingBeforeN.substring(0, remainingBeforeN.indexOf(" ") + 1);
                    int nextWidth = fm.stringWidth(next);
                    
                    boolean needToUpdateRemaining = true;
                    
                    // If next string's width added to current width reaches past right side bound.
                    if (currentWidth + nextWidth > size.getX() - SCROLL_WIDTH - 3)
                    {
                        if (current.isEmpty())
                            for (int i = 1; i <= next.length(); i++)
                            {
                                if (fm.stringWidth(remainingBeforeN.substring(0, i)) > size.getX() - SCROLL_WIDTH - 3)
                                {
                                    current = remainingBeforeN.substring(0, i - 1);
                                    remainingBeforeN = remainingBeforeN.substring(i - 1);
                                    needToUpdateRemaining = false;
                                    break;
                                }
                            }
                        
                        addCurrent_drawHighlight_rememberCaret();
                        
                        handleCaret_finishCurrent(showingMessage);
                    }
                    
                    // If haven't already updated remainings.
                    if (needToUpdateRemaining)
                        // If the next String is bigger than bound.
                        if (current.isEmpty() && nextWidth > size.getX() - SCROLL_WIDTH - 3)
                        {
                            for (int i = 1; i <= next.length(); i++)
                            {
                                if (fm.stringWidth(next.substring(0, i)) > size.getX() - SCROLL_WIDTH - 3)
                                {
                                    current = next.substring(0, i - 1);
                                    remainingBeforeN = remainingBeforeN.substring(i - 1);
                                    break;
                                }
                            }
                        }
                        else
                        {
                            // Add next String to currently looked at string.
                            current += next;
                            // Pass over the visited next String.
                            remainingBeforeN = remainingBeforeN.substring(remainingBeforeN.indexOf(" ") + 1);
                        }
                }
                
                if (current.length() > 1 || current.charAt(0) != ' ' || !remainingAfterN.isEmpty() || !remainingAfterN.isEmpty())
                    addCurrent_drawHighlight_rememberCaret();
                
                handleCaret_finishCurrent(showingMessage);
                
                if (!remainingAfterN.isEmpty())
                    if (remainingAfterN.indexOf("\n") != -1)
                    {
                        remainingBeforeN = remainingAfterN.substring(0, remainingAfterN.indexOf("\n")) + " ";
                        remainingAfterN = remainingAfterN.substring(remainingAfterN.indexOf("\n") + 1);
                    }
                    else
                    {
                        remainingBeforeN = remainingAfterN + " ";
                        remainingAfterN = "";
                    }
            }
            
            // If still haven't found position for caret, place at end of text.
            if (clickedAt != null)
            {
                caret = text.length();
                clickedAt = null;
                
                caretLine = strs.size();
            }
            
            // If caret moved out of view, adjust scroll amount.
            if (oldCaret != caret && caretLine != -1)
            {
                // Y-coordinate relative to image, where the top of the line of text that the caret is within, is drawn.
                int y = font.getSize() * (caretLine - 1) + font.getSize() / 5 - scrollValue - 3;
                
                // If above top.
                if (y < 0)
                    scrollValue = font.getSize() * (caretLine - 1) + font.getSize() / 5 - 3;
                // If below bottom.
                else if (y + font.getSize() + 6 > size.getY())
                    scrollValue = font.getSize() * (caretLine - 1) + font.getSize() / 5 + font.getSize() - (int)size.getY() + 3;
            }
            
            // Listen for mouse scroll wheel.
            int n = scroller.getScroll();
            
            // If scroll bar present.
            if (strs.size() * font.getSize() > size.getY())
            {
                // If mouse is over this TextBox.
                if (mouseOverThis() && getWorld() != null)
                {
                    // Add scroll amount from scroll wheel.
                    scrollValue += n;
                    
                    // Adjust scroll amount if out of bounds.
                    if (scrollValue < 0)
                        scrollValue = 0;
                    else if (scrollValue > font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3)
                        scrollValue = font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3;
                }
                
                // Y-coordinate relatvie to image, of where top of scroll bar will be.
                int scroll = (int)(scrollValue / (double)(font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3) * (size.getY() - scrollBar));
                
                // Draw scroll bar.
                pic.setColor(scrollColor);
                pic.fillRect((int)size.getX() - SCROLL_WIDTH, scroll, SCROLL_WIDTH, scrollBar);
                pic.setColor(Color.BLACK);
                pic.drawRect((int)size.getX() - SCROLL_WIDTH, scroll, SCROLL_WIDTH, scrollBar);
            }
            
            // Adjust scroll amount if out of bounds.
            if (scrollValue < 0 || strs.size() * font.getSize() <= size.getY())
                scrollValue = 0;
            else if (scrollValue > font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3)
                scrollValue = font.getSize() * strs.size() + font.getSize() / 5 - (int)size.getY() + 3;
            
            // Draw lines of text.
            g.setColor(showingMessage ? messageColor : textColor);
            for (int i = 0; i < strs.size(); i++)
                g.drawString(strs.get(i), 4, font.getSize() * (i + 1) - scrollValue);
            g.dispose();
            
            // Draw blinking caret.
            if (blink > 0 && hasFocus())
                if (caretLine != -1 && caretX > 3)
                {
                    pic.setColor(blinkColor);
                    pic.fillRect(caretX, font.getSize() * (caretLine - 1) + font.getSize() / 5 - scrollValue, 2, font.getSize());
                }
                // If no text, has focus, draw caret bar blinking animation.
                else if (text.isEmpty())
                {
                    pic.setColor(blinkColor);
                    pic.fillRect(4, font.getSize() / 5 + 1, 2, font.getSize());
                }
            
            GreenfootImage p = getImage();
            p.clear();
            p.drawImage(pic, 3, 3);
            
            // Draw TextBox outer frame.
            p.setColor(borderColor);
            p.drawRect(2, 2, p.getWidth() - 5, p.getHeight() - 5);
            
            if (hasFocus())
            {
                p.setColor(activeColor);
                p.drawRect(1, 1, p.getWidth() - 3, p.getHeight() - 3);
                p.setColor(new Color(activeColor.getRed(), activeColor.getGreen(), activeColor.getBlue(), 100));
                p.drawRect(0, 0, p.getWidth() - 1, p.getHeight() - 1);
            }
            else
            {
                p.setColor(new Color(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), 100));
                p.drawRect(1, 1, p.getWidth() - 3, p.getHeight() - 3);
            }
            setImage(p);
        }
        
        private void addCurrent_drawHighlight_rememberCaret()
        {
            strs.add(current);
            
            if (selectingPivot != -1)
            {
                int minC = Math.min(selectingPivot, caret);
                int maxC = Math.max(selectingPivot, caret);
                if (maxC > count && minC < count + current.length())
                {
                    minC -= count;
                    if (minC < 0)
                        minC = 0;
                    maxC -= count;
                    if (maxC > current.length())
                        maxC = current.length();
                    int hBegin = fm.stringWidth(current.substring(0, minC)) + 4;
                    int hLength = fm.stringWidth(current.substring(minC, maxC));
                    pic.setColor(selectColor);
                    pic.fillRect(hBegin, font.getSize() * (strs.size() - 1) + font.getSize() / 5 - scrollValue, hLength, font.getSize());
                }
            }
            
            // Remember location for caret bar if is within this line of text.
            if (hasFocus() && caret >= count && caret < count + current.length())
            {
                caretX = fm.stringWidth(current.substring(0, caret - count)) + 4;
                caretLine = strs.size();
            }
        }
        
        private void handleCaret_finishCurrent(boolean showingMessage)
        {
            if (!showingMessage)
                // If looking to place caret and location is before this line, then set caret to beginning of text. (Only occur when iterating first line).
                if (clickedAt != null && clickedAt.getY() <= font.getSize() * (strs.size() - 1) - scrollValue)
                {
                    caret = 0;
                    clickedAt = null;
                    if (caret == selectingPivot)
                        selectingPivot = -1;
                    caretLine = strs.size();
                }
                
                // If looking to place caret within text, and is within current.
                else if (clickedAt != null && clickedAt.getY() > font.getSize() * (strs.size() - 1) - scrollValue && clickedAt.getY() <= font.getSize() * strs.size() - scrollValue)
                {
                    // If to the left of entire line of text.
                    if (clickedAt.getX() < 4 + fm.stringWidth(current.substring(0, 1)) / 2)
                    {
                        caret = count;
                        clickedAt = null;
                    }
                    else
                    {
                        // Step to the right, one character at a time.
                        for (int i = 1; i < current.length() - 1; i++)
                            // If to the left of index.
                            if (clickedAt.getX() >= 4 + fm.stringWidth(current.substring(0, i - 1)) + fm.stringWidth(current.substring(i - 1, i)) / 2 && clickedAt.getX() < 4 + fm.stringWidth(current.substring(0, i)) + fm.stringWidth(current.substring(i, i + 1)) / 2)
                            {
                                if (clickCount < 2 || (clickCount - 1) % 3 == 0)
                                    caret = count + i;
                                else if ((clickCount - 1) % 3 == 1) {
                                    int charType = getCharType(current.charAt(i - 1));
                                    int k;
                                    for (k = i - 2; k >= 0; k--)
                                        if (getCharType(current.charAt(k)) != charType)
                                            break;
                                    selectingPivot = count + k + 1;
                                    for (k = i; k < current.length(); k++)
                                        if (getCharType(current.charAt(k)) != charType)
                                            break;
                                    caret = count + k;
                                }
                                else if ((clickCount - 1) % 3 == 2) {
                                    selectingPivot = count;
                                    caret = count + current.length() - 1;
                                }
                                clickedAt = null;
                                break;
                            }
                        // If haven't found position for caret yet, place at end of this line of text.
                        if (clickedAt != null)
                        {
                            caret = count + current.length() - 1;
                            
                            // Since text has ' ' added to end for iterating, caret might get placed at end of it. (Occurs if last line).
                            if (caret > text.length())
                                caret = text.length();
                            
                            clickedAt = null;
                        }
                    }
                    if (caret == selectingPivot)
                        selectingPivot = -1;
                    // Record which line of text the caret position is in.
                    caretLine = strs.size();
                }
            
            // Update how many characters have been drawn.
            count += current.length();
            
            // Reset currently looked at String.
            current = "";
        }
        private int getCharType(char ch)
        {
            if (Character.isAlphabetic(ch))
                return 0;
            if (Character.isDigit(ch))
                return 1;
            return 2;
        }
        public void resized()
        {
            pic = new GreenfootImage((int)size.getX(), (int)size.getY());
            setImage(new GreenfootImage(pic.getWidth() + 6, pic.getHeight() + 6));
        }
    }
}