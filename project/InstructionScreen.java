/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;

public class InstructionScreen extends Screen {
    
    private NameText nameText;
    private GreenfootSound sound;

    public InstructionScreen(GameWorld world) {
        super(world);
    }

    @Override
    public void setNextScreen(IScreen screen) {
        String name = nameText.getText();
        if(name != null && !name.trim().isEmpty()){
            sound.stop();
            Score score = new Score();
            score.setName(name);
            world.setScore(score);
            world.setScreen(ScreenFactory.getScreen(ScreenType.WAIT, world));
            clearScreen();
            world.getScreen().displayScreen();
        }
    }
    
    @Override
    public void setPreviousScreen() {
        StoryScreen screen = (StoryScreen)ScreenFactory.getScreen(ScreenType.STORY, world);
        screen.setPage(1);
        world.setScreen(screen);
        clearScreen();
        world.getScreen().displayScreen();
    }

    @Override
    public void displayScreen() {
        world.setBackground(new GreenfootImage("S5.jpg"));
        nameText=new NameText();
        world.addObject(nameText, 525, 430);
        world.addObject(new BackButton(world), 75, 485);
        world.addObject(new PlayButton(world), 925, 485); 
    }

    @Override
    public void clearScreen() {
        world.removeObjects(world.getObjects(Actor.class));
    }
    
    public void setSound(GreenfootSound sound){
        this.sound = sound;
    }

}
