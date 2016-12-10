/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WaitActor extends UserInput
{
    private boolean isReady = false;
    private GifImage gif;
    private IServer server = new Server();
    private Thread thread = new Thread("gif updater thread"){
        public void run(){
            Score opponent = server.reserveRoom(world.getScore());
            world.getScore().setRoomId(opponent.getRoomId());
            if(opponent != null && opponent.getName() != null){
                ((WaitScreen)world.getScreen()).setOpponentScore(opponent);
                world.getScreen().setNextScreen(null);
            }else{
                world.getScore().setFirst(true);
                while(!isReady){ 
                    opponent = server.opponentArrived(world.getScore().getRoomId());
                    if(opponent.getName() != null){
                        isReady = true;
                        ((WaitScreen)world.getScreen()).setOpponentScore(opponent);
                        world.getScreen().setNextScreen(null);
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
        super(world);
        gif = new GifImage(img);
    }
    
    public void act(){
        setImage(gif.getCurrentImage());
        if(thread.getState() == Thread.State.NEW){ 
            thread.start();
        }
    }
}
