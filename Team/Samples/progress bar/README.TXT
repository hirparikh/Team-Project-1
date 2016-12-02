PROJECT TITLE: Progress bar/Health Bar class

PURPOSE OF PROJECT: Re-usable class

VERSION or DATE: February 9, 2012

HOW TO START THIS PROJECT: The Bar class was created as a support class
    for program designers.  They only need create an sub-class of Actor
    called 'Bar' and copy the bar class code provided directly into that
    users bar class.  Then, the constructor would be called from the world
    or another class to instantiate a bar, and methods can be used to alter
    the appearance and change the value of the bar.
    
THE BAR CLASS CODE: The code for the Bar class is complete, in that there
    is no need to make any changes in the code itself.  However, creating
    sub-classes for it to produce different actions from the bars may be
    useful (one bar may track frames-per-second, while another could be 
    used to allow the user to adjust the speed of the scenario; all by
    having different act methods in the sub-classes).
    
AUTHOR: danpost

WHAT IS A BAR: A bar is a rectangular area the is long and narrow (usually 
    long horizontally, but may be rotated 90 degrees).  A portion of the
    bar is filled with a color (starting from one end; usually from the
    left, or the bottom). The percentage of the bar that is colored is
    calculated to be where the value falls between the minimum value and
    the maximum value.  Oftentimes, when the value gets too low (or for some
    instances, too high), the value is considered to be in a range where
    something is at risk.  The color of the bar can be made to change when
    the value is in the at-risk area.

USER INSTRUCTIONS: This project is not for the end-user.  It is for program
    designers who wish to take advantage of the fact that this is already
    prepared for there use.
    
------------------------------------------------------DOCUMENTATION----------------------------------------------------
TITLE:  Progress bar/Health bar class (Bar.class)
VERSION: 2.1
LAST MODIFIED: February 9, 2012
AUTHOR:  danpost
--------------------------------------------------------FIELDS---------------------------------------------------------


private    Color      backgroundColor	The background color of the entire object
						DEFAULT: Color(0, 0, 0, 0) (transparent)

private    int 	      barHeight		The height of the color portion of the bar.
						DEFAULT: 10

private    int 	      barWidth 		The width of the color portion of the bar.
						DEFAULT: 100

private    boolean    breakLow		This flag determines whether the 'safeColor' values are greater than the
					'dangerColor' values or not.  A 'true' value indicates low values are
					'dangerColor' values and high values are 'safeColor' values. A 'false' value
					reverses the colors.
						DEFAULT: true

private    int 	      breakPercent	The percentage amount that determines at what point the bar changes its color 							between 'safeColor' and 'dangerColor'. The value is determined by the percent
					of the range between the 'minimumValue' and the 'maximumValue'. When exactly at							the breakPercent value,	the bar will be the average of the two colors.
					NOTE: See 'private boolean usingBreakValue', which must be 'false' for this
					value to be in use.
						DEFAULT: 20

private    int 	      breakValue	The value at which the bar changes its color between 'safeColor' and 
					'dangerColor'.  When exactly at the breakValue, the bar will be the average of
					the two colors.
					NOTE: See 'private boolean usingBreakValue', which must be 'true' for this
					value to be in use.
						DEFAULT: 20

private    Color      dangerColor	The color of the bar while in the 'danger' range (see 'usingBreakValue').
						DEFAULT: Color.RED

private    float      fontSize		The font-size of the text.
						DEFAULT: 18.0f

private    int 	      maximumValue	The maximum value of the bar (the value at the right-most edge of the bar).
						DEFAULT: n/a (received via the constructor)

private    int 	      minimumValue	The minimum value of the bar (the value at the left-most edge of the bar).
						DEFAULT: 0 (zero)

private    String     referenceText	The title string (who or what the meter/bar is for). Any text that describes
					what the bar is used for (or an empty string).
						DEFAULT: n/a (received via the constructor)

private    Color      safeColor		The color of the bar while in the 'safe' range (see 'usingBreakValue').
						DEFAULT: Color.GREEN

private    Color      textColor		The color of all text and the frame of the bar itself.
						DEFAULT: Color.BLACK

private    String     unitOfMeasure	The unit of measure of the bar (any quantitative standard of measurement).
					Any text that gives meaning to the value (or an empty string).
						DEFAULT: n/a (received via the constructor)

protected  boolean    usingBreakValue	Flag that determines whether 'breakValue' (when 'true') or 'breakPercent' (when
					'false') is used in determining color changing between 'safeColor' and
					'dangerColor'.  This flag is set automatically; to 'true' when 'setBreakValue'
					is used, or to 'false' when 'setBreakPercent' is used.
						DEFAULT: false

private    int 	      value		The current value of the bar.
						DEFAULT: 0 (zero)

private    boolean    showTextualUnits	Flag thatdetermines whether or not the textual quantity of the bar is to show.
						DEFAULT: true

----------------------------------------------------CONSTRUCTOR--------------------------------------------------------

Bar(String referenceName, String unitOfMeasure, int initialValue, int maximumValue)

------------------------------------------------------METHODS----------------------------------------------------------


	VALUE:  public 	void 	add(int addAmt)				Adds 'addAmt' to 'value'.
	        public 	void 	subtract(int subAmt)			Subtracts 'subAmt' from 'value'.
	        public	int 	getValue()				Returns 'value'.
	        public 	void 	setValue(int newValue)			Sets the value of 'value' to 'newValue'.

	RANGE:  public 	int 	getMaximumValue()			Returns 'maximumValue'.
	        public 	void 	setMaximumValue(int maxValue)		Sets the value of 'maximumValue' to 'maxValue'.
	        public 	int 	getMinimumValue()			Returns 'minimumValue'.
	        public 	void 	setMinimumValue(int minValue)		Sets the value of 'minimumValue' to 'minValue'.


	 TEXT:  public 	String 	getReferenceName()			Returns 'referenceName'.
	        public 	void 	setReferenceName(String refName)	Sets the string 'referenceName' to 'refName'.
	        public 	String 	getUnitOfMeasure()			Returns 'unitOfMeasure'.
	        public 	void 	setUnitOfMeasure(String uom)		Sets the string 'unitOfMeasure' to 'uom'.
	        public 	Color 	getTextColor()				Returns 'textColor'.
	        public 	void 	setTextColor(Color cText)		Sets the color of 'textColor' to 'cText'.
	        public 	float 	getFontSize()				Returns 'fontSize'.
	        public 	void 	setFontSize(float size)			Sets the value of 'fontSize' to 'size'.

	  BAR:  public 	int 	getBarWidth()				Returns 'barWidth'.
	        public 	void 	setBarWidth(int barWide)		Sets the value of 'barWidth' to 'barWide'.
        	public 	int 	getBarHeight()				Returns 'barHeight'.
        	public 	void 	setBarHeight(int barHigh)		Sets the value of 'barHeight' to 'barHigh'.
        	public 	Color 	getBackgroundColor()			Returns 'backgroundColor'.
        	public 	void 	setBackgroundColor(Color cBack)		Sets the color of 'backgroundColor' to 'cBack'.
        	public 	Color 	getSafeColor()				Returns 'safeColor'.
        	public 	void 	setSafeColor(Color cSafe)		Sets the color of 'safeColor' to 'cSafe'.
        	public 	Color 	getDangerColor()			Returns 'dangerColor'.
        	public 	void 	setDangerColor(Color cDanger)		Sets the color of 'dangerColor' to 'cDanger'.

     BEHAVIOR:  public 	int 	getBreakPercent()			Returns 'breakPercent'.
        	public 	void 	setBreakPercent(int breakPct)		Sets the value of 'breakPercent' to 'breakPct'.
									Also, sets 'usingBreakValue' to false.
        	public 	int 	getBreakValue()				Returns 'breakValue'.
        	public 	void 	setBreakValue(int breakVal)		Sets the value of 'breakValue' to 'breakVal'.
									Also, sets 'usingBreakValue' to true.
        	public 	boolean getBreakLow()				Returns 'breakLow'.
        	public 	void 	setBreakLow(boolean brkLow)		Sets the value of 'breakLow' to 'brkLow'.
        	public 	boolean getUsingBreakValue()			Returns 'usingBreakValue'.
        	public 	boolean getShowTextualUnits()			Returns 'showTextualUnits'.
        	public 	void 	setShowTextualUnits(boolean show)	Sets the value of 'showTextualUnits' to 'show'.

-----------------------------------------------------------------------------------------------------------------------