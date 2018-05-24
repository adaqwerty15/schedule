package ru.isu.tashkenova.appSch;

public class Role {
	int uniqueId;
	String name;
	boolean classAccess;
	boolean scheduleAccess;
	boolean usersAccess;
	int errorCode;

	public Role(int uniqueId, String name, boolean classAccess, boolean scheduleAccess, boolean usersAccess, int errorCode) {
		this.uniqueId = uniqueId;
		this.name = name;
		this.classAccess = classAccess;
		this.scheduleAccess = scheduleAccess;
		this.usersAccess = usersAccess;
		this.errorCode = errorCode;
	}

	public Role(){
		this.errorCode = 1;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClassAccess() {
		return classAccess;
	}

	public void setClassAccess(boolean classAccess) {
		this.classAccess = classAccess;
	}

	public boolean isScheduleAccess() {
		return scheduleAccess;
	}

	public void setScheduleAccess(boolean scheduleAccess) {
		this.scheduleAccess = scheduleAccess;
	}

	public boolean isUsersAccess() {
		return usersAccess;
	}

	public void setUsersAccess(boolean usersAccess) {
		this.usersAccess = usersAccess;
	}
}
