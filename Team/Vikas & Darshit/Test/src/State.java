
public enum State {
	NOT_SELECTED("Not Selected"), SELECTED("Selected"), SUGGESTED("Suggested");

	String value;

	private State(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
}
