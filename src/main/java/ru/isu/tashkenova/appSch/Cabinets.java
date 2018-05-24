package ru.isu.tashkenova.appSch;

public class Cabinets {
	int uniqueId;
	String name;
	public int errorCode;

	public Cabinets(int uniqueId, String name) {
		this.uniqueId = uniqueId;
		this.name = name;
		this.errorCode = errorCode;
	}

	public Cabinets() {
		this.errorCode = 1;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUniqueId(){
		return uniqueId;
	}

	public String getName(){
		return name;
	}
}
