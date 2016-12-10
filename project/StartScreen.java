/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;

public class StartScreen extends Screen {

    public StartScreen(GameWorld world) {
        super(world);
    }

    @Override
    public void setNextScreen(IScreen screen) {
        StoryScreen storyScreen = (StoryScreen)ScreenFactory.getScreen(ScreenType.STORY, world);
        storyScreen.setPage(1); 
        world.setScreen(storyScreen);
        //world.setScreen(ScreenFactory.getScreen(ScreenType.LEADERBOARD, world)); 
        //GameScreen gameScreen = (GameScreen)ScreenFactory.getScreen(ScreenType.GAME, world);
        //world.setScreen(gameScreen);
        clearScreen();
        world.getScreen().displayScreen(); 
    }
    
    @Override
	public void setPreviousScreen() {
	}

    @Override
    public void displayScreen() {
        world.setBackground(new GreenfootImage("S1.jpg"));
        world.addObject(new StartButton(world), 515,470);
    }

    @Override
    public void clearScreen() {
        world.removeObjects(world.getObjects(Actor.class));
    }
}
