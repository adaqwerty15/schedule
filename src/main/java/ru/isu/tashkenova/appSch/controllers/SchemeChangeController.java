package ru.isu.tashkenova.appSch.controllers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
                 final int numb = s.numberOfTheLesson;
                 final int class_index = s.getStudentClassId()+2;
                labels[numb][class_index].setFont(new Font("Arial", 11));
                labels[numb][class_index].setText(new WorkloadView(subjects.get(s.subjectId), users.get(s.teacherId)).toString());
                labels[numb][class_index].setSubjectId(s.subjectId);
                labels[numb][class_index].setTeacherId(s.teacherId);
                if (s.cabinetId == -1)
                    labels[numb][class_index].setCabinetId(s.cabinetId, 0);
                else {
                    labels[numb][class_index].setText(labels[numb][class_index].getText() + "\n" + cabinets.get(s.cabinetId).getName());
                    labels[numb][class_index].setCabinetId(s.cabinetId, cabinets.get(s.cabinetId).getName().length());
                }
                if (numb % 2 == 0) {
                    gridpane.setRowSpan(labels[numb-1][class_index], 1);
                    labels[numb][class_index].setMinHeight(55);
                    labels[numb][class_index].setPrefHeight(gridpane.getPrefHeight());
                    labels[numb][class_index].setPrefWidth(gridpane.getPrefWidth());
                    labels[numb][class_index].setId("right");
                    labels[numb][class_index].setAlignment(Pos.CENTER);
                    labels[numb][class_index].setOnMouseMoved((EventHandler) event2 -> gridpane.selectRange(class_index, numb));
                }
            }
        }

        checkAll();
    }



}
