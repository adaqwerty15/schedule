package ru.isu.tashkenova.appSch;

import javafx.scene.Node;
import javafx.scene.control.Label;

import java.awt.*;

public class LabelNew extends Label {
    private int cabinetId;
    private int teacherId;
    private boolean isValid;
    private int subjectId;
    private int cabinetLength;


    public LabelNew() {
    }

    public LabelNew(int cabinetId, int teacherId, boolean isValid, int subjectId) {
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = isValid;
        this.subjectId = subjectId;
    }

    public LabelNew(String text, int cabinetId, int teacherId, boolean isValid, int subjectId) {
        super(text);
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = isValid;
        this.subjectId = subjectId;
    }

    public LabelNew(String text, Node graphic, int cabinetId, int teacherId, boolean isValid, int subjectId) {
        super(text, graphic);
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = isValid;
        this.subjectId = subjectId;
    }

    public LabelNew(int cabinetId, int teacherId, boolean isValid) {
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = isValid;
    }

    public LabelNew(String text, int cabinetId, int teacherId, boolean isValid) {
        super(text);
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = isValid;
    }

    public LabelNew(String text, Node graphic, int cabinetId, int teacherId, boolean isValid) {
        super(text, graphic);
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = isValid;
    }

    public LabelNew(int cabinetId, int teacherId) {
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = true;
    }

    public LabelNew(String text, int cabinetId, int teacherId) {
        super(text);
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = true;
    }

    public LabelNew(String text, Node graphic, int cabinetId, int teacherId) {
        super(text, graphic);
        this.cabinetId = cabinetId;
        this.teacherId = teacherId;
        this.isValid = true;
    }

    public LabelNew(String text) throws HeadlessException {
        super(text);
        this.cabinetId = -1;
        this.teacherId = -1;
        this.subjectId = -1;
        this.isValid = true;
    }

    public void addCabinet(String cabinet) {
        if (cabinetId!=-1) {
            this.clearCabinet();
        }
        this.setText(this.getText()+"\n"+cabinet);
    }

    public void clearCabinet() {
        if (cabinetId!=-1) {
            this.setText(this.getText().substring(0, this.getText().length()-this.cabinetLength-1));
        }
        this.cabinetLength = 0;
        this.cabinetId = -1;
    }

    public int getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(int cabinetId, int cabinetLength) {
        this.cabinetId = cabinetId;
        this.cabinetLength = cabinetLength;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "LabelNew{" +
                "cabinetId=" + cabinetId +
                ", teacherId=" + teacherId +
                ", isValid=" + isValid +
                ", subjectId=" + subjectId +
                '}';
    }
}



