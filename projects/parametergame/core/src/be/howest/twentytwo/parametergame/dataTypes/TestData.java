package be.howest.twentytwo.parametergame.dataTypes;

public class TestData {
	
	private int number;
	private String letter;
	private boolean bool;
	
	public TestData() {
		number = 1;
		letter = "a";
		bool = true;
	}
	
	//	SETTERS

	public void setNumber(int number) {
		this.number = number;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	//	GETTERS

	public int getNumber() {
		return number;
	}

	public String getLetter() {
		return letter;
	}

	public boolean getBool() {
		return bool;
	}

}
