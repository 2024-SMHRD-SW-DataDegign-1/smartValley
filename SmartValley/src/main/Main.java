package main;

import java.sql.ResultSet;
import java.util.Scanner;

import javazoom.jl.player.MP3Player;

import java.sql.PreparedStatement;
import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		DAO dao = new DAO();
		
		//노래 재생 도구
		MP3Player bgm = new MP3Player();
		
		
		
		// 오라클 연결
		Connection conn = null;
		// 쿼리문 전달
		PreparedStatement psmt = null;
		
		//입력 도구
		Scanner sc = new Scanner(System.in);
		
		bgm.play("C:\\Users\\smhrd\\Desktop\\JavaStudy\\Mini\\bgm\\Valley.mp3");
		
		
		System.out.println(
				"═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
		System.out.println("\r\n"
				+ " ::::::::  ::::    ::::      :::     :::::::::  :::::::::::  :::     :::     :::     :::        :::        :::::::::: :::   ::: \r\n"
				+ ":+:    :+: +:+:+: :+:+:+   :+: :+:   :+:    :+:     :+:      :+:     :+:   :+: :+:   :+:        :+:        :+:        :+:   :+: \r\n"
				+ "+:+        +:+ +:+:+ +:+  +:+   +:+  +:+    +:+     +:+      +:+     +:+  +:+   +:+  +:+        +:+        +:+         +:+ +:+  \r\n"
				+ "+#++:++#++ +#+  +:+  +#+ +#++:++#++: +#++:++#:      +#+      +#+     +:+ +#++:++#++: +#+        +#+        +#++:++#     +#++:   \r\n"
				+ "       +#+ +#+       +#+ +#+     +#+ +#+    +#+     +#+       +#+   +#+  +#+     +#+ +#+        +#+        +#+           +#+    \r\n"
				+ "#+#    #+# #+#       #+# #+#     #+# #+#    #+#     #+#        #+#+#+#   #+#     #+# #+#        #+#        #+#           #+#    \r\n"
				+ " ########  ###       ### ###     ### ###    ###     ###          ###     ###     ### ########## ########## ##########    ###    \r\n"
				+ "");
		System.out.println(
				"═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
		while (true) {
			//타이틀 메뉴
			System.out.print("[1] 새로하기   [2] 이어하기   [3] 종료   >>  ");
			int title = sc.nextInt();
			
			if (title == 1) {
				 // 새로하기,Join
				System.out.print("ID 입력 : ");
				String joinId = sc.next();
				System.out.print("PW 입력 : ");
				String joinPw = sc.next();
				System.out.print("농장이름 입력 : ");
				String farm = sc.next();

				

				// 회원가입 쿼리문
				int row = dao.join(joinId, joinPw, farm);
				
				//row 값 확인
				//System.out.println("회원가입 row : " + row);
				
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
							bgm.stop();
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
			} else if (title == 2) { 
				// 이어하기,Login
				try {
					System.out.print("ID 입력 : ");
					String loginId = sc.next();
					System.out.print("PW 입력 : ");
					String loginPw = sc.next();

					// DB 연결
					//dao.conn();

					// 로그인 쿼리문
					// SELECT ID FROM ACCOUNT WHERE ID = ? AND PW = ?
					String name = dao.login(loginId, loginPw);

					if (name.equals("")) {
						//값이 없거나 틀렸을 때
						System.out.println("로그인 실패! 다시 입력해주세요 ｡° ૮₍°´ᯅ`°₎ა °｡");
					} else {
						// 로그인 성공 시
						System.out.println(name + "님 환영합니다 ₍₍ ◝( ◉ ‸ ◉ )◟ ⁾⁾ \n오늘 하루도 힘차게 시작해봐요!!");
						break;
					}
				} catch (Exception e) {

				}

			} else { // 게임종료
				System.out.println("게임이 종료되었습니다. Good Game... ദ്ദി・ᴗ・)✧");
				bgm.stop();
				break;

			}
		}

	}

}
