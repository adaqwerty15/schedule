package ru.isu.tashkenova.appSch;

public class Schedule {
    public int id;
    public int subjectId;
    public int studentClassId;
    public int cabinetId;
    public int teacherId;

    public String numberOfTheSchema;
    public int numberOfTheLesson;
    int errorCode;

    public Schedule(int id, int subjectId, int studentClassId, int cabinetId, int teacherId, String numberOfTheSchema, int numberOfTheLesson, int errorCode) {
        this.id = id;
        this.subjectId = subjectId;
        this.studentClassId = studentClassId;
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.numberOfTheSchema = numberOfTheSchema;
        this.numberOfTheLesson = numberOfTheLesson;
        this.errorCode = errorCode;
    }

    public Schedule(int subjectId, int studentClassId, int cabinetId, int teacherId, String numberOfTheSchema, int numberOfTheLesson) {
        this.subjectId = subjectId;
        this.studentClassId = studentClassId;
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.numberOfTheSchema = numberOfTheSchema;
        this.numberOfTheLesson = numberOfTheLesson;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getNumberOfTheSchema() {
        return numberOfTheSchema;
    }

    public void setNumberOfTheSchema(String numberOfTheSchema) {
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

    public Schedule() {
        this.errorCode = 1;
    }

    public Schedule(int errorCode) {
        this.errorCode = 0;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "subjectId=" + subjectId +
                ", studentClassId=" + studentClassId +
                ", cabinetId=" + cabinetId +
                ", numberOfTheSchema=" + numberOfTheSchema +
                ", numberOfTheLesson=" + numberOfTheLesson +
                '}';
    }
}
