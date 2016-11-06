import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Edge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Edge extends Actor
{
    private State state = State.NOT_SELECTED;
    private int id, weight;
    private Node n1, n2;
    private MST mst;
    //The height of the image of the edge
    private int imgHeight ;
    //The width of the image of the edge
    private int imgWidth ;
    //Indicates if the image of the edge is to be scaled
    private boolean scaleImage ;
    /**
     * Act - do whatever the Edge wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        
        if(Greenfoot.mouseClicked(this) && !mst.isFirstNode()){
            Greenfoot.playSound("sounds/edge_click.mp3");
            pickEdge();            
        }
        
    }    
    
    public Edge(int id, Node n1, Node n2, int weight, MST mst) {
        // TODO Auto-generated constructor stub
        this.id = id;
        this.weight = weight;
        this.n1 = n1;
        this.n2 = n2;
        this.mst = mst;
    }
    
    public void pickEdge() {
        if (state.equals(State.SUGGESTED)) {
            setState(State.SELECTED);
            Node other;
            if (n1.getState().equals(State.SUGGESTED)) {
                n1.setState(State.SELECTED);
                other = n1;
            } else {
                n2.setState(State.SELECTED);
                other = n2;
            }
            for (Edge edge : other.getEdgeSet()) {
                switch (edge.getState()) {
                case NOT_SELECTED:
                    edge.setState(State.SUGGESTED);
                    if (edge.getN1().getState().equals(State.SELECTED)) {
                        edge.getN2().setState(State.SUGGESTED);
                    } else {
                        edge.getN1().setState(State.SUGGESTED);
                    }
                    break;
                case SUGGESTED:
                    if ((edge.getN1() == other && edge.getN2().getState().equals(State.SELECTED))
                            || (edge.getN2() == other && edge.getN1().getState().equals(State.SELECTED))) {
                        edge.setState(State.NOT_SELECTED);
                    }
                    break;
                default:
                    break;
                }
            }
            // checking if entire graph is traversed
            mst.checkResult();
        } else {
            System.out.println("oops...you hit a dead end...shoud not have done that..!!");
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
         this.state = state;
        switch(state){
            case NOT_SELECTED :
                setImage("images/line_gray.png");
            break;
            case SELECTED :
                setImage("images/line_yellow.png");
            break;
            case SUGGESTED :
                setImage("images/line_orange.png");
            break;
        }
        //After replacing the image for the actor, check if the image is to be sclaed
       scaleImageOfActor();
    }
    
     /**
     * Checks if the image of the edge is to be scaled. If the image is to be scaled, then get the desired dimensions for the image to be used
     * that are already set on the edge object and scale the image accordingly.
     */
    public void scaleImageOfActor(){
         if(scaleImage){
            //If the image is to be scaled, get the current image of the actor
            GreenfootImage img = getImage();
            //Scale it to desired dimensions
            img.scale(imgWidth,imgHeight);
            //Set the image on the actor
            setImage(img);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getN1() {
        return n1;
    }

    public void setN1(Node n1) {
        this.n1 = n1;
    }

    public Node getN2() {
        return n2;
    }

    public void setN2(Node n2) {
        this.n2 = n2;
    }
    
    public void setImgHeight(int imgHeight){
        this.imgHeight = imgHeight;
    }
    
    public void setImageWidth(int imgWidth){
        this.imgWidth = imgWidth;
    }

    public void setScaleImage( boolean scaleImage){
        this.scaleImage = scaleImage;
    }

}


