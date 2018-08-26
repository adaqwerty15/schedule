package ru.isu.tashkenova.appSch.controllers;

import ru.isu.tashkenova.appSch.CreateScheduleView;
import ru.isu.tashkenova.appSch.RetrofitService;
import ru.isu.tashkenova.appSch.ScheduleOwner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class SchemeController extends Scheme{


    private int dayId;
    public String nameSchema;



    public void setDayId (int dayId) throws IOException {
        ArrayList<String> a= new ArrayList<String>(Arrays.asList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"));
        this.dayId = dayId;
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("ddMMyyyyHHmmss");
        numberSchemaId = format2.format(d);
        nameSchema = "Схема от "+ format1.format(d);
        RetrofitService.RetrofitBuildW().addscheduleOwner(new ScheduleOwner(0, numberSchemaId, nameSchema, 0, dayId, valid,0 )).execute();
        CreateSheduleController.data.add(new CreateScheduleView(nameSchema, 0, a.get(dayId), numberSchemaId));

        super.init();
    }



//    public void saveClick(ActionEvent actionEvent) throws IOException {
//        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
//          for(int i=1; i<rows*2-1; i++)
//              for (int j=2; j<cols;j++) {
//              if (labels[i][j].getSubjectId()!=-1)
//              schedules.add(new Schedule(labels[i][j].getSubjectId(),j-2,
//                      labels[i][j].getCabinetId(), labels[i][j].getTeacherId(), numberSchemaId, i));
//              }
//
//        for (Schedule s:schedules) {
//            service.addschedule(s).execute();
//        }
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("Изменения сохранены");
//        alert.setHeaderText(null);
//        alert.setTitle("Information");
//        alert.showAndWait();
//    }



}
