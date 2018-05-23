package ru.isu.tashkenova.appSch;

public class ScheduleOwners {
	int uniqueId;
	int numberOfTheSchemaId;
	int userOwnerId;
	boolean main;
	String name;
	int errorCode;

	public ScheduleOwners(int uniqueId, int numberOfTheSchemaId, int userOwnerId, boolean main, String name, int errorCode) {
		this.uniqueId = uniqueId;
		this.numberOfTheSchemaId = numberOfTheSchemaId;
		this.userOwnerId = userOwnerId;
		this.main = main;
		this.name = name;
		this.errorCode = errorCode;
	}

	public  ScheduleOwners(){
		this.errorCode = 1;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getNumberOfTheSchemaId() {
		return numberOfTheSchemaId;
	}

	public void setNumberOfTheSchemaId(int numberOfTheSchemaId) {
		this.numberOfTheSchemaId = numberOfTheSchemaId;
	}

	public int getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(int userOwnerId) {
		this.userOwnerId = userOwnerId;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
