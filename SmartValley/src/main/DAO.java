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
		// ResultSet : select 절을 통한 테이블 형식 데이터를 받아 올 수 있는 타입

		String name = "";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginId);
			psmt.setString(2, LoginPw);

			// executeQuery --> 쿼리문을 통해서 테이블에 있는 데이터에 영향을 끼치지 않을 때 사용 되어진다.
			rs = psmt.executeQuery();

			// rs.next() : 다음행에 데이터가 있는지 확인하는 기능
			if (rs.next()) {
				// rs.getString(2) : 결과데이터 중 두번째 컬럼에 있는 데이터를 문자열로 받아오겠다!
				name = rs.getString(1);
				// System.out.println("이름 출력 : "+name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return name;

	}



}
