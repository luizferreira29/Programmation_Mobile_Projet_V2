package com.example.programmation_mobile_projet;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.programmation_mobile_projet.data.BeerApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Singletons{

    private static final String BASE_URL = "https://api.punkapi.com/";


    private static Gson gsonInstance;
    private static BeerApi beerApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }

        return  gsonInstance;

    }

    public static BeerApi getBeerApi(){
        if(beerApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            beerApiInstance = retrofit.create(BeerApi.class);
        }
        return beerApiInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("programmation_projet_mobile", Context.MODE_PRIVATE);
        }

        return  sharedPreferencesInstance;

    }

}
