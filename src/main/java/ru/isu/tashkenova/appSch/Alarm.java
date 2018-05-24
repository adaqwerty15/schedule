package ru.isu.tashkenova.appSch;

public class Alarm {
	private int uniqueId;
	private int shift;
	private int errorCode;

	public Alarm(int uniqueId, int shift, int errorCode) {
		this.uniqueId = uniqueId;
		this.shift = shift;
		this.errorCode = errorCode;
	}

	public Alarm() {
		this.errorCode = 1;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
