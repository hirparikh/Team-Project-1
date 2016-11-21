import greenfoot.*;
import java.util.*;
import java.awt.Color;

public class GameScreen extends Screen implements IScoreObserver{
    private boolean isFirstNode = true;
    private Set<Node> nodeSet = new HashSet<Node>();
    private Set<Edge> edgeSet = new HashSet<Edge>();
    private int minimum = 18;
    private boolean isFinished;
    private Score opponentScore;
    private Thread subject;
    private SimpleTimer timer;
    private Bar playerBar;
    private Bar opponentBar;
    private Text textActor;
    
    public GameScreen(GameWorld world) {
        super(world);
    }

    @Override
    public void setNextScreen(IScreen screen) {
        world.setScreen(screen);
        clearScreen();
        world.getScreen().displayScreen();
    }

    @Override
    public void displayScreen() {
        setIsFinished(false);
        world.setBackground(new GreenfootImage("S7.jpg")); 
        prepare();
        //subject.start();
        timer = new SimpleTimer();
        timer.mark();
    }
    
    public void prepare(){
        textActor = new Text();
        textActor.changeText("0");
        world.addObject(textActor, 905, 85);
        
        playerBar = new Bar(world.getScore().getName(), "% ", 0, 100);
        playerBar.setTextColor(Color.CYAN); 
        world.addObject(playerBar, 170, 40);
        //opponentBar = new Bar(opponentScore.getName(), "%", 0, 100);
        //world.addObject(opponentBar, 350, 40);
        String[] horizontal_line = {"horizontal1.png", "horizontal2.png", "horizontal3.png"};
        String[] backslash_line = {"backslash1.png", "backslash2.png", "backslash3.png"};
        String[] forwardslash_line = {"forwardslash1.png", "forwardslash2.png", "forwardslash3.png"};
        setFirstNode(true);
        //Add the nodes to the world
        CardPart cardPart = new CardPart(478, 87, "card_9.jpg");
        Object[] arr = {this,cardPart};
        Node n1 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part1
        world.addObject(cardPart, 261, 146);
        cardPart = new CardPart(516, 57, "card_6.jpg");
        
        arr[0] = this;
        arr[1] = cardPart;
        Node n2 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part2
        world.addObject(cardPart, 522, 146);
        cardPart = new CardPart(478, 42, "card_3.jpg");
        
        arr[0] = this;
        arr[1] = cardPart;
        Node n3 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part3
        world.addObject(cardPart, 100, 268);
        cardPart = new CardPart(516, 42, "card_4.jpg");
        arr[0] = this;
        arr[1] = cardPart;
        Node n4 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part4
        world.addObject(cardPart, 362, 324);
        cardPart = new CardPart(478, 57, "card_5.jpg");
        arr[0] = this;
        arr[1] = cardPart;
        Node n5 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part5
        world.addObject(cardPart, 261, 447);
        cardPart = new CardPart(478, 72, "card_7.jpg");
        arr[0] = this;
        arr[1] = cardPart;
        Node n6 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part6
        world.addObject(cardPart, 492, 444);
        cardPart = new CardPart(478, 27, "card_1.jpg"); 
        arr[0] = this;
        arr[1] = cardPart;
        Node n7 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part7
        world.addObject(cardPart, 652, 267);
        cardPart = new CardPart(516, 72, "card_8.jpg");
        arr[0] = this;
        arr[1] = cardPart;
        Node n8 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part8
        world.addObject(cardPart, 907, 267);
        cardPart = new CardPart(516, 27, "card_2.jpg");
        arr[0] = this;
        arr[1] = cardPart;
        Node n9 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part9
        world.addObject(cardPart, 786, 444);
        cardPart = new CardPart(516, 87, "card_10.jpg");
        arr[0] = this;
        arr[1] = cardPart;
        Node n10 =(Node)ElementFactory.getElement(ElementType.NODE,arr);
        //part10
        world.addObject(cardPart, 639, 562);
        
        world.addObject(n1, 246, 176);
        world.addObject(n2, 507, 176);
        world.addObject(n3, 116, 297);
        world.addObject(n4, 377, 297);
        world.addObject(n5, 246, 417);
        world.addObject(n6, 507, 417);
        world.addObject(n7, 637, 297);
        world.addObject(n8, 899, 297);
        world.addObject(n9, 768, 417);
        world.addObject(n10, 637, 538);
        
        getNodeSet().add(n1);
        getNodeSet().add(n2);
        getNodeSet().add(n3);
        getNodeSet().add(n4);
        getNodeSet().add(n5);
        getNodeSet().add(n6);
        getNodeSet().add(n7);
        getNodeSet().add(n8);
        getNodeSet().add(n9);
        getNodeSet().add(n10);

        //Add the edges to the world
        //edge1
        createEdge(n1, n2, 2, horizontal_line, ((n1.getX() + n2.getX()) / 2), ((n1.getY() + n2.getY()) / 2));
        //edge2
        createEdge(n1, n3, 7, forwardslash_line, ((n1.getX() + n3.getX()) / 2) - 2, ((n1.getY() + n3.getY()) / 2));
        //edge3
        createEdge(n3, n4, 2, horizontal_line, ((n3.getX() + n4.getX()) / 2), ((n3.getY() + n4.getY()) / 2));
        //edge4
        createEdge(n2, n4, 4, forwardslash_line, ((n2.getX() + n4.getX()) / 2) - 2, ((n2.getY() + n4.getY()) / 2));
        //edge5
        createEdge(n3, n5, 3, backslash_line, ((n3.getX() + n5.getX()) / 2), ((n3.getY() + n5.getY()) / 2));
        //edge6
        createEdge(n5, n6, 1, horizontal_line, ((n5.getX() + n6.getX()) / 2), ((n5.getY() + n6.getY()) / 2));
        //edge7
        createEdge(n4, n6, 10, backslash_line, ((n4.getX() + n6.getX()) / 2), ((n4.getY() + n6.getY()) / 2));
        //edge8
        createEdge(n2, n7, 8, backslash_line, ((n2.getX() + n7.getX()) / 2), ((n2.getY() + n7.getY()) / 2));
        //edge9
        createEdge(n6, n7, 3, forwardslash_line, ((n6.getX() + n7.getX()) / 2) - 2, ((n6.getY() + n7.getY()) / 2));
        //edge10
        createEdge(n7, n8, 5, horizontal_line, ((n7.getX() + n8.getX()) / 2), ((n7.getY() + n8.getY()) / 2));
        //edge11
        createEdge(n7, n9, 6, backslash_line, ((n7.getX() + n9.getX()) / 2), ((n7.getY() + n9.getY()) / 2));
        //edge12
        createEdge(n8, n9, 11, forwardslash_line, ((n8.getX() + n9.getX()) / 2) - 2, ((n8.getY() + n9.getY()) / 2));
        //edge13
        createEdge(n6, n10, 3, backslash_line, ((n6.getX() + n10.getX()) / 2), ((n6.getY() + n10.getY()) / 2));
        //edge14
        createEdge(n9, n10, 2, forwardslash_line, ((n9.getX() + n10.getX()) / 2) - 2, ((n9.getY() + n10.getY()) / 2));
    }
    
    public void createEdge(Node n1, Node n2, int weight, String[] alignArr, int x, int y){
        Object[] arr = new Object[5];
        arr[0]=n1;
        arr[1]=n2;
        arr[2]=weight;
        arr[3]=alignArr;
        arr[4]=this;
        
        Edge e =(Edge)ElementFactory.getElement(ElementType.EDGE,arr); 
        world.addObject(e, x, y);
        getEdgeSet().add(e);
    }
    
    public void checkResult(){
        int result = 0;
        for (Edge edge : edgeSet) {
            if (edge.getState().equals(State.SELECTED))
                result += edge.getWeight();
        }
        int selectedNodes = 0;
        for (Node node : nodeSet) {
            if (node.getState().equals(State.SELECTED)) {
                selectedNodes++;
            }
        }
        int perc = selectedNodes * 100 / nodeSet.size();
        Score score = world.getScore();
        score.setScore(result);
        score.setPercentage(perc);
        score.setTime(timer.millisElapsed()/1000);
        if(perc == 100 && result == minimum) {
            score.setDidFinish(true);
        }
        updatePlayerScore();
        if(perc == 100){
            stopScoreSubject();
            if (result == minimum) {
                //Greenfoot.playSound("sounds/win.mp3");
                System.out.println("Eureka");
                setWinScreen();
            } else if(result > minimum){
                //Greenfoot.playSound("sounds/sorry.mp3");
                System.out.println("You haven't connected bulbs with minimum volts");
               setLoseScreen();
            }
        }
    }
    
    public void setWinScreen(){
        setNextScreen(ScreenFactory.getScreen(ScreenType.WIN, world));
    }
    
    public void setLoseScreen(){
        LoseScreen loseScreen = (LoseScreen)ScreenFactory.getScreen(ScreenType.LOSE, world);
        setNextScreen(loseScreen);
    }
    
    public void updatePlayerScore(){
        Score score = world.getScore();
        playerBar.add((int)score.getPercentage());
        textActor.changeText(""+score.getScore());
        //updating player score on server
        //Server.setScore(score);
    }
    
    public void stopScoreSubject(){
        isFinished = true;
        //subject.stop();
    }
    
    public void updateOpponentScore(Score opponentScore){
        this.opponentScore = opponentScore;
        System.out.println(opponentScore.getName() + " : " + opponentScore.getScore() + " : " + opponentScore.getPercentage());
        if(opponentScore.getPercentage() == 100){
            //opponent just finished his/her game, 
            //so stop getting updates and let this player keep playing
            stopScoreSubject();
            if( opponentScore.isDidFinish()){
                //opponent finished his/her game with optimum path,
                //so stop this game as well
                setLoseScreen();
            }
        }
    }
    
    public void setOpponentScore(Score opponentScore){
        this.opponentScore = opponentScore;
    }
    
    @Override
    public void setPreviousScreen() {
    }

    @Override
    public void clearScreen() {
        world.removeObjects(world.getObjects(Actor.class));
        nodeSet.clear();
        edgeSet.clear();
        subject = null;
    }
    
    public boolean isFirstNode() {
        return isFirstNode;
    }

    public void setFirstNode(boolean isFirstNode) {
        this.isFirstNode = isFirstNode;
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set<Node> nodeSet) {
        this.nodeSet = nodeSet;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public Set<Edge> getEdgeSet() {
        return edgeSet;
    }

    public void setEdgeSet(Set<Edge> edgeSet) {
        this.edgeSet = edgeSet;
    }
    public boolean isFinished(){
        return isFinished;
    }
    
    public void setIsFinished(boolean isFinished){
        this.isFinished = isFinished;
    }
    
    public void setScoreSubject(ScoreUpdate subject){
        this.subject = subject;
    }
}
