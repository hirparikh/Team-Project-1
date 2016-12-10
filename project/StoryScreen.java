/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;

public class StoryScreen extends Screen {

    private int page = 1;
    
    public StoryScreen(GameWorld world) {
        super(world);
    }
    
    public void setPage(int page){
        this.page = page;
    }

    @Override
    public void setNextScreen(IScreen screen) {
        if(page < 3){
            page++;
            clearScreen();
            displayScreen();
        }else{
            world.setScreen(ScreenFactory.getScreen(ScreenType.INSTRUCTION, world));
            clearScreen();
            world.getScreen().displayScreen();
        }
    }
    
    @Override
    public void setPreviousScreen() {
    }

    @Override
    public void displayScreen() {
        String background;
        switch(page){
            case 1:
                background = "S2.jpg";
                break;
            case 2:
                background = "S3.jpg";
                break;
            case 3:
                background = "S4.jpg";
                break;
            default:
                background = null;
                break;
        }
        world.setBackground(new GreenfootImage(background));
        world.addObject(new NextButton(world), 910,520);
    }   

    @Override
    public void clearScreen() {
        world.removeObjects(world.getObjects(Actor.class));
    }
}
