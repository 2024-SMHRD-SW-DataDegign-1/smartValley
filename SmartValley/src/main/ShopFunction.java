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
	public void shopMenu(int gold, int crCount, int tmtCount, int rdCount, int pkCount, int crsdCount, int tmtsdCount,
			int rdsdCount, int pksdCount) {
	
		while (true) {
			System.out.print("[1] 씨앗 구매 [2]수확물 판매 [3]이전으로 >> ");
			int shopmenuNum = sc.nextInt();
			if (shopmenuNum == 1) {
				int[] array = seedBuy(gold, crsdCount, tmtsdCount, rdsdCount, pksdCount);
				gold = array[0];
				crsdCount = array[1];
				tmtsdCount = array[2];
				rdsdCount = array[3];
				pksdCount = array[4];
			} else if (shopmenuNum == 2) {
				int[] array = harvSell(gold, crCount, tmtCount, rdCount, pkCount);
				gold = array[0];
				crCount = array[1];
				tmtCount = array[2];
				rdCount = array[3];
				pkCount = array[4];
			} else if (shopmenuNum == 3) {
				// 3) 이전으로
				// goback();
			} else {
				System.out.println("원하는 메뉴의 번호를 정확하게 입력해주세요!");
			}

		}
	}

	// 씨앗 구매 기능
	public int[] seedBuy(int gold, int crsdCount, int tmtsdCount, int rdsdCount, int pksdCount) {
		int[] array = new int[5];
		// 씨앗 구매

		// 1) 구매할 작물 씨앗 선택
		System.out.println("구매할 씨앗을 선택하세요!");
		System.out.print("[1] 당근 500G  [2] 토마토 300G  [3] 무 400G  [4] 호박 600G  >> ");
		int seedmenuNum = sc.nextInt();

		// 1-1) 소지한 골드가 작물 씨앗 가격보다 많을 때
		if (gold >= tmtsdPrice) { // 1차 판별 : 가장 저렴한 씨앗의 가격보다 소지한 골드가 더 많은지
			if (seedmenuNum == 1 && gold >= crsdPrice) {
				gold -= crsdPrice; // 가지고있는 골드에서 산 씨앗 가격만큼 빼줌
				crsdCount++; // 산 씨얏의 갯수를 1 올려줌
				System.out.println("당근 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + crsdCount + "개");
				System.out.println("토마토\t" + tmtsdCount + "개");
				System.out.println("무\t" + rdsdCount + "개");
				System.out.println("호박\t" + pksdCount + "개");
				System.out.println("소지골드 " + gold + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 2 && gold >= tmtsdPrice) {
				gold -= tmtsdPrice;
				tmtsdCount++;
				System.out.println("토마토 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + crsdCount + "개");
				System.out.println("토마토\t" + tmtsdCount + "개");
				System.out.println("무\t" + rdsdCount + "개");
				System.out.println("호박\t" + pksdCount + "개");
				System.out.println("소지골드 " + gold + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 3 && gold >= rdsdPrice) {
				gold -= rdsdPrice;
				rdsdCount++;
				System.out.println("무 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + crsdCount + "개");
				System.out.println("토마토\t" + tmtsdCount + "개");
				System.out.println("무\t" + rdsdCount + "개");
				System.out.println("호박\t" + pksdCount + "개");
				System.out.println("소지골드 " + gold + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 4 && gold >= pksdPrice) {
				gold -= pksdPrice;
				pksdCount++;
				System.out.println("호박 씨앗 구매 완료 ! ");
				System.out.println("==========");
				System.out.println("씨앗종류\t개수");
				System.out.println("당근\t" + crsdCount + "개");
				System.out.println("토마토\t" + tmtsdCount + "개");
				System.out.println("무\t" + rdsdCount + "개");
				System.out.println("호박\t" + pksdCount + "개");
				System.out.println("소지골드 " + gold + "G");
				System.out.println("==========");
			} else if (seedmenuNum == 1 && gold < crsdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else if (seedmenuNum == 2 && gold < tmtsdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else if (seedmenuNum == 3 && gold < rdsdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else if (seedmenuNum == 4 && gold < pksdPrice) {
				System.out.println("씨앗을 구매할 골드가 부족합니다 ! ");
			} else {
				System.out.println("구매하고 싶은 씨앗의 번호를 정확하게 입력해주세요!");
			}

			array[0] = gold;
			array[1] = crsdCount;
			array[2] = tmtsdCount;
			array[3] = rdsdCount;
			array[4] = pksdCount;

		}
		// 1-2) 소지한 골드가 작물 씨앗 가격보다 적을 때
		else {
			System.out.println("골드가 부족합니다 ! 수확물을 판매 후 다시 시도하세요 !");
		}
		return array;
	}

	// 수확물 판매 기능
	public int[] harvSell(int gold, int crCount, int tmtCount, int rdCount, int pkCount) {
		int[] array = new int[5];
		// 2) 수확물 판매
		// 판매할 수확물이 하나라도 존재할때 혹은 존재하지 않을 때로 조건문
		// 수확물이 하나라도 존재할 때
		if (crCount + tmtCount + rdCount + pkCount != 0) {
			int salTotal = (crCount * crsalPrice) + (tmtCount * tmtsalPrice) + (rdCount * rdsalPrice)
					+ (pkCount * pksalPrice);
			gold += salTotal;
			crCount = 0;
			tmtCount = 0;
			rdCount = 0;
			pkCount = 0;
			array[0] = gold;
			array[1] = crCount;
			array[2] = tmtCount;
			array[3] = rdCount;
			array[4] = pkCount;

			// 팔았으니까 가지고 있던 수확물 갯수 0으로 초기화
			System.out.println("소지하고 있는 수확물을 전부 판매했습니다 ! " + "소지한 골드 : " + gold + "G");
			// 소지한 수확물이 없을 때
		} else {
			System.out.println("판매할 수확물이 존재하지 않습니다 !");
		}
		return array;
	}

	// 이전으로 기능
	public void goBack() {

	}
}
