package main;

public abstract class Plant {
	// 작물 추상 클래스

	private int cntWater = 0;
	protected int cntReach = 99;
	private boolean isHarvest = false;
	protected String plantName;

	// 각 하위 클래스마다 cntReach, plantName 값을 변경하기 위한 추상 메소드
	public abstract void setField();

	// 다 자란 상태로 바꾸는 메소드
	public boolean grow() {
		boolean result = false;
		if (cntWater >= cntReach) {
			isHarvest = true;
			result = true;
		}
		return result;
		
	}

	// Getter, Setter
	public int getCntWater() {
		return cntWater;
	}

	public void setCntWater(int cntWater) {
		this.cntWater = cntWater;
	}

	public int getCntReach() {
		return cntReach;
	}

	public void setCntReach(int cntReach) {
		this.cntReach = cntReach;
	}

	public boolean getIsHarvest() {
		return isHarvest;
	}

	public void setIsHarvest(boolean isHarvest) {
		this.isHarvest = isHarvest;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

}
