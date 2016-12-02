import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WaitActor extends UserInput
{
    private GameWorld world;
    private boolean isReady = false;
    private long lastAdded = System.currentTimeMillis();
    private WaitScreen waitScreen;
    private GifImage gif;
    private Thread thread = new Thread("gif updater thread"){
        public void run(){
            Score opponent = Server.reserveRoom(world.getScore());
            world.getScore().setRoomId(opponent.getRoomId());
            if(opponent != null && opponent.getName() != null){
                waitScreen.setOpponentScore(opponent);
                waitScreen.setNextScreen(null);
            }else{
                world.getScore().setFirst(true);
                while(!isReady){ 
                    opponent = Server.opponentArrived(world.getScore().getRoomId());
                    if(opponent.getName() != null){
                        isReady = true;
                        waitScreen.setOpponentScore(opponent);
                        waitScreen.setNextScreen(null);
                        break;
                    }else{
                        try{
                            Thread.sleep(500);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };

    public WaitActor(GameWorld world, String img){
        this.world = world;
        waitScreen = (WaitScreen) world.getScreen();
        gif = new GifImage(img);
    }
    
    public void act(){
        setImage(gif.getCurrentImage());
        if(thread.getState() == Thread.State.NEW){ 
            thread.start();
        }
    }
}
