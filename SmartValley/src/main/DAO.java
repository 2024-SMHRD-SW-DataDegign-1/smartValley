package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	// 오라클 연결
	private Connection conn = null;
	
	// 쿼리문 전달
	private PreparedStatement psmt = null;

	// DB 연결
	public void conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";

			String user = "campus_24SW_DD_p1_1";

			String password = "smhrd1";

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {

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

		}

	}

	// 회원가입
	public int join(String id, String pw, String farm) {

		// DB 연결
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
				// 중복 시
				checkNum = 0;
				System.out.println("사용중입니다. 다시 입력해주세요!");
			} else {
				// 중복 없을 시
				checkNum = 1;
			}

			// 중복값이 없을 때 회원가입 실행
			if (checkNum == 1) {
				// 회원가입 쿼리문
				// 1. 계정 데이터 추가(ACCOUNT 테이블)
				String sql2 = "INSERT INTO ACCOUNT VALUES(?,?,?)";

				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, id);
				psmt.setString(2, pw);
				psmt.setString(3, farm);

				row = psmt.executeUpdate();

				// 2. 게임 진행 정보 데이터 추가(PROGRESS 테이블)
				String sql3 = "INSERT INTO PROGRESS VALUES(?,?,?,?,?,?,?)";

				psmt = conn.prepareStatement(sql3);
				psmt.setString(1, id); // ID
				psmt.setInt(2, 1); // GAME_DATE
				psmt.setInt(3, 3000); // GOLD
				psmt.setInt(4, 0); // CONDI_FIELD
				psmt.setString(5, ""); // CROPS_FIELD
				psmt.setInt(6, 0); // CNT_WATER
				psmt.setString(7, "false"); // IS_HARVEST

				row = psmt.executeUpdate();

				// 3. 소지품 데이터 추가(INVENTORY 테이블)
				String sql4 = "INSERT INTO INVENTORY VALUES(?,?,?,?,?,?,?,?,?)";

				psmt = conn.prepareStatement(sql4);
				psmt.setString(1, id);
				psmt.setInt(2, 0); // 당근
				psmt.setInt(3, 0); // 당근 씨앗
				psmt.setInt(4, 0); // 토마토
				psmt.setInt(5, 0); // 토마토 씨앗
				psmt.setInt(6, 0); // 무
				psmt.setInt(7, 0); // 무 씨앗
				psmt.setInt(8, 0); // 호박
				psmt.setInt(9, 0); // 호박 씨앗

				row = psmt.executeUpdate();

			}

		} catch (Exception e) {

		} finally {
			// DB종료
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

		String[] loginInfo = { name, farmName };

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, LoginId);
			psmt.setString(2, LoginPw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				// 결과데이터 중 첫번째 컬럼값
				name = rs.getString(1);
				farmName = rs.getString(2);
				loginInfo[0] = name;
				loginInfo[1] = farmName;
			}

		} catch (SQLException e) {

		} finally {
			dbClose();
		}
		return loginInfo;

	}

	// 게임 진행 정보 불러오기 기능
	public LoginAccount loadData(LoginAccount login) {

		// PROGRESS 테이블 데이터 불러오기
		login = loadProgress(login);

		// INVENTORY 테이블 데이터 불러오기
		login = loadInventory(login);

		return login;
	}

	// PROGRESS 테이블 데이터 불러오기
	public LoginAccount loadProgress(LoginAccount login) {

		conn();
		String sql = "SELECT * FROM PROGRESS WHERE ID = ?";
		ResultSet rs = null;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login.getId());

			rs = psmt.executeQuery();

			if (rs.next()) {
				login.setGameDay(rs.getInt(2));
				login.setGold(rs.getInt(3));
				login.setCondiFarm(rs.getInt(4));

				// 심은 작물이 없을 경우, 공백(null)이 DB에 저장되는데,
				// 다시 Java에 불러올 때에는 login.getPlantName()의 값이 null이면 에러가 발생하므로
				// null일 경우, ""으로 대입, 아니라면 DB에 저장된 대로 가져오도록 조건문 설정
				if (rs.getString(5).isEmpty()) {
					login.setPlantName("");

				} else {
					login.setPlantName(rs.getString(5));

				}

				login.setCntWater(rs.getInt(6));

				// 오라클 DB에는 boolean 자료형이 없어서 String 자료형으로 변환하여 저장됨
				// Java에서 다시 사용하기 위해 String으로 저장된 데이터를 다시 boolean으로 변환 필요
				login.setIsHarvest(Boolean.valueOf(rs.getString(7)));

			}

		} catch (Exception e) {

		} finally {
			dbClose();

		}

		return login;

	}

	// INVENTORY 테이블 데이터 불러오기
	public LoginAccount loadInventory(LoginAccount login) {

		conn();
		String sql = "SELECT * FROM INVENTORY WHERE ID = ?";
		ResultSet rs = null;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login.getId());

			rs = psmt.executeQuery();

			if (rs.next()) {
				login.setCrCount(rs.getInt(2));
				login.setCrsdCount(rs.getInt(3));
				login.setTmtCount(rs.getInt(4));
				login.setTmtsdCount(rs.getInt(5));
				login.setRdCount(rs.getInt(6));
				login.setRdsdCount(rs.getInt(7));
				login.setPkCount(rs.getInt(8));
				login.setPksdCount(rs.getInt(9));

			}
		} catch (Exception e) {

		} finally {
			dbClose();

		}

		return login;

	}

	// DB에 게임 진행 정보 저장하기
	public void saveData(LoginAccount login) {

		saveProgress(login);
		saveInventory(login);

	}

	// PROGRESS 테이블에 저장하기
	public void saveProgress(LoginAccount login) {

		conn();
		String sql = "UPDATE PROGRESS SET GAME_DATE = ?, GOLD = ?, CONDI_FIELD = ?, CROPS_FIELD = ?, CNT_WATER = ?, IS_HARVEST = ? WHERE ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, login.getGameDay()); // GAME_DATE
			psmt.setInt(2, login.getGold()); // GOLD
			psmt.setInt(3, login.getCondiFarm()); // CONDI_FIELD
			psmt.setString(4, login.getPlantName()); // CROPS_FIELD
			psmt.setInt(5, login.getCntWater()); // CNT_WATER

			// boolean 자료형 -> String 자료형으로 변환
			psmt.setString(6, String.valueOf(login.getIsHarvest())); // IS_HARVEST

			psmt.setString(7, login.getId()); // ID

			psmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			dbClose();

		}

	}

	// INVENTORY 테이블에 저장하기
	public void saveInventory(LoginAccount login) {

		conn();
		String sql = "UPDATE INVENTORY SET 당근 = ?, 당근_씨앗 = ?, 토마토 = ?, 토마토_씨앗 = ?, 무 = ?, 무_씨앗 = ?, 호박 = ?, 호박_씨앗 = ? WHERE ID = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, login.getCrCount()); // 당근
			psmt.setInt(2, login.getCrsdCount()); // 당근_씨앗
			psmt.setInt(3, login.getTmtCount()); // 토마토
			psmt.setInt(4, login.getTmtsdCount()); // 토마토_씨앗
			psmt.setInt(5, login.getRdCount()); // 무
			psmt.setInt(6, login.getRdsdCount()); // 무_씨앗
			psmt.setInt(7, login.getPkCount()); // 호박
			psmt.setInt(8, login.getPksdCount()); // 호박_씨앗

			psmt.setString(9, login.getId()); // ID

			psmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			dbClose();

		}

	}

}
