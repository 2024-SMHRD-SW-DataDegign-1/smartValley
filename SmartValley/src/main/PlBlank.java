package main;

public class PlBlank extends Plant {
	// Farm 클래스의 Plant pl 값이 null이면 에러가 발생하므로
	// 심어진 작물이 없는 상태인 본 클래스를 생성하여 사실상의 null을 대체
	
	@Override
	public void setField() {
		cntReach = 0;
		plantName = "";
		
	}

}
