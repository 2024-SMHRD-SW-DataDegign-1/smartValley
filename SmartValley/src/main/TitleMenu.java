package main;

import java.util.Scanner;

public class TitleMenu {

	// JDBC 기능 객체 생성
	private DAO dao = new DAO();

	// 타이틀 메뉴 실행 메소드
	public LoginAccount runTitleMenu() {
		// 비어있는 로그인 정보 객체 생성
		LoginAccount login = new LoginAccount();

		// 입력 도구
		Scanner sc = new Scanner(System.in);

		// 타이틀 배너 출력
		printTitleBanner();

		// 타이틀 메뉴
		printTitleMenu();
		int title = sc.nextInt();

		if (title == 1) {
			login = titleJoin(sc);

		} else if (title == 2) {
			login = titleLogin(sc);

		} else { // 게임종료
			login.setId(""); // 게임 메뉴로 진입하지 않도록 하는 조건
			login.setGameOver(true); // Main의 while문 탈출 조건
		}
		

		return login;
	}

	// 타이틀 배너 출력 메소드
	public void printTitleBanner() {
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
	}

	// 타이틀 메뉴 출력 메소드
	public void printTitleMenu() {
		System.out.print("[1] 새로하기   [2] 이어하기   [3] 종료   >>  ");
	}

	// 새로하기(회원가입) 메소드
	public LoginAccount titleJoin(Scanner sc) {
		// 비어있는 로그인 정보 객체 생성
		LoginAccount login = new LoginAccount();

		// 새로하기,Join
		System.out.print("ID 입력 : ");
		String joinId = sc.next();
		System.out.print("PW 입력 : ");
		String joinPw = sc.next();
		System.out.print("농장이름 입력 : ");
		String farm = sc.next();

		// 회원가입 쿼리문
		int row = dao.join(joinId, joinPw, farm);

		int prolNum = 0;

		if (row > 0) {
			System.out.println("회원가입 성공!  프롤로그를 시청하시겠어요?");
			System.out.print("[1] 네      [2] 아니오   >>   ");
			prolNum = sc.nextInt();

			if (prolNum == 1) {
				// 프롤로그 보기
				System.out.println();
				System.out.println("나는 스인개 출신 시니어 개발자 n년차를 달리고 있었다,, \n개발자란 이런걸까,,?");
				System.out.println("내가 꿈꿔왔던 워라밸은 어디간거지,,?  （´＿｀） \n이렇게는 살 수 없어,,,!!!!!!!     \n내 꿈을 찾아 떠난다! 지!금!당!장!");
				System.out.println();
				System.out.print("게임 시작  >>  [1] 네  [2] 아니오 ");
				int ans = sc.nextInt();
				if (ans == 1) {
					// 로그인 성공 및 게임 메뉴 진입을 위해 로그인 정보 객체 생성
					login = settingIdName(login, joinId, farm);

					System.out.println();
					printWelcome(joinId);
//					System.out.println(joinId + "님 환영합니다 (=^ . ^=) \n 오늘 하루도 힘차게 시작해봐요!!");
				} else {
					System.out.println("타이틀로 돌아갑니다!");
				}

			} else if (prolNum == 2) {
				// 프롤로그 넘기기
				// 로그인 성공 및 게임 메뉴 진입을 위해 로그인 정보 객체 생성
				login = settingIdName(login, joinId, farm);

				System.out.println();
				printWelcome(joinId);
//				System.out.println(joinId + "님 환영합니다 (=^ . ^=) \n 오늘 하루도 힘차게 시작해봐요!!");
			}

		}

		return login;

	}

	// 이어하기(로그인) 메소드
	public LoginAccount titleLogin(Scanner sc) {
		// 비어있는 로그인 정보 객체 생성
		LoginAccount login = new LoginAccount();

		// 이어하기,Login
		try {
			System.out.print("ID 입력 : ");
			String loginId = sc.next();
			System.out.print("PW 입력 : ");
			String loginPw = sc.next();

			// 로그인 쿼리문
			// SELECT ID FROM ACCOUNT WHERE ID = ? AND PW = ?
			String[] loginInfo = dao.login(loginId, loginPw);

			if (loginInfo[0].equals("")) {
				// 값이 없거나 틀렸을 때
				System.out.println("로그인 실패! 다시 입력해주세요");
			} else {
				// 로그인 성공 및 게임 메뉴 진입을 위해 로그인 정보 객체 생성
				login = settingIdName(login, loginInfo[0], loginInfo[1]);

				printWelcome(loginInfo[0]);
//				System.out.println(name + "님 환영합니다 (=^ . ^=) \n오늘 하루도 힘차게 시작해봐요!!");
//				break;
			}

		} catch (Exception e) {

		}

		return login;

	}

	// 게임 메뉴 진입 전에 로그인 정보에 id, farmName 대입하는 메소드
	public LoginAccount settingIdName(LoginAccount login, String joinId, String farm) {
		login.setId(joinId);
		login.setFarmName(farm);

		return login;

	}

	// 로그인 환영 메시지
	public void printWelcome(String joinId) {
		System.out.println(joinId + "님 환영합니다 (=^ . ^=) \n 오늘 하루도 힘차게 시작해봐요!!");
	}

}
