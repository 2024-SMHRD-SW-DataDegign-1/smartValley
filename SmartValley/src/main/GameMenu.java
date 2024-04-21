package main;

import java.util.Scanner;

public class GameMenu {

	// DB에서 게임 진행 정보 불러오기 및 저장하기 기능을 사용하기 위한 객체 생성
	private DAO dao = new DAO();

	public LoginAccount runGameMenu(LoginAccount login) {
		// 농사하기 객체 생성
		ActFarm actFarm = new ActFarm();

		// 상점 객체 생성
		ShopFunction shop = new ShopFunction();

		// 게임 메뉴에 진입해서 로그인한 계정의 게임 정보를 불러오기
		loadAccountInfo(login);

		// 게임 메뉴 진입
		int selectGameMenu = 0;
		while (selectGameMenu != 4) {
			printDayGoldInfo(login);
			selectGameMenu = printGameMenu();

			switch (selectGameMenu) {
			case 1: // 농사짓기
				login = actFarm.runActFarm(login);

				break;

			case 2: // 소지품 보기
				viewInventory(login);
				break;

			case 3: // 상점
				login = shop.shopMenu(login);
				break;

			case 4: // 돌아가기
				// DB에 게임 진행 정보 저장
				dao.saveData(login);

				System.out.println("타이틀로 돌아갑니다!");
				System.out.println();
				break;

			}

		}
		return login;

	}

	public void loadAccountInfo(LoginAccount login) {
		// DB에서 게임 진행 정보 불러오기
		login = dao.loadData(login);

	}

	// 농장명, 날짜, 소지 골드 정보 출력
	public void printDayGoldInfo(LoginAccount login) {
		System.out.println();
		System.out.println("==============================================================");
		System.out
				.println(login.getFarmName() + "\t" + login.getGameDay() + "일차\t" + "소지 골드: " + login.getGold() + "G");

	}

	// 게임 메뉴 출력
	public int printGameMenu() {
		Scanner sc = new Scanner(System.in);

		System.out.println("======================메뉴를 선택하세요.======================");
		System.out.print("[1] 농사짓기  [2] 소지품 보기  [3] 상점  [4] 돌아가기  >> ");
		int selectMenu = sc.nextInt();

		return selectMenu;

	}

	public void viewInventory(LoginAccount login) {
		// 아이템 보유량 출력
		System.out.println();
		System.out.println("==============소지품==============");
		System.out.println("아이템명\t씨앗\t수확물");
		System.out.println("당근\t\t" + login.getCrsdCount() + "\t" + login.getCrCount());
		System.out.println("토마토\t\t" + login.getTmtsdCount() + "\t" + login.getTmtCount());
		System.out.println("무\t\t" + login.getRdsdCount() + "\t" + login.getRdCount());
		System.out.println("호박\t\t" + login.getPksdCount() + "\t" + login.getPkCount());
		System.out.println("==================================");

	}

}
