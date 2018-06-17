package ru.isu.tashkenova.appSch;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserService {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<User> addUser(@Body User user);

    @DELETE("users/{id}")
    Call<User> deleteUser(@Path("id") Integer id);

    @PUT("users/{id}")
    Call<User> putUser(@Path("id") Integer id, @Body User user);

    @GET("role")
    Call<List<Role>> getRole();

    @GET("cabinets")
    Call<List<Cabinet>> getCabinet();

    @DELETE("cabinets/{id}")
    Call<Cabinet> deleteCabinet(@Path("id") Integer id);

    @GET("subject")
    Call<List<Subject>> getSubject();

    @DELETE("subject/{id}")
    Call<Subject> deleteSubject(@Path("id") Integer id);


    @GET("studentsClass")
    Call<List<StudentsClass>> getStudetsClasses();

    @GET("workload")
    Call<List<Workload>> getWorkload();


}
