import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
/**
 * Write a description of class MyWorld2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld2 extends World
{
    String name;
    //private GreenfootSound gfs; 

    /**
     * Constructor for objects of class MyWorld2.
     * 
     */
    public MyWorld2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
       // gfs = new GreenfootSound("sounds/Adrenaline.mp3");
       // gfs.playLoop();
        setBackground(new GreenfootImage("back_image1.jpg"));
        prepare();
    }
    
    public void prepare(){
        //Add the scoreboard object
        ScoreBoard scoreboard = new ScoreBoard(this);
        addObject(scoreboard,120,70);
        scoreboard.updateScore(0, 0,false,true);
        scoreboard.printScore();
        

        //Add timer object
        Timer timer = new Timer(180);
        addObject(timer,350,70);

        //Add the treasure chest object
        TreasureChest treasurechest = new TreasureChest();
        addObject(treasurechest,609,90);

        //MST mst = new MST(scoreboard, treasurechest);
        MST mst = new MST(scoreboard, treasurechest);

        //Add the nodes to the world
        Node n1= new Node(1,mst);
        Node n2 = new Node(2,mst);
        Node n3 = new Node(3,mst);
        Node n4 = new Node(4,mst);
        Node n5 = new Node(5,mst);
        Node n6 = new Node(6,mst);
        Node n7 = new Node(7,mst);

        addObject(n1,233,404);
        addObject(n2,415,510);
        addObject(n3,232,193);
        addObject(n4,632,391);
        addObject(n5,446,228);
        addObject(n6,686,535);
        addObject(n7,635,207);

        //Add the edges to the world
        Edge e1 = new Edge(1, n2, n6, 11, mst);
        n2.getEdgeSet().add(e1);
        n6.getEdgeSet().add(e1);
        addObject(e1,((n2.getX()+n6.getX())/2),((n2.getY()+n6.getY())/2)+20);
        e1.turn(-85);
        e1.setScaleImage(false);
        Edge e2 = new Edge(2, n1, n2, 6, mst);
        n2.getEdgeSet().add(e2);
        n1.getEdgeSet().add(e2);
        addObject(e2,((n1.getX()+n2.getX())/2),((n1.getY()+n2.getY())/2)+20);
        e2.turn(-55);
        e2.setScaleImage(false);
        Edge e3 = new Edge(3, n4, n5, 7, mst);
        n5.getEdgeSet().add(e3);
        n4.getEdgeSet().add(e3);
        addObject(e3,((n4.getX()+n5.getX())/2),((n4.getY()+n5.getY())/2)+20);
        e3.turn(-45);
        e3.setScaleImage(false);
        Edge e4 = new Edge(4, n3, n5, 7, mst);
        n3.getEdgeSet().add(e4);
        n5.getEdgeSet().add(e4);
        addObject(e4,((n3.getX()+n5.getX())/2),((n3.getY()+n5.getY())/2)+20);
        e4.turn(-80);
        e4.setScaleImage(false);
        Edge e5 = new Edge(5, n1, n3, 5, mst);
        n1.getEdgeSet().add(e5);
        n3.getEdgeSet().add(e5);
        addObject(e5,((n1.getX()+n3.getX())/2),((n1.getY()+n3.getY())/2));
        //e5.turn(-10);
        e5.setScaleImage(false);
        Edge e6 = new Edge(6, n1, n5, 9, mst);
        n1.getEdgeSet().add(e6);
        n5.getEdgeSet().add(e6);
        addObject(e6,((n1.getX()+n5.getX())/2),((n1.getY()+n5.getY())/2)+20);
        e6.turn(50);
        //GreenfootImage e6Img = e6.getImage();
        //e6Img.scale(11,200);
        //e6.setImage(e6Img);
        
         e6.setScaleImage(true);
         //Set the width and height for scaling
        //e6.setImgHeight(Math.sqrt((n1.getX()*n5.getX())+(n1.getY()*n5.getY())));
        e6.setImgHeight(200);
        e6.setImageWidth(11);
        
        Edge e7 = new Edge(7, n1, n4, 15, mst);
        n1.getEdgeSet().add(e7);
        n4.getEdgeSet().add(e7);
        addObject(e7,((n1.getX()+n4.getX())/2),((n1.getY()+n4.getY())/2)+20);
        e7.turn(-90);
        e7.setScaleImage(true);
        
        GreenfootImage e7Img = e7.getImage();
        e7Img.scale(11,260);
        e7.setImage(e7Img);
        
        e7.setImgHeight(260);
        e7.setImageWidth(11);

        Edge e8=new Edge(8,n5,n7,8,mst);
        n5.getEdgeSet().add(e8);
        n7.getEdgeSet().add(e8);
        addObject(e8,((n5.getX()+n7.getX())/2),((n5.getY()+n7.getY())/2)+20);
        e8.turn(80);
        e8.setScaleImage(false);
        
        Edge e9=new Edge(9,n4,n7,5,mst);
        n4.getEdgeSet().add(e9);
        n7.getEdgeSet().add(e9);
        addObject(e9,((n4.getX()+n7.getX())/2),((n4.getY()+n7.getY())/2)+20);        
        e9.setScaleImage(false);
        Edge e10=new Edge(10,n2,n4,8,mst);
        n2.getEdgeSet().add(e10);
        n4.getEdgeSet().add(e10);
        addObject(e10,((n4.getX()+n2.getX())/2),((n4.getY()+n2.getY())/2)+20);
        e10.turn(60);
        e10.setScaleImage(false);
        Edge e11=new Edge(11,n4,n6,9,mst);
        n4.getEdgeSet().add(e11);
        n6.getEdgeSet().add(e11);
        addObject(e11,((n4.getX()+n6.getX())/2),((n4.getY()+n6.getY())/2)+20);
        e11.turn(-20);
        e11.setScaleImage(false);
        
        //Add the labels to the world displaying weights
        Text text = new Text("7", Color.orange);
        addObject(text,e4.getX()-10,e4.getY()-10);
        Text text2 = new Text("9", Color.orange);
        addObject(text2,e6.getX()-10,e6.getY()-10);
        Text text3 = new Text("8", Color.orange);
        addObject(text3,e8.getX()-10,e8.getY()-10);
        Text text4 = new Text("6", Color.orange);
        addObject(text4,e2.getX()-10,e2.getY()+10);
        Text text5 = new Text("8", Color.orange);
        addObject(text5,e10.getX()-10,e10.getY()-10);
        Text text6 = new Text("15", Color.orange);
        addObject(text6,e7.getX()-10,e7.getY()-10);
        Text text7 = new Text("5", Color.orange);
        addObject(text7,e5.getX()-10,e5.getY()-10);
        Text text8 = new Text("7", Color.orange);
        addObject(text8,e3.getX()-10,e3.getY()+20);
        Text text9 = new Text("5", Color.orange);
        addObject(text9,e9.getX()-10,e9.getY()+20);
        Text text10 = new Text("9", Color.orange);
        addObject(text10,e11.getX()-10,e11.getY()+20);
        Text text11 = new Text("11", Color.orange);
        addObject(text11,e1.getX()-10,e1.getY()-10);
        

        mst.getNodeSet().add(n1);
        mst.getNodeSet().add(n2);
        mst.getNodeSet().add(n3);
        mst.getNodeSet().add(n4);
        mst.getNodeSet().add(n5);

        mst.getEdgeSet().add(e1);
        mst.getEdgeSet().add(e2);
        mst.getEdgeSet().add(e3);
        mst.getEdgeSet().add(e4);
        mst.getEdgeSet().add(e5);
        mst.getEdgeSet().add(e6);
        mst.getEdgeSet().add(e7);
        new Thread(){
            public void run(){
                do{
                    scoreboard.printScore();
                    try{
                        Thread.sleep(1000);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }while(!mst.isIsFinished());
                timer.stop();
                scoreboard.printScore();
            }
        }.start();
 
    }
}
