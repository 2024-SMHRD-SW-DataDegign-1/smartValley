package main;

public class LoginAccount {

	// 게임 종료 여부 정보
	private boolean gameOver = false;
	
	// ID 정보
	private String id = "";
	private String farmName = "";
	
	// 날짜 정보
	private int gameDay = 1;
	
	// 골드 정보
	private int gold = 0; // 재화(골드)
	
	// 밭 정보
	private int condiFarm = 0; // 밭 상태
	private String plantName = ""; // 심어진 작물명
	
	// 작물 정보
	private int cntWater = 0;
	private boolean isHarvest = Boolean.parseBoolean("false");
    
	// 보유한 수확물 개수
	private int crCount = 0; // 당근
    private int tmtCount = 0; // 토마토
    private int rdCount = 0; // 무
    private int pkCount = 0; // 호박

    // 보유한 씨앗 개수
    private int crsdCount = 0; // 당근
    private int tmtsdCount = 0; // 토마토
    private int rdsdCount = 0; // 무
    private int pksdCount = 0; // 호박
	
    // Getter, Setter
	public boolean getGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFarmName() {
		return farmName;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
    public int getGameDay() {
		return gameDay;
	}
	public void setGameDay(int gameDay) {
		this.gameDay = gameDay;
	}
    public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getCondiFarm() {
		return condiFarm;
	}
	public void setCondiFarm(int condiFarm) {
		this.condiFarm = condiFarm;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public int getCntWater() {
		return cntWater;
	}
	public void setCntWater(int cntWater) {
		this.cntWater = cntWater;
	}
	public boolean getIsHarvest() {
		return isHarvest;
	}
	public void setIsHarvest(boolean isHarvest) {
		this.isHarvest = isHarvest;
	}
	public int getCrCount() {
		return crCount;
	}
	public void setCrCount(int crCount) {
		this.crCount = crCount;
	}
	public int getTmtCount() {
		return tmtCount;
	}
	public void setTmtCount(int tmtCount) {
		this.tmtCount = tmtCount;
	}
	public int getRdCount() {
		return rdCount;
	}
	public void setRdCount(int rdCount) {
		this.rdCount = rdCount;
	}
	public int getPkCount() {
		return pkCount;
	}
	public void setPkCount(int pkCount) {
		this.pkCount = pkCount;
	}
	public int getCrsdCount() {
		return crsdCount;
	}
	public void setCrsdCount(int crsdCount) {
		this.crsdCount = crsdCount;
	}
	public int getTmtsdCount() {
		return tmtsdCount;
	}
	public void setTmtsdCount(int tmtsdCount) {
		this.tmtsdCount = tmtsdCount;
	}
	public int getRdsdCount() {
		return rdsdCount;
	}
	public void setRdsdCount(int rdsdCount) {
		this.rdsdCount = rdsdCount;
	}
	public int getPksdCount() {
		return pksdCount;
	}
	public void setPksdCount(int pksdCount) {
		this.pksdCount = pksdCount;
	}
	
    
	
}
