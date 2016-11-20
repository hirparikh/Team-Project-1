package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://173.194.106.78:3306/mst";

	// Database credentials
	static final String USER = "dthesiya";
	static final String PASS = "94260";

	public static Score getScore(int roomId, boolean isFirst) {
		Connection conn = null;
		Statement stmt = null;
		Score score = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM room WHERE id = " + roomId;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				score = new Score();
				if (isFirst) {
					score.setId(rs.getInt("player2_id"));
					score.setName(rs.getString("player2_name"));
					score.setScore(rs.getInt("player2_score"));
					score.setPercentage(rs.getFloat("player2_percentage"));
					byte didFinish = rs.getByte("player2_did_finish");
					score.setDidFinish(didFinish == 1);
				} else {
					score.setId(rs.getInt("player1_id"));
					score.setName(rs.getString("player1_name"));
					score.setScore(rs.getInt("player1_score"));
					score.setPercentage(rs.getFloat("player1_percentage"));
					byte didFinish = rs.getByte("player1_did_finish");
					score.setDidFinish(didFinish == 1);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return score;
	}

	public static void setScore(Score score) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			byte didFinish = (byte) (score.isDidFinish() ? 1 : 0);
			if (score.isFirst()) {
				sql = "UPDATE room SET player1_score = '" + score.getScore() + "', player1_percentage = '"
						+ score.getPercentage() + "', player1_did_finish = b'" + didFinish + "' WHERE id = "
						+ score.getRoomId();
			} else {
				sql = "UPDATE room SET player2_score = '" + score.getScore() + "', player2_percentage = '"
						+ score.getPercentage() + "', player2_did_finish = b'" + didFinish + "' WHERE id = "
						+ score.getRoomId();
			}
			stmt.executeUpdate(sql);
			if (score.getPercentage() == 100) {
				sql = "UPDATE player SET score = '" + score.getScore() + "', percentage = '" + score.getPercentage()
						+ "', time = '" + score.getTime() + "', did_finish = b'" + didFinish + "' WHERE id = ";
				if (score.isFirst()) {
					sql += "(SELECT player1_id FROM room r WHERE r.id = '" + score.getRoomId() + "')";
				} else {
					sql += "(SELECT player2_id FROM room r WHERE r.id = '" + score.getRoomId() + "')";
				}
				stmt.executeUpdate(sql);
			}
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	public static Score setRoom(Score score) {
		Connection conn = null;
		Statement stmt = null;
		Score result = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "INSERT INTO player(name) VALUES('" + score.getName() + "')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			int user_id = 0;
			if (generatedKeys.next()) {
				user_id = generatedKeys.getInt(1);
			}
			generatedKeys.close();
			if (user_id > 0) {
				sql = "SELECT * FROM room WHERE isAvailable = 1";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					result = new Score();
					result.setRoomId(rs.getInt("id"));
					result.setId(rs.getInt("player1_id"));
					result.setName(rs.getString("player1_name"));
					result.setScore(rs.getInt("player1_score"));
					result.setPercentage(rs.getFloat("player1_percentage"));
					rs.close();
					sql = "UPDATE room SET player2_id = '" + user_id + "', player2_name = '" + score.getName()
							+ "', isAvailable = 0 WHERE id = " + result.getRoomId();
					stmt.executeUpdate(sql);
				} else {
					rs.close();
					sql = "INSERT INTO room(player1_id, player1_name) VALUES('" + user_id + "','" + score.getName()
							+ "')";
					Statement statement = conn.createStatement();
					statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
					ResultSet resultSet = statement.getGeneratedKeys();
					int room_id = 0;
					if (resultSet.next()) {
						room_id = resultSet.getInt(1);
					}
					resultSet.close();
					statement.close();
					if (room_id > 0) {
						result = new Score();
						result.setRoomId(room_id);
					}
				}
			}
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return result;
	}

	public static Score getOpponent(int roomId) {
		Connection conn = null;
		Statement stmt = null;
		Score score = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM room WHERE id = " + roomId;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				score = new Score();
				score.setId(rs.getInt("player2_id"));
				score.setName(rs.getString("player2_name"));
				score.setScore(rs.getInt("player2_score"));
				score.setPercentage(rs.getFloat("player2_percentage"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return score;
	}

	public static List<Score> getLeaderBoard(int limit) {
		Connection conn = null;
		Statement stmt = null;
		List<Score> scoreList = new ArrayList<Score>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM player WHERE did_finish = 1 ORDER BY time LIMIT " + limit;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getInt("id"));
				score.setName(rs.getString("name"));
				score.setScore(rs.getInt("score"));
				score.setPercentage(rs.getFloat("percentage"));
				score.setTime(rs.getInt("time"));
				scoreList.add(score);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return scoreList;
	}
}
