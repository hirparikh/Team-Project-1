import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    public MyWorld()
    {    
        // Create a new world with 800*600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
    }
    
    
    public void prepare(){

        ScoreBoard scoreboard = new ScoreBoard();
        addObject(scoreboard,298,178);
        removeObject(scoreboard);
        ScoreBoard scoreboard2 = new ScoreBoard();
        addObject(scoreboard2,139,80);
        TreasureChest treasurechest = new TreasureChest();
        addObject(treasurechest,609,90);

        Node node= new Node(1);
        addObject(node,260,478);
        Node node2 = new Node(2);
        addObject(node2,523,482);
        node2.setLocation(524,478);
        Node node3 = new Node(3);
        addObject(node3,156,341);
        Node node4 = new Node(4);
        addObject(node4,621,318);
        Node node5 = new Node(5);
        addObject(node5,402,215);
        Edge edge = new Edge(1, node2, node4, 9);
        addObject(edge,586,418);
        edge.turn(45);
        Edge edge2 = new Edge(2, node, node2, 3);
        addObject(edge2,385,482);
        edge2.turn(90);
        Edge edge3 = new Edge(3, node4, node5, 7);
        addObject(edge3,514,258);
        edge3.turn(-45);
        Edge edge4 = new Edge(4, node3, node5, 9);
        addObject(edge4,368,262);
        edge4.turn(60);
        Edge edge5 = new Edge(5, node, node3, 8);
        addObject(edge5,316,392);
        edge5.turn(20);

        node.setLocation(302,464);
        node2.setLocation(470,461);
        edge.setLocation(533,424);
        node4.setLocation(593,347);

        edge3.setLocation(532,309);

        node3.setLocation(326,291);
        node.setLocation(291,486);
        edge5.setLocation(300,389);
        edge4.setLocation(400,273);
        node5.setLocation(478,225);
        Edge edge6 = new Edge(6, node5, node, 6);
        addObject(edge6,420,384);
        edge6.turn(-40);
        edge6.setLocation(395,383);
        Edge edge7 = new Edge(7, node5, node2, 5);
        addObject(edge7,471,345);
        edge7.turn(-80);
    
        Text text = new Text("7");
        addObject(text,545,278);
        edge4.setLocation(401,277);
        text.setLocation(538,279);
        Text text2 = new Text("9");
        addObject(text2,390,260);
        Text text3 = new Text("8");
        addObject(text3,280,376);
        Text text4 = new Text("3");
        addObject(text4,391,506);
        Text text5 = new Text("9");
        addObject(text5,561,450);
        Text text6 = new Text("6");
        addObject(text6,379,397);
        Text text7 = new Text("5");
        addObject(text7,447,315);
        edge7.setLocation(438,332);
    }
}
