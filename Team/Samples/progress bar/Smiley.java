import greenfoot.*;

public class Smiley extends Actor
{
    public void act() 
    {
        if (!getWorld().getObjects(GameOver.class).isEmpty()) return;
        if (Greenfoot.mouseClicked(this))
        {
            BackGround bg = (BackGround) getWorld();
//             Bar bar = (Bar) bg.getObjects(Bar.class).get(0);
            int change = Greenfoot.getRandomNumber(2) * 2 - 1;
            setImage((change == -1) ? "smiley5.png" : "smiley4.png");
            bg.bar.add(change);
        }
    }    
}
