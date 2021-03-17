package com.example.meditation.Network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitCalls {
    @POST("user/login")
    Call<Tokens>login(@Body HashMap<String,String> auth);
    @GET("quotes")
    Call<Emot>quotes();
    @GET("feelings")
    Call<Feelings>feelings();
}
