import greenfoot.*;

public class InstructionScreen extends Screen {
    
    private NameText nameText;

    public InstructionScreen(GameWorld world) {
        super(world);
    }

    @Override
    public void setNextScreen(IScreen screen) {
        String name = nameText.getText();
        if(name != null && !name.trim().isEmpty()){
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

}
