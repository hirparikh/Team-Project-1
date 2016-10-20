import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashSet;
import java.util.Set;
import java.awt.Color;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MyWorld extends World
{
 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    String name;
    public MyWorld(String name)
    {    
        // Create a new world with 800*600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground(new GreenfootImage("back_image1.jpg"));
        prepare();
    }
    
    
    public void prepare(){
        //Add the scoreboard object
        ScoreBoard scoreboard = new ScoreBoard(this);
        addObject(scoreboard,120,70);
        scoreboard.updateScore(0, 0);
        //Add the treasure chest object
        TreasureChest treasurechest = new TreasureChest();
        addObject(treasurechest,609,90);

        MST mst = new MST(scoreboard);
        
        //Add the nodes to the world
        Node n1= new Node(1,mst);
        Node n2 = new Node(2,mst);
        Node n3 = new Node(3,mst);
        Node n4 = new Node(4,mst);
        Node n5 = new Node(5,mst);
        
        addObject(n1,293,487);
        addObject(n2,470,461);
        addObject(n3,328,287);
        addObject(n4,597,343);
        addObject(n5,478,225);

        //Add the edges to the world
        Edge e1 = new Edge(1, n2, n4, 9, mst);
        n2.getEdgeSet().add(e1);
        n4.getEdgeSet().add(e1);
        addObject(e1,533,424);
        e1.turn(45);
        Edge e2 = new Edge(2, n1, n2, 3, mst);
        n2.getEdgeSet().add(e2);
        n1.getEdgeSet().add(e2);
        addObject(e2,385,482);
        e2.turn(90);
        Edge e3 = new Edge(3, n4, n5, 7, mst);
        n5.getEdgeSet().add(e3);
        n4.getEdgeSet().add(e3);
        addObject(e3,536,307);
        e3.turn(-45);
        Edge e4 = new Edge(4, n3, n5, 4, mst);
        n3.getEdgeSet().add(e4);
        n5.getEdgeSet().add(e4);
        addObject(e4,401,277);
        e4.turn(60);
        Edge e5 = new Edge(5, n1, n3, 8, mst);
        n1.getEdgeSet().add(e5);
        n3.getEdgeSet().add(e5);
        addObject(e5,300,389);
        e5.turn(20);
        Edge e6 = new Edge(6, n2, n3, 6, mst);
        n2.getEdgeSet().add(e6);
        n3.getEdgeSet().add(e6);
        addObject(e6,397,398);
        e6.turn(-40);
        GreenfootImage e6Img = e6.getImage();
        e6Img.scale(11,200);
        e6.setImage(e6Img);
        Edge e7 = new Edge(7, n4, n3, 5, mst);
        n3.getEdgeSet().add(e7);
        n4.getEdgeSet().add(e7);
        addObject(e7,461,341);
        e7.turn(-80);
        GreenfootImage e7Img = e7.getImage();
        e7Img.scale(11,260);
        e7.setImage(e7Img);

        
        //Add the labels to the world displaying weights
        Text text = new Text("7", Color.orange);
        addObject(text,538,279);
        Text text2 = new Text("4", Color.orange);
        addObject(text2,390,260);
        Text text3 = new Text("8", Color.orange);
        addObject(text3,280,376);
        Text text4 = new Text("3", Color.orange);
        addObject(text4,391,506);
        Text text5 = new Text("9", Color.orange);
        addObject(text5,561,450);
        Text text6 = new Text("6", Color.orange);
        addObject(text6,379,397);
        Text text7 = new Text("5", Color.orange);
        addObject(text7,447,315);
        

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
    }

}
