package main;

import java.util.Scanner;

public class ShopFunction {
	Scanner sc = new Scanner(System.in);

	private int crsdPrice = 500; // 당근 씨앗 가격 (500골드)
	private int tmtsdPrice = 300; // 토마토 씨앗 가격(300골드)
	private int rdsdPrice = 400; // 무 씨앗 가격(400골드)
	private int pksdPrice = 600; // 호박 씨앗 가격(600골드)
	
	private int crsalPrice = 1500; // 당근 판매 가격 (1500골드)
	private int tmtsalPrice = 900; // 토마토 판매 가격 (900골드)
	private int rdsalPrice = 1200; // 무 판매 가격 (1200골드)
	private int pksalPrice = 1800; // 호박 판매 가격 (1800골드)

	// 상점 메뉴 기능
	public LoginAccount shopMenu(LoginAccount login) {
	
		int shopmenuNum = 0;
		
		while (shopmenuNum != 3) {
			System.out.println();
			System.out.println("===================[상점]===================");
			System.out.print("[1] 씨앗 구매  [2]수확물 판매  [3]이전으로 >> ");
			shopmenuNum = sc.nextInt();
			if (shopmenuNum == 1) {
				int[] array = seedBuy(login);
				login.setGold(array[0]);
				login.setCrsdCount(array[1]);
				login.setTmtsdCount(array[2]);
				login.setRdsdCount(array[3]);
				login.setPksdCount(array[4]);
			} else if (shopmenuNum == 2) {
				int[] array = harvSell(login);
				login.setGold(array[0]);
				login.setCrCount(array[1]);
				login.setTmtCount(array[2]);
				login.setRdCount(array[3]);
				login.setPkCount(array[4]);
			} else if (shopmenuNum == 3) {
				System.out.println("게임 메뉴로 돌아갑니다.");
				
			} else {
				System.out.println("원하는 메뉴의 번호를 정확하게 입력해주세요!");
				
			}

		}
		
		return login;
		
	}

	// 씨앗 구매 기능
	public int[] seedBuy(LoginAccount login) {
		int[] array = new int[5];
		// 씨앗 구매

		// 1) 구매할 작물 씨앗 선택
		System.out.println();
		System.out.println("구매할 씨앗을 선택하세요!");
		System.out.print("[1] 당근 500G  [2] 토마토 300G  [3] 무 400G  [4] 호박 600G  >> ");
		int seedmenuNum = sc.nextInt();

		// 1-1) 소지한 골드가 작물 씨앗 가격보다 많을 때
		if (login.getGold() >= tmtsdPrice) { // 1차 판별 : 가장 저렴한 씨앗의 가격보다 소지한 골드가 더 많은지
			if (seedmenuNum == 1 && login.getGold() >= crsdPrice) {
				login.setGold(login.getGold() - crsdPrice); // 가지고있는 골드에서 산 씨앗 가격만큼 빼줌
				login.setCrsdCount(login.getCrsdCount() + 1); // 산 씨앗의 갯수를 1 올려줌
				System.out.println("당근 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + login.getCrsdCount() + "개");
				System.out.println("토마토\t" + login.getTmtsdCount() + "개");
				System.out.println("무\t" + login.getRdsdCount() + "개");
				System.out.println("호박\t" + login.getPksdCount() + "개");
				System.out.println("소지골드 " + login.getGold() + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 2 && login.getGold() >= tmtsdPrice) {
				login.setGold(login.getGold() - tmtsdPrice);
				login.setTmtsdCount(login.getTmtsdCount() + 1);
				System.out.println("토마토 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + login.getCrsdCount() + "개");
				System.out.println("토마토\t" + login.getTmtsdCount() + "개");
				System.out.println("무\t" + login.getRdsdCount() + "개");
				System.out.println("호박\t" + login.getPksdCount() + "개");
				System.out.println("소지골드 " + login.getGold() + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 3 && login.getGold() >= rdsdPrice) {
				login.setGold(login.getGold() - rdsdPrice);
				login.setRdsdCount(login.getRdsdCount() + 1);
				System.out.println("무 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + login.getCrsdCount() + "개");
				System.out.println("토마토\t" + login.getTmtsdCount() + "개");
				System.out.println("무\t" + login.getRdsdCount() + "개");
				System.out.println("호박\t" + login.getPksdCount() + "개");
				System.out.println("소지골드 " + login.getGold() + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 4 && login.getGold() >= pksdPrice) {
				login.setGold(login.getGold() - pksdPrice);
				login.setPksdCount(login.getPksdCount() + 1);
				System.out.println("호박 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + login.getCrsdCount() + "개");
				System.out.println("토마토\t" + login.getTmtsdCount() + "개");
				System.out.println("무\t" + login.getRdsdCount() + "개");
				System.out.println("호박\t" + login.getPksdCount() + "개");
				System.out.println("소지골드 " + login.getGold() + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 1 && login.getGold() < crsdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else if (seedmenuNum == 2 && login.getGold() < tmtsdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else if (seedmenuNum == 3 && login.getGold() < rdsdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else if (seedmenuNum == 4 && login.getGold() < pksdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else {
				System.out.println("구매하고 싶은 씨앗의 번호를 정확하게 입력해주세요!");
			}

			array[0] = login.getGold();
			array[1] = login.getCrsdCount();
			array[2] = login.getTmtsdCount();
			array[3] = login.getRdsdCount();
			array[4] = login.getPksdCount();

		}
		// 1-2) 소지한 골드가 작물 씨앗 가격보다 적을 때
		else {
			System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
		}
		return array;
	}

	// 수확물 판매 기능
	public int[] harvSell(LoginAccount login) {
		int[] array = new int[5];
		// 2) 수확물 판매
		// 판매할 수확물이 하나라도 존재할때 혹은 존재하지 않을 때로 조건문
		// 수확물이 하나라도 존재할 때
		if (login.getCrCount() + login.getTmtCount() + login.getRdCount() + login.getPkCount() != 0) {
			int salTotal = (login.getCrCount() * crsalPrice) + (login.getTmtCount() * tmtsalPrice) + (login.getRdCount() * rdsalPrice)
					+ (login.getPkCount() * pksalPrice);
			login.setGold(login.getGold() + salTotal);
			login.setCrCount(0);
			login.setTmtCount(0);
			login.setRdCount(0);
			login.setPkCount(0);
			array[0] = login.getGold();
			array[1] = login.getCrCount();
			array[2] = login.getTmtCount();
			array[3] = login.getRdCount();
			array[4] = login.getPkCount();

			// 팔았으니까 가지고 있던 수확물 갯수 0으로 초기화
			System.out.println("소지하고 있는 수확물을 전부 판매했습니다 ! " + "소지한 골드 : " + login.getGold() + "G");
			// 소지한 수확물이 없을 때
		} else {
			// array[]의 값이 현재 전부 0이기 때문에, 현재 로그인 계정의 정보를 array[]에 담아야 골드가 0이 되는 문제가 발생하지 않음
			array[0] = login.getGold();
			array[1] = login.getCrCount();
			array[2] = login.getTmtCount();
			array[3] = login.getRdCount();
			array[4] = login.getPkCount();
			
			System.out.println("판매할 수확물이 존재하지 않습니다 !");
		}
		return array;
	}

}
