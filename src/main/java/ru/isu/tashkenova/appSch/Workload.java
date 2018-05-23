package ru.isu.tashkenova.appSch;

public class Workload {
	int uniqueId;
	int studentClassId;
	int userId;
	int subjectId;
	int errorCode;

	public Workload(int uniqueId, int studentClassId, int userId, int subjectId, int errorCode) {
		this.uniqueId = uniqueId;
		this.studentClassId = studentClassId;
		this.userId = userId;
		this.subjectId = subjectId;
		this.errorCode = errorCode;
	}

	public Workload(){
		this.errorCode = 1;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getStudentClassId() {
		return studentClassId;
	}

	public void setStudentClassId(int studentClassId) {
		this.studentClassId = studentClassId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
