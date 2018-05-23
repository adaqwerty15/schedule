package ru.isu.tashkenova.appSch;

public class Day {
	int uniqueId;
	String day;
	int errorCode;

	public Day(int uniqueId, String day, int errorCode){
		this.uniqueId = uniqueId;
		this.day = day;
		this.errorCode = errorCode;
	}

	public Day() {
		this.errorCode = 1;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public String getDay() {
		return day;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
