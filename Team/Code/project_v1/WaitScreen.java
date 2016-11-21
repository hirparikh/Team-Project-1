import greenfoot.*;

public class WaitScreen extends Screen 
{
    private Score opponentScore;
    
    public WaitScreen(GameWorld world) {
        super(world);
    }
    
    @Override
    public void setNextScreen(IScreen screen) {
        GameScreen gameScreen = (GameScreen)ScreenFactory.getScreen(ScreenType.GAME, world);
        ScoreUpdate scoreUpdate = new ScoreUpdate(world, gameScreen);
        scoreUpdate.attach(gameScreen);
        gameScreen.setOpponentScore(opponentScore);
        gameScreen.setScoreSubject(scoreUpdate);
        world.setScreen(gameScreen);
        clearScreen();
        gameScreen.displayScreen();
    }
    
    public void setOpponentScore(Score opponentScore){
        this.opponentScore = opponentScore;
    }    
    
    @Override
    public void setPreviousScreen() {
    }

    @Override
    public void displayScreen() {
        world.setBackground(new GreenfootImage("S6.jpg"));
        world.addObject(new WaitActor(world, "hourglass.gif"), 185, 350);
    }

    @Override
    public void clearScreen() {
        world.removeObjects(world.getObjects(Actor.class));
    }
}
