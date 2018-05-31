package ru.isu.tashkenova.appSch;


public class ScheduleOwner {
    public int id;
    public String numberOfTheSchemaId;
    public String name;
    public int userOwnerId;
    public int dayId;
    public boolean valid;
    public int errorCode;

    public ScheduleOwner(int id, String numberOfTheSchemaId, String name, int userOwnerId, int dayId, boolean valid, int errorCode) {
        this.id = id;
        this.numberOfTheSchemaId = numberOfTheSchemaId;
        this.name = name;
        this.userOwnerId = userOwnerId;
        this.dayId = dayId;
        this.valid = valid;
        this.errorCode = errorCode;
    }

    public ScheduleOwner(String numberOfTheSchemaId, String name, int userOwnerId, int dayId, boolean valid) {
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

    public String getNumberOfTheSchemaId() {
        return numberOfTheSchemaId;
    }

    public void setNumberOfTheSchemaId(String numberOfTheSchemaId) {
        this.numberOfTheSchemaId = numberOfTheSchemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(int userOwnerId) {
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

    @Override
    public String toString() {
        return "ScheduleOwner{" +
                "id=" + id +
                ", numberOfTheSchemaId='" + numberOfTheSchemaId + '\'' +
                ", name='" + name + '\'' +
                ", userOwnerId=" + userOwnerId +
                ", dayId=" + dayId +
                '}';
    }
}
