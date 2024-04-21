package main;

import java.util.Scanner;

public class ActFarm {

	private DAO dao = new DAO();

	// 농사짓기 메뉴 실행 메소드
	public LoginAccount runActFarm(LoginAccount login) {
		// 농장, 작물 정보 불러오기
		Farm farm = loadFarm(login);

		int selectActMenu = 0;

		while (selectActMenu != 5) {
			selectActMenu = printActFarmMenu();

			switch (selectActMenu) {
			case 1: // 개간
				farmClearning(login, farm);
				break;

			case 2: // 씨앗심기
				farmSowing(login, farm);
				break;

			case 3: // 물 뿌리기
				farmWatering(login, farm);
				break;

			case 4: // 수확하기
				farmHarvesting(login, farm);
				break;

			case 5: // 이전으로
				System.out.println("게임 메뉴로 돌아갑니다.");
				break;

			}

		}
		return login;

	}

	// 농사짓기 메뉴 출력
	public int printActFarmMenu() {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("======================활동을 선택하세요.======================");
		System.out.print("[1] 개간  [2] 씨앗심기  [3] 물 뿌리기  [4] 수확하기  [5] 이전으로  >> ");
		int selectMenu = sc.nextInt();

		return selectMenu;
	}

	// 농장 정보 불러오기
	public Farm loadFarm(LoginAccount login) {
		Farm farm = new Farm();
		farm.setCondiFarm(login.getCondiFarm());

		switch (login.getPlantName()) {
		case "당근":
			farm.setPl(new PlCarrot());
			farm.getPl().setField();
			farm.getPl().setCntWater(login.getCntWater());
			farm.getPl().setIsHarvest(login.getIsHarvest());
			break;

		case "토마토":
			farm.setPl(new PlTomato());
			farm.getPl().setField();
			farm.getPl().setCntWater(login.getCntWater());
			farm.getPl().setIsHarvest(login.getIsHarvest());
			break;

		case "무":
			farm.setPl(new PlRadish());
			farm.getPl().setField();
			farm.getPl().setCntWater(login.getCntWater());
			farm.getPl().setIsHarvest(login.getIsHarvest());
			break;

		case "호박":
			farm.setPl(new PlPumpkin());
			farm.getPl().setField();
			farm.getPl().setCntWater(login.getCntWater());
			farm.getPl().setIsHarvest(login.getIsHarvest());
			break;

		case "":
			farm.setPl(new PlBlank());
			farm.getPl().setField();
			farm.getPl().setCntWater(0);
			farm.getPl().setIsHarvest(false);

			break;

		}
		return farm;

	}

	// 개간
	public void farmClearning(LoginAccount login, Farm farm) {
		if (farm.getCondiFarm() == 0) {
			farm.setCondiFarm(1); // 밭 상태를 "개간됨"으로 변경
			login.setCondiFarm(farm.getCondiFarm());
			System.out.println("밭을 일구었습니다. 씨앗을 심을 수 있습니다.");

			nextDay(login);

		} else {
			System.out.println("개간 할 수 있는 상태가 아닙니다.");

		}

	}

	// 씨앗심기
	public void farmSowing(LoginAccount login, Farm farm) {
		Scanner sc = new Scanner(System.in);

		if (farm.getCondiFarm() == 1) {
			System.out.println();
			System.out.println("====================어떤 씨앗을 심을까요?=====================");
			System.out.print("[1] 당근 씨앗  [2] 토마토 씨앗  [3] 무 씨앗  [4] 호박 씨앗  [5] 이전으로  >> ");
			int seedNum = sc.nextInt();

			switch (seedNum) {
			case 1:
				if (login.getCrsdCount() > 0) {
					login.setCrsdCount(login.getCrsdCount() - 1);
					farm.setPl(new PlCarrot());
					farm.getPl().setField();
					System.out.println("밭에 당근 씨앗을 심었습니다.");

					login.setPlantName("당근");

					farm.setCondiFarm(2); // 밭 상태를 "심어짐"으로 변경
					login.setCondiFarm(farm.getCondiFarm());

					nextDay(login);

				} else {
					System.out.println("당근 씨앗이 부족합니다.");

				}
				break;

			case 2:
				if (login.getTmtsdCount() > 0) {
					login.setTmtsdCount(login.getTmtsdCount() - 1);
					farm.setPl(new PlTomato());
					farm.getPl().setField();
					System.out.println("밭에 토마토 씨앗을 심었습니다.");

					login.setPlantName("토마토");

					farm.setCondiFarm(2); // 밭 상태를 "심어짐"으로 변경
					login.setCondiFarm(farm.getCondiFarm());

					nextDay(login);

				} else {
					System.out.println("토마토 씨앗이 부족합니다.");

				}
				break;

			case 3:
				if (login.getRdsdCount() > 0) {
					login.setRdsdCount(login.getRdsdCount() - 1);
					farm.setPl(new PlRadish());
					farm.getPl().setField();
					System.out.println("밭에 무 씨앗을 심었습니다.");

					login.setPlantName("무");

					farm.setCondiFarm(2); // 밭 상태를 "심어짐"으로 변경
					login.setCondiFarm(farm.getCondiFarm());

					nextDay(login);

				} else {
					System.out.println("무 씨앗이 부족합니다.");

				}
				break;

			case 4:
				if (login.getPksdCount() > 0) {
					login.setPksdCount(login.getPksdCount() - 1);
					farm.setPl(new PlPumpkin());
					farm.getPl().setField();
					System.out.println("밭에 호박 씨앗을 심었습니다.");

					login.setPlantName("호박");

					farm.setCondiFarm(2); // 밭 상태를 "심어짐"으로 변경
					login.setCondiFarm(farm.getCondiFarm());

					nextDay(login);

				} else {
					System.out.println("호박 씨앗이 부족합니다.");

				}
				break;

			case 5:
				System.out.println();
				break;
			}

		} else {
			System.out.println("씨를 심을 수 있는 상태가 아닙니다.");

		}

	}

	// 물 뿌리기
	public void farmWatering(LoginAccount login, Farm farm) {
		if (farm.getCondiFarm() == 2) {
			farm.getPl().setCntWater(farm.getPl().getCntWater() + 1);
			login.setCntWater(farm.getPl().getCntWater());
			System.out.println("씨앗에 물을 뿌렸습니다.");
			System.out.println("물을 뿌린 회수 : " + farm.getPl().getCntWater());

			nextDay(login);
			isGrow(login, farm);

		} else {
			System.out.println("물을 뿌릴 수 있는 상태가 아닙니다.");

		}

	}

	// 수확하기
	public void farmHarvesting(LoginAccount login, Farm farm) {
		if (farm.getCondiFarm() == 3 && farm.getPl().getIsHarvest()) {
			// 수확하려는 작물명 추출
			String plName = farm.getPl().getPlantName();

			// 추출한 작물명에 따라 계정 정보의 해당 작물의 수확물 보유량 1 증가
			switch (plName) {
			case "당근":
				login.setCrCount(login.getCrCount() + 1);
				System.out.println("당근을 수확했습니다.");
				break;

			case "토마토":
				login.setTmtCount(login.getTmtCount() + 1);
				System.out.println("토마토를 수확했습니다.");
				break;

			case "무":
				login.setRdCount(login.getRdCount() + 1);
				System.out.println("무를 수확했습니다.");
				break;

			case "호박":
				login.setPkCount(login.getPkCount() + 1);
				System.out.println("호박을 수확했습니다.");
				break;

			}

			// 수확 후 뒷처리
			farm.setCondiFarm(0); // 땅 상태를 '개간필요'로 상태 변경
			farm.getPl().setIsHarvest(false); // 수확했으니 수확이 불가능하도록 상태 변경
			farm.setPl(new PlBlank()); // 농장의 작물을 제거

			// 뒷처리 결과를 계정 정보에 저장
			login.setPlantName("");
			login.setCondiFarm(farm.getCondiFarm());
			login.setCntWater(0);
			login.setIsHarvest(false);

			nextDay(login);

		} else {
			System.out.println("수확 할 수 있는 작물이 없습니다.");

		}

	}

	// 날짜 변경
	public void nextDay(LoginAccount login) {
		login.setGameDay(login.getGameDay() + 1); // 날짜 하루 넘김
		dao.saveData(login);

		System.out.println();
		System.out.println(login.getGameDay() + "일차");
		System.out.println(login.getId() + "님 오늘 하루도 힘차게!");

	}

	// 작물 성장시킬지 여부 판단
	public void isGrow(LoginAccount login, Farm farm) {
		// cntWater >= cntReach일 때 isHarvest를 true로 변경
		if (farm.getPl().getCntWater() >= farm.getPl().getCntReach()) {
			farm.setCondiFarm(3);
			farm.getPl().setIsHarvest(true);

			login.setCondiFarm(farm.getCondiFarm());
			login.setIsHarvest(farm.getPl().getIsHarvest());

			dao.saveData(login);

			System.out.println(farm.getPl().getPlantName() + "을(를) 수확 할 수 있습니다!");

		}

	}

}
