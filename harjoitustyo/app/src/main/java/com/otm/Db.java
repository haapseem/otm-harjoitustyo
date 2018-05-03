package com.otm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.otm.models.Highscore;

public class Db {

	private Connection c;

	public Db() {
		try {
			this.c = DriverManager.getConnection("jdbc:sqlite:db.db");
			this.c.createStatement();
			PreparedStatement s = c.prepareStatement("CREATE TABLE IF NOT EXISTS 'HighScores' (" + "id int NOT NULL,"
					+ "score int," + "PRIMARY KEY(id));");
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Highscore getScore() {
		try {
			PreparedStatement s = c.prepareStatement("SELECT * FROM HighScores LIMIT 1");
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				return new Highscore(rs.getInt("id"), rs.getInt("score"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO HighScores (id, score) VALUES (0, 0)");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Highscore(0, 0);
	}

	public void updateScore(int score) {
		PreparedStatement ps;
		try {
			ps = c.prepareStatement("UPDATE HighScores SET score = ?");
			ps.setInt(1, score);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
