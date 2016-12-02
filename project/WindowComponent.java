import greenfoot.World;

/**
 * WindowComponent
 * <p>
 * A component to be used within a Window.<p>
 * Handles horizontal and vertical justification (beginning, center, and end) within a cell of a Container.
 * 
 * @author Taylor Born
 * @version February 2013 - March 2014
 */
public abstract class WindowComponent extends GUI_Component
{
    public static final int BEGINNING = 0, CENTER = 1, END = 2;

    private int justifyHorizontal = CENTER, justifyVertical = CENTER;
    
    private int lastKnownWidth;
    private int lastKnownHeight;
    
    /**
     * Listener to be called by Container.
     * @return Whether this WindowComponent's GUI width or height has changed since last call.
     */
    public boolean hasChangedGUISize()
    {
        int w = getGUIWidth();
        int h = getGUIHeight();
        boolean changed = false;
        if (w != lastKnownWidth) {
            lastKnownWidth = w;
            changed = true;
        }
        if (h != lastKnownHeight) {
            lastKnownHeight = h;
            changed = true;
        }
        return changed;
    }

    /**
     * Called within Container to set this WindowComponent's location within World to be within a cell of grid/table structure.<p>
     * @param leftSide The x-coordinate of the left side of the cell assigned to.
     * @param xRange The width in pixels of the cell assigned to.
     * @param topSide The y-coordinate of the top side of the cell assigned to.
     * @param yRange The height in pixels of the cell assigned to.
     * @see addToWorldInContainerCell(World, int, int, int, int)
     */
    public void setLocationInContainerCell(int leftSide, int xRange, int topSide, int yRange)
    {
        setLocation(leftSide + getJustifyX(xRange), topSide + getJustifyY(yRange));
    }
    
    /**
     * Called within Container to add this WindowComponent to the World within a cell of grid/table structure.<p>
     * @param leftSide The x-coordinate of the left side of the cell assigned to.
     * @param xRange The width in pixels of the cell assigned to.
     * @param topSide The y-coordinate of the top side of the cell assigned to.
     * @param yRange The height in pixels of the cell assigned to.
     * @see setLocationInContainerCell(World, int, int, int, int)
     */
    public void addToWorldInContainerCell(World world, int leftSide, int xRange, int topSide, int yRange)
    {
        world.addObject(this, leftSide + getJustifyX(xRange), topSide + getJustifyY(yRange));
    }
    
    private int getJustifyX(int range)
    {
        switch (justifyHorizontal) {
            case BEGINNING: return getGUIWidth() / 2;
            case END:       return range - getGUIWidth() / 2;
            default:        return range / 2;
        }
    }
    private int getJustifyY(int range)
    {
        switch (justifyVertical) {
            case BEGINNING: return getGUIHeight() / 2;
            case END:       return range - getGUIHeight() / 2;
            default:        return range / 2;
        }
    }
    
    /**
     * Justify this WindowComponent horizontally within its Container's column 
     */
    public void justifyHorizontally(int j)
    {
        justifyHorizontal = j;
    }
    
    /**
     * Justify this WindowComponent vertically within its Container's row 
     */
    public void justifyVertically(int j)
    {
        justifyVertical = j;
    }
}