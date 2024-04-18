package main;

import java.sql.ResultSet;
import java.util.Scanner;


import java.sql.PreparedStatement;
import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		DAO dao = new DAO();
		Connection conn = null;
		// 오라클 연결
		PreparedStatement psmt = null;
		// 쿼리문 전달

		Scanner sc = new Scanner(System.in);

		System.out.println("===================================");
		System.out.println("                                   ");
		System.out.println("             WELCOME TO            ");
		System.out.println("            SMART VALLEY           ");
		System.out.println("                                   ");
		System.out.println("===================================");

		while (true) {
			System.out.print("[1] 새로하기   [2] 이어하기   [3] 종료   >>  ");
			int title = sc.nextInt();

			if (title == 1) { // 새로하기,Join

				System.out.print("ID 입력 : ");
				String joinId = sc.next();
				System.out.print("PW 입력 : ");
				String joinPw = sc.next();
				System.out.print("농장이름 입력 : ");
				String farm = sc.next();

				// DB 연결
				dao.conn();


				// 입력값이 중복된 데이터가 있는지 확인하는 거 먼저
				String sql = "SELECT * FROM ACCOUNT WHERE ID = ? OR FARMNAME = ?";

				int checkNum = 0;
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, joinId);
					psmt.setString(2, farm);

					ResultSet rs = psmt.executeQuery();

					if (rs.next() || rs.getString(1).equals(joinId) || rs.getString(2).equals(farm)) {
						checkNum = 0;
						System.out.println("사용중입니다. 다시 입력해주세요!");
					} else {
						checkNum = 1;
						System.out.println("회원가입 성공 ˶•⩊•˶ 프롤로그를 시청하시겠어요?");
					}

				} catch (Exception e) {

				}

				// 회원가입 쿼리문
				int row = dao.join(joinId, joinPw, farm);

				int prolNum = 0;

				if (row > 0) {
					System.out.println("회원가입 성공 ˶•⩊•˶ 프롤로그를 시청하시겠어요? ");
					System.out.print("[1] 네      [2] 아니오   >>   ");
					prolNum = sc.nextInt();

					if (prolNum == 1) {
						// 프롤로그 보기
						System.out.println("나는 스인개 출신 시니어 개발자 n년차를 달리고 있었다,, \n개발자란 이런걸까,,?");
						System.out.println(
								"내가 꿈꿔왔던 워라밸은 어디간거지,,? ʕ ´•̥̥̥ ᴥ•̥̥̥`ʔ \n이렇게는 살 수 없어,,,!!!!!!!  (ʘ言ʘ╬) \n내 꿈을 찾아 떠난다! 지!금!당!장!");
						System.out.println();
						System.out.print("게임 시작  >>  [1] 네  [2] 아니오 ");
						int ans = sc.nextInt();
						if (ans == 1) {
							System.out.println();
							System.out.println(joinId + "님 환영합니다. \n 오늘 하루도 힘차게 시작해봐요!!");
							break;
						} else {
							System.out.println("게임이 종료되었습니다. Good Game... ദ്ദി・ᴗ・)✧");
							break;
						}

					} else if (prolNum == 2) {
						// 프롤로그 넘기기
						System.out.println();
						System.out.println(joinId + "님 환영합니다. \n 오늘 하루도 힘차게 시작해봐요!!");
						break;
					}
				} else if (row == 0) {
					System.out.println("회원가입 실패 ( •ᴗ•̥ ˳ ) 다시 시도해주세요. ");

				}
			} else if (title == 2) { // 이어하기,Login

				try {
					System.out.print("ID 입력 : ");
					String LoginId = sc.next();
					System.out.print("PW 입력 : ");
					String LoginPw = sc.next();

					// DB 연결
					dao.conn();

					// 로그인 쿼리문
					// SELECT ID FROM ACCOUNT WHERE ID = ? AND PW = ?
					String name = dao.login(LoginId, LoginPw);

					if (name.equals("")) {
						System.out.println("로그인 실패! 다시 입력해주세요 ｡° ૮₍°´ᯅ`°₎ა °｡");
					} else {
						System.out.println(name + "님 환영합니다 ₍₍ ◝( ◉ ‸ ◉ )◟ ⁾⁾ \n오늘 하루도 힘차게 시작해봐요!!");
						break;
					}
				} catch (Exception e) {

				}

			} else { // 게임종료
				System.out.println("게임이 종료되었습니다. Good Game... ദ്ദി・ᴗ・)✧");
				break;

			}
		}

	
		
		
		
		
		
		
		
	}

}
