/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

public class ScreenFactory {
    private static IScreen startScreen;
    private static IScreen storyScreen;
    private static IScreen instructionScreen;
    private static IScreen waitScreen;
    private static IScreen gameScreen;
    private static IScreen winScreen;
    private static IScreen loseScreen;
    private static IScreen leaderboardScreen;
    
    public static IScreen getScreen(ScreenType screenType, GameWorld world) {
        if (screenType == null) {
            return null;
        }
        IScreen screen;
        switch (screenType) {
        case START:
            screen = getStartScreen(world);
            break;
        case STORY:
            screen = getStoryScreen(world);
            break;
        case INSTRUCTION:
            screen = getInstructionScreen(world);
            break;
        case WAIT:
            screen = getWaitScreen(world);
            break;
        case GAME:
            screen = getGameScreen(world);
            break;
        case WIN:
            screen = getWinScreen(world);
            break;
        case LOSE:
            screen = getLoseScreen(world);
            break;
        case LEADERBOARD:
            screen = getLeaderBoardScreen(world);
            break;
        default:
            screen = null;
            break;
        }
        return screen;
    }
    
    private static IScreen getStartScreen(GameWorld world){
        if(startScreen == null){
            startScreen = new StartScreen(world);
        }
        return startScreen;
    }
    
    private static IScreen getStoryScreen(GameWorld world){
        if(storyScreen == null){
            storyScreen = new StoryScreen(world);
        }
        return storyScreen;
    }
    
    private static IScreen getInstructionScreen(GameWorld world){
        if(instructionScreen == null){
            instructionScreen = new InstructionScreen(world);
        }
        return instructionScreen;
    }
    
    private static IScreen getWaitScreen(GameWorld world){
        if(waitScreen == null){
            waitScreen = new WaitScreen(world);
        }
        return waitScreen;
    }
    
    private static IScreen getGameScreen(GameWorld world){
        if(gameScreen == null){
            gameScreen = new GameScreen(world);
        }
        return gameScreen;
    }
    
    private static IScreen getWinScreen(GameWorld world){ 
        if(winScreen == null){
            winScreen = new WinScreen(world);
        }
        return winScreen;
    }
    
    private static IScreen getLoseScreen(GameWorld world){
        if(loseScreen == null){
            loseScreen = new LoseScreen(world);
        }
        return loseScreen;
    }
    
    private static IScreen getLeaderBoardScreen(GameWorld world){
        if(leaderboardScreen == null){
            leaderboardScreen = new LeaderBoardScreen(world);
        }
        return leaderboardScreen;
    }
}
