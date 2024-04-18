package main;

public class LoginAccount {
	// 로그인 한 계정의 게임 정보를 관리하는 클래스
	
	
	private int gold = 3000; // 재화(골드)
    
	// 보유한 수확물 개수
	private int crCount = 1; // 당근
    private int tmtCount = 1; // 토마토
    private int rdCount = 1; // 무
    private int pkCount = 1; // 호박

    // 보유한 씨앗 개수
    private int crsdCount = 0; // 당근
    private int tmtsdCount = 0; // 토마토
    private int rdsdCount = 0; // 무
    private int pksdCount = 0; // 호박
	
    // Getter, Setter
    public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
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
