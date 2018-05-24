package ru.isu.tashkenova.appSch;

import java.util.ArrayList;

public class Subject {
	int uniqueId;
	int groupNumber;
	String name;
	ArrayList preferredClassrooms;
	int errorCode;

	public Subject(int uniqueId, int groupNumber, String name, ArrayList preferredClassrooms, int errorCode){
		this.uniqueId = uniqueId;
		this.groupNumber = groupNumber;
		this.name = name;
		this.preferredClassrooms = preferredClassrooms;
		this.errorCode = errorCode;
	}

	public Subject(){
		this.errorCode = 1;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreferredClassrooms(ArrayList preferredClassrooms) {
		this.preferredClassrooms = preferredClassrooms;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public String getName() {
		return name;
	}

	public ArrayList getPreferredClassrooms() {
		return preferredClassrooms;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
