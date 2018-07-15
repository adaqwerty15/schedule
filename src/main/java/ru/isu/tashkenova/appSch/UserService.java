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

    @POST("cabinets")
    Call<Cabinet> addCabinet(@Body Cabinet cabinet);

    @DELETE("cabinets/{id}")
    Call<Cabinet> deleteCabinet(@Path("id") Integer id);

    @PUT("cabinets/{id}")
    Call<Cabinet> putCabinet(@Path("id") Integer id, @Body Cabinet cabinet);

    @GET("subject")
    Call<List<Subject>> getSubject();

    @POST("subject")
    Call<Subject> addSubject(@Body Subject subject);

    @DELETE("subject/{id}")
    Call<Subject> deleteSubject(@Path("id") Integer id);

    @PUT("subject/{id}")
    Call<Subject> putSubject(@Path("id") Integer id, @Body Subject subject);

    @GET("studentsClass")
    Call<List<StudentsClass>> getStudetsClasses();

    @GET("studentsClass/{id}")
    Call<StudentsClass> getStudetsClassWithCode(@Path("id") String id);

    @GET("workload")
    Call<List<Workload>> getWorkload();

    @POST("workload")
    Call<Workload> addWorkload(@Body Workload workload);

    @DELETE("workload/{id}")
    Call<Workload> deleteWorkload(@Path("id") Integer id);

    @GET("workload/{id}")
    Call<List<Workload>> getWorkloadWithId(@Path("id") Integer id);

    @PUT("workload/{id}")
    Call<Workload> putWorkload(@Path("id") Integer id, @Body Workload workload);


}
