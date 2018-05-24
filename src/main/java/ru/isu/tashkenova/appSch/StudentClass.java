package ru.isu.tashkenova.appSch;

public class StudentClass {
	private String code;
	private int course;
	private int uniqueId;
	private int shiftId;
	private int errorCode;

	public StudentClass(String code, int course, int uniqueId, int shiftId, int errorCode){
		this.code = code;
		this.course = course;
		this.uniqueId = uniqueId;
		this.shiftId = shiftId;
		this.errorCode = errorCode;
	}

	public StudentClass(){
		this.errorCode = 1;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getCourse() {
		return course;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public int getShiftId() {
		return shiftId;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public String getCode() {
		return code;
	}
}
