/**
 * @author dthes
 *
 */
public interface IScreen {
	public void setNextScreen(IScreen screen);
	
	public void setPreviousScreen();

	public void displayScreen();

	public void clearScreen();
}
