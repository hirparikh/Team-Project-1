package api;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		Score score = new Score();
		score.setName("dthesiya");
		Score room = MySQL.setRoom(score);
		Score opponent = MySQL.getOpponent(room.getRoomId());
		if (opponent != null) {
			System.out.println(opponent.getName() + ":" + opponent.getScore());
		}
	}
}
