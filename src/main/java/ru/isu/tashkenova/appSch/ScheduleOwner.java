package ru.isu.tashkenova.appSch;

public class ScheduleOwner {
    public int id;
    public int numberOfTheSchemaId;
    public String name;
    public String userOwnerId;
    public int dayId;
    public boolean valid;
    public int errorCode;

    public ScheduleOwner(int id, int numberOfTheSchemaId, String name, String userOwnerId, int dayId, boolean valid, int errorCode) {
        this.id = id;
        this.numberOfTheSchemaId = numberOfTheSchemaId;
        this.name = name;
        this.userOwnerId = userOwnerId;
        this.dayId = dayId;
        this.valid = valid;
        this.errorCode = errorCode;
    }

    public ScheduleOwner(int numberOfTheSchemaId, String name, String userOwnerId, int dayId, boolean valid) {
        this.numberOfTheSchemaId = numberOfTheSchemaId;
        this.name = name;
        this.userOwnerId = userOwnerId;
        this.dayId = dayId;
        this.valid = valid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfTheSchemaId() {
        return numberOfTheSchemaId;
    }

    public void setNumberOfTheSchemaId(int numberOfTheSchemaId) {
        this.numberOfTheSchemaId = numberOfTheSchemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(String userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ScheduleOwner(){

        this.errorCode = 1;
    }

    public ScheduleOwner(int errorCode) {
        this.errorCode = 0;
    }
}
