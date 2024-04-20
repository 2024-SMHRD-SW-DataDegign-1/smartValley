package main;

import java.util.Scanner;

public class GameMenu {

	public LoginAccount runGameMenu(LoginAccount login) {
//		// 로그인한 계정 객체 생성
//		LoginAccount login = new LoginAccount();
		
		// 농사하기 객체 생성
		ActFarm actFarm = new ActFarm();
		
		// 상점 객체 생성
		ShopFunction shop = new ShopFunction();
		
//		// 농장 객체 생성
//		Farm farm = new Farm();
		
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
				System.out.println("타이틀로 돌아갑니다!");
				System.out.println();
				break;
			
			}
			
			
		}
		
		return login;
		
	}
	
	public void loadAccountInfo(LoginAccount login) {

		// 골드 보유량 불러오기
		login.setGold(5000);

		// 밭 정보 불러오기
		login.setCondiFarm(0);
		login.setPlantName("");
		
		// 작물 정보 불러오기
		login.setCntWater(0);
		login.setIsHarvest(false);
		
		// 수확물 보유량 불러오기
		login.setCrCount(1);
		login.setTmtCount(1);
		login.setRdCount(1);
		login.setPkCount(1);

		// 씨앗 보유량 불러오기
		login.setCrsdCount(1);
		login.setTmtsdCount(1);
		login.setRdsdCount(1);
		login.setPksdCount(1);

		
		System.out.println("불러오기 성공!");

	}

	// 농장명, 날짜, 소지 골드 정보 출력
	public void printDayGoldInfo(LoginAccount login) {
		System.out.println();
		System.out.println("==============================================================");
		System.out.println(login.getFarmName() + "\t" + login.getGameDay() + "일차\t" + "소지 골드: " + login.getGold() + "G");
		
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

		// 로그인한 멤버의 객체에서 id 값을 받아오면
		// 인벤토리 테이블에서 그 id 값에 해당하는 row를 찾아서 ResultSet 타입 객체 inv를 반환
		// 그럼 그 inv에서 .getInt()해서 값을 출력

		// 인벤토리 테이블의 객체 inv가 있어야 함
//		ResultSet inv;

		// 아이템 보유량 출력
		System.out.println();
		System.out.println("==============소지품==============");
		System.out.println("아이템명\t씨앗\t수확물");
		System.out.println("당근\t\t" + login.getCrsdCount() + "\t" + login.getCrCount());
		System.out.println("토마토\t\t" + login.getTmtsdCount() + "\t" + login.getTmtCount());
		System.out.println("무\t\t" + login.getRdsdCount() + "\t" + login.getRdCount());
		System.out.println("호박\t\t" + login.getPksdCount() + "\t" + login.getPkCount());
		System.out.println("==================================");
		
//		while (inv.next()) {
//			System.out.print(inv.getString(2) + "\t\t");
//			System.out.print(inv.getInt(3) + "\t"); // 씨앗 개수
//			System.out.print(inv.getInt(4)); // 수확물 개수
//		}

	}

}
