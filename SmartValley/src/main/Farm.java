package main;

public class Farm {
	// 밭 클래스

	// 밭 상태
	// 0: 개간필요, 1: 개간됨, 2: 심어짐, 3: 수확가능
	private int condiFarm = 0;

	private Plant pl = new PlBlank();

	// Getter, Setter
	public int getCondiFarm() {
		return condiFarm;
	}
	public void setCondiFarm(int condiFarm) {
		this.condiFarm = condiFarm;
	}

	public Plant getPl() {
		return pl;
	}
	public void setPl(Plant pl) {
		this.pl = pl;
	}

}
