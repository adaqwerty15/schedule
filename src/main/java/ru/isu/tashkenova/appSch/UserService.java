package ru.isu.tashkenova.appSch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface UserService {
    @GET("users")
    Call<List<User>> getUsers();
}
