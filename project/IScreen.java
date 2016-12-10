/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

public interface IScreen {
    
	public void setNextScreen(IScreen screen);
	public void setPreviousScreen();
	public void displayScreen();
	public void clearScreen();
}
