package ru.isu.tashkenova.appSch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService  {

   //static Proxy proxy = new Proxy(Proxy.Type.HTTP,  new InetSocketAddress(
   //         "proxy.isu.ru", 3128));
    static OkHttpClient client = new OkHttpClient.Builder().build();//.proxy(proxy).build();

    public static UserService RetrofitBuild() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://protected-temple-84049.herokuapp.com/")
                .baseUrl("http://localhost:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        return service;
    }



}
