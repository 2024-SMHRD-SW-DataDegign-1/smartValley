package main;

import java.util.Scanner;

public class ShopMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// LoginAccount 테이블과 연결
		LoginAccount login = new LoginAccount();

		int gold = login.getGold();
		int crCount = login.getCrCount();
		int tmtCount = login.getTmtCount();
		int rdCount = login.getRdCount();
		int pkCount = login.getPkCount();

		int crsdCount = login.getCrsdCount();
		int tmtsdCount = login.getTmtsdCount();
		int rdsdCount = login.getRdsdCount();
		int pksdCount = login.getPksdCount();

		ShopFunction shop = new ShopFunction();
		shop.shopMenu(gold, crCount, tmtCount, rdCount, pkCount, crsdCount, tmtsdCount, rdsdCount, pksdCount);

	}

}
