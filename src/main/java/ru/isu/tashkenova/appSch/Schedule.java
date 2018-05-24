package ru.isu.tashkenova.appSch;

public class Schedule {
	int uniqueId;
	int subjectId;
	int studentClassId;
	int cabinetId;
	int dayId;
	int numberOfTheSchema;
	int numberOfTheLesson;
	int errorCode;

	public Schedule(int uniqueId, int subjectId, int studentClassId, int cabinetId, int dayId, int numberOfTheSchema, int numberOfTheLesson, int errorCode){
		this.uniqueId = uniqueId;
		this.subjectId = subjectId;
		this.studentClassId = studentClassId;
		this.cabinetId = cabinetId;
		this.dayId = dayId;
		this.numberOfTheSchema = numberOfTheSchema;
		this.numberOfTheLesson = numberOfTheLesson;
		this.errorCode = errorCode;
	}

	public Schedule(){
		this.errorCode = 1;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getStudentClassId() {
		return studentClassId;
	}

	public void setStudentClassId(int studentClassId) {
		this.studentClassId = studentClassId;
	}

	public int getCabinetId() {
		return cabinetId;
	}

	public void setCabinetId(int cabinetId) {
		this.cabinetId = cabinetId;
	}

	public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

	public int getNumberOfTheSchema() {
		return numberOfTheSchema;
	}

	public void setNumberOfTheSchema(int numberOfTheSchema) {
		this.numberOfTheSchema = numberOfTheSchema;
	}

	public int getNumberOfTheLesson() {
		return numberOfTheLesson;
	}

	public void setNumberOfTheLesson(int numberOfTheLesson) {
		this.numberOfTheLesson = numberOfTheLesson;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
