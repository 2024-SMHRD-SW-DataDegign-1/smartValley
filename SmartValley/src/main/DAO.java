package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAO {

	private Connection conn = null;
	// 오라클 연결
	private PreparedStatement psmt = null;
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
		
		//DB 연결
		conn();
		int row = 0;
		try {
			// 중복값 확인 쿼리
			String sql1 = "SELECT * FROM ACCOUNT WHERE ID = ? OR FARMNAME = ?";
			
			int checkNum = 0;
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, id);
			psmt.setString(2, farm);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				//중복 시
				checkNum = 0;
				System.out.println("사용중입니다. 다시 입력해주세요!");
			} else {
				//중복 없을 시
				checkNum = 1;
//				System.out.println("회원가입 성공!  프롤로그를 시청하시겠어요?");
			}
			
			//중복값이 없을 때 회원가입 실행
			if(checkNum == 1){
				//회원가입 쿼리문
				String sql2 = "INSERT INTO ACCOUNT VALUES(?,?,?)";
				
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, id);
				psmt.setString(2, pw);
				psmt.setString(3, farm);
				
				row = psmt.executeUpdate();
			}
			

		} catch (Exception e) {

		}finally {
			//DB종료
			dbClose();
		}
		
		
		return row;

	}

	// 로그인 기능
	public String[] login(String LoginId, String LoginPw) {

		conn();
		String sql = "SELECT ID, FARMNAME FROM ACCOUNT WHERE ID = ? AND PW = ?";
		ResultSet rs = null;

		String name = "";
		String farmName = "";
		
		String[] loginInfo = {name, farmName};
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginId);
			psmt.setString(2, LoginPw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				//결과데이터 중 첫번째 컬럼값
				name = rs.getString(1);
				farmName = rs.getString(2);
				loginInfo[0] = name;
				loginInfo[1] = farmName;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return loginInfo;

	}



}
