package main;

import javazoom.jl.player.MP3Player;

public class Main {

	public static void main(String[] args) {

		// 노래 재생 도구
		MP3Player bgm = new MP3Player();
		bgm.play(".\\bgm\\Valley.mp3"); // 상대경로로 변경

		// 타이틀 메뉴 객체 생성
		TitleMenu titleMenu = new TitleMenu();

		// 게임 메뉴 객체 생성
		GameMenu gameMenu = new GameMenu();

		// 비어있는 로그인 정보 객체 생성
		LoginAccount login = new LoginAccount();
		
		while (!login.getGameOver()) {
			// 타이틀 메뉴 진입
			login = titleMenu.runTitleMenu();

			// runTitleMenu()에서 
			// 프롤로그 시청 여부에서 시청한 뒤 게임 시작을 하지 않은 경우, id 값이 없는 login 객체가 리턴됨
			// 이 경우, 게임 메뉴로 진입하지 않으며, while문 반복으로 인해 다시 타이틀 메뉴로 진입
			
			if (login.getId().equals("")) {

			} else {
				// 게임 메뉴 진입(타이틀 메뉴 기능 클래스화 완료 후, 매개변수에 login 추가하고, 반환값을 login에 대입하도록 변경할 것)
				login = gameMenu.runGameMenu(login);
				
			}
			
		}
		
		// 게임 종료 전 bgm 정지
		bgm.stop();

		// 게임 종료 메시지 출력
		System.out.println("게임이 종료되었습니다. Good Game... ・ᴗ・)!");
	}

}
