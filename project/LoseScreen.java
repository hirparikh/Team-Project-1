/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;

public class LoseScreen extends Screen {

	public LoseScreen(GameWorld world) {
		super(world);
	}

	@Override
	public void setNextScreen(IScreen screen) {
		world.setScreen(ScreenFactory.getScreen(ScreenType.LEADERBOARD, world));
        clearScreen();
        world.getScreen().displayScreen();
	}
	
	@Override
	public void setPreviousScreen() {
	    world.setScreen(ScreenFactory.getScreen(ScreenType.START, world));
        clearScreen();
        world.getScreen().displayScreen();
	}

	@Override
	public void displayScreen() {
		world.setBackground(new GreenfootImage("S8_2.jpg"));
        world.addObject(new PlayAgain(world), 640, 535);
        world.addObject(new LeaderBoardButton(world), 840, 535);
	}

	@Override
	public void clearScreen() {
		world.removeObjects(world.getObjects(Actor.class));
	}

}
