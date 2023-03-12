package characterProject;

public class Character implements Comparable<Character> {
	public static final int COUNT = 3;
	private int id;
	private String loginId;
	private String loginPw;
	private String signInId;
	private String signInPw;
	private String name; // 이름
	private String tribe; // 종족
	private String myclass; // 클래스
	private String gender; // 성별
	private int level; // 레벨
	private int viability; // 생존력
	private int power; // 힘
	private int intelligence; // 지능
	private int total;
	private double avg;
	private String grade;

	public Character() {
	}

	public Character(String name, String tribe, String myclass, String gender, int level, int viability, int power,
			int intelligence) {
		this(0, null, null, null, null, name, tribe, myclass, gender, level, viability, power, intelligence, 0, 0,
				null);
	}

	public Character(int id, String name, int total, double avg, String grade) {
		this(id, null, null, null, null, name, null, null, null, 0, 0, 0, 0, total, avg, grade);
	}

	public Character(String loginId, String loginPw) {
		this(0, loginId, loginPw, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0.0, null);
	}

	public Character(int id, String loginId, String loginPw, String signInId, String signInPw, String name,
			String tribe, String myclass, String gender, int level, int viability, int power, int intelligence,
			int total, double avg, String grade) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.signInId = signInId;
		this.signInPw = signInPw;
		this.name = name;
		this.tribe = tribe;
		this.myclass = myclass;
		this.gender = gender;
		this.level = level;
		this.viability = viability;
		this.power = power;
		this.intelligence = intelligence;
		this.total = total;
		this.avg = avg;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPw() {
		return loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	public String getSignInId() {
		return signInId;
	}

	public void setSignInId(String signInId) {
		this.signInId = signInId;
	}

	public String getSignInPw() {
		return signInPw;
	}

	public void setSignInPw(String signInPw) {
		this.signInPw = signInPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTribe() {
		return tribe;
	}

	public void setTribe(String tribe) {
		this.tribe = tribe;
	}

	public String getMyclass() {
		return myclass;
	}

	public void setMyclass(String myclass) {
		this.myclass = myclass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getViability() {
		return viability;
	}

	public void setViability(int viability) {
		this.viability = viability;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getTotal() {
		return total;
	}

	public void calTotal() {
		this.total = this.viability + this.power + this.intelligence;
	}

	public double getAvg() {
		return avg;
	}

	public void calAvg() {
		this.avg = this.total / (double) Character.COUNT;
	}

	public String getGrede() {
		return grade;
	}

	public void calGrade() {
		switch ((int) (this.avg / 10)) {

		case 10:
		case 9:
			this.grade = "A";
			break;
		case 8:
			this.grade = "B";
			break;
		case 7:
			this.grade = "C";
			break;
		case 6:
			this.grade = "D";
			break;
		default:
			this.grade = "F";
			break;
		}
	}

//	@Override
//	public String toString() {
//		return "Character [id=" + id + ", name=" + name + ", tribe=" + tribe + ", myclass=" + myclass + ", gender="
//				+ gender + ", level=" + level + ", viability=" + viability + ", power=" + power + ", intelligence="
//				+ intelligence + ", total=" + total + ", avg=" + avg + ", grade=" + grade + "]";
//	}

	@Override

	public int compareTo(Character o) {
		if ((this.total - o.total) == 0) {
			return 0;
		} else if ((this.total - o.total) > 0) {
			return 1;
		} else {
			return -1;
		}

	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + tribe + "\t" + myclass + "\t" + level + "\t" + gender + "\t" + viability + "\t"
				+ power + "\t" + intelligence + "\t" + total + "\t" + String.format("%.2f", this.avg) + "\t" + grade;

	}
}
