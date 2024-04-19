package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAO {

	Connection conn = null;
	// 오라클 연결
	PreparedStatement psmt = null;
	// 쿼리문 전달

	// DB 연결
	public void conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";

			String user = "campus_24SW_DD_p1_1";

			String password = "smhrd1";

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DB 닫기
	public void dbClose() {

		try {
			if (psmt != null)
				psmt.close();

			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 회원가입
	public int join(String id, String pw, String farm) {
		String sql = "INSERT INTO ACCOUNT VALUES(?,?,?)";
		int row = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, farm);

			row = psmt.executeUpdate();

		} catch (Exception e) {

			//e.printStackTrace();

		} finally {
			dbClose();
		}
		return row;

	}

	// 로그인 기능
	public String login(String LoginId, String LoginPw) {

		String sql = "SELECT ID FROM ACCOUNT WHERE ID = ? AND PW = ?";
		ResultSet rs = null;

		String name = "";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginId);
			psmt.setString(2, LoginPw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				//결과데이터 중 첫번째 컬럼값
				name = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return name;

	}



}
