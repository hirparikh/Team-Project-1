import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashSet;
import java.util.Set;
/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Node extends Actor
{
   private State state = State.SUGGESTED;
	private int id;
	private Set<Edge> edgeSet;

    
    /**
     * Act - do whatever the Node wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public Node(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
		edgeSet = new HashSet<>();
	}

	public Edge addEdgeTo(int id, Node n, int weight) {

		Edge e = new Edge(id, this, n, weight);
		edgeSet.add(e);
		n.getEdgeSet().add(e);
		return e;
	}

	public void pickNode(MST mst) {
		if (mst.isFirstNode() && state.equals(State.SUGGESTED)) {
			state = State.SELECTED;
			mst.setFirstNode(false);
			for (Node n : mst.getNodeSet()) {
				if (n != this) {
					n.setState(State.NOT_SELECTED);
				}
			}
			for (Edge edge : edgeSet) {
				switch (edge.getState()) {
				case NOT_SELECTED:
					edge.setState(State.SUGGESTED);
					if (edge.getN1() == this) {
						edge.getN2().setState(State.SUGGESTED);
					} else {
						edge.getN1().setState(State.SUGGESTED);
					}
					break;
				// case SUGGESTED:
				// if ((edge.getN1() == this &&
				// edge.getN2().getState().equals(State.SELECTED))
				// || (edge.getN2() == this &&
				// edge.getN1().getState().equals(State.SELECTED))) {
				// // error scenario
				// } else {
				// edge.setState(State.SELECTED);
				// }
				// break;
				default:
					break;
				}
			}
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Edge> getEdgeSet() {
		return edgeSet;
	}

	public void setEdgeSet(Set<Edge> edgeSet) {
		this.edgeSet = edgeSet;
	}
}
