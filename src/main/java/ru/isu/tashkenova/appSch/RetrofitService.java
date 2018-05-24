package ru.isu.tashkenova.appSch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class RetrofitService  {

    static private Proxy proxy = new Proxy(Proxy.Type.HTTP,  new InetSocketAddress(
            "proxy.isu.ru", 3128));
    static private OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();

    public static UserService RetrofitBuild() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mysterious-mountain-44553.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        return service;
    }



}
