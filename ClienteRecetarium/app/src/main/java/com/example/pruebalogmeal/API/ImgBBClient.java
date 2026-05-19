package com.example.pruebalogmeal.API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImgBBClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://api.imgbb.com/";

    public static ImgBBApi getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ImgBBApi.class);
    }
}