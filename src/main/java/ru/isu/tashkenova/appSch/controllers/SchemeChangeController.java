package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.scene.text.Font;
import retrofit2.Response;
import ru.isu.tashkenova.appSch.CreateScheduleView;
import ru.isu.tashkenova.appSch.Schedule;
import ru.isu.tashkenova.appSch.WorkloadView;

import java.io.IOException;
import java.util.List;


public class SchemeChangeController extends Scheme{

    public void setView(CreateScheduleView view) throws IOException {
        numberSchemaId = view.getNumberoftheSchema();
        this.view = view;

        super.init();

        Response<List<Schedule>> schedule = service.getscheduleOfId(view.getNumberoftheSchema()).execute();
        contentSch = FXCollections.observableArrayList(
                schedule.body()
        );

        for (Schedule s : contentSch) {
            if (s.subjectId != -1) {
                labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setFont(new Font("Arial", 11));
                labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setText(new WorkloadView(subjects.get(s.subjectId), users.get(s.teacherId)).toString());
                labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setSubjectId(s.subjectId);
                labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setTeacherId(s.teacherId);
                if (s.cabinetId == -1)
                    labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setCabinetId(s.cabinetId, 0);
                else {
                    labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setText(labels[s.numberOfTheLesson][s.getStudentClassId() + 1].getText() + "\n" + cabinets.get(s.cabinetId).getName());
                    labels[s.numberOfTheLesson][s.getStudentClassId() + 1].setCabinetId(s.cabinetId, cabinets.get(s.cabinetId).getName().length());
                }
            }
        }

        checkAll();
    }




}
