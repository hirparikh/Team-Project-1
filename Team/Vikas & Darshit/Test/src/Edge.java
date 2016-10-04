
public class Edge {

	private State state = State.NOT_SELECTED;
	private int id, weight;
	private Node n1, n2;

	public Edge(int id, Node n1, Node n2, int weight) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.weight = weight;
		this.n1 = n1;
		this.n2 = n2;
	}

	public void pickEdge(MST mst) {
		if (state.equals(State.SUGGESTED)) {
			state = State.SELECTED;
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
						// error scenario
						// creating cycle if we keep it suggested and user tries
						// to pick the same
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

}
