package ru.isu.tashkenova.appSch;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface WorkloadService {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("role")
    Call<List<Role>> getRole();

    @GET("studentsClass")
    Call<List<StudentsClass>> getStudentClasses();

    @GET("subject")
    Call<List<Subject>> getSubjects();

    @GET("workload")
    Call<List<Workload>> getWorkload();

    @GET("scheduleOwner")
    Call<List<ScheduleOwner>> getscheduleOwner();

    @POST("scheduleOwner")
    Call<ScheduleOwner> addscheduleOwner(@Body ScheduleOwner owner);

    @DELETE("scheduleOwner/{id}")
    Call<ScheduleOwner> deletescheduleOwner(@Path("id") Integer id);

    @PUT("scheduleOwner/{id}")
    Call<ScheduleOwner> putscheduleOwner(@Path("id") Integer id, @Body  ScheduleOwner owner);

    @GET("schedule")
    Call<List<Schedule>> getschedule();

    @DELETE("schedule/{id}")
    Call<Schedule> deleteSchedule(@Path("id") String id);

    @POST("schedule")
    Call<Schedule> addschedule(@Body Schedule schedule);
}
