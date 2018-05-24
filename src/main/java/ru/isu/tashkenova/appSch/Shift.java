package ru.isu.tashkenova.appSch;

public class Shift {
	private int uniqueId;
	private int number;
	private int startTime;
	private int endTime;
	private int errorCode;

	public Shift(int uniqueId, int number, int startTime, int endTime, int errorCode) {
		this.uniqueId = uniqueId;
		this.number = number;
		this.startTime = startTime;
		this.endTime = endTime;
		this.errorCode = errorCode;
	}

	public Shift(){
		this.errorCode = 1;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
