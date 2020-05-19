package com.example.programmation_mobile_projet.presentation.controller;

import android.content.SharedPreferences;

import com.example.programmation_mobile_projet.Singletons;
import com.example.programmation_mobile_projet.presentation.model.Beer;
import com.example.programmation_mobile_projet.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private static final String BASE_URL = "https://api.punkapi.com/";

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){


        List<Beer> BeerList = getDataFromCache();

        if(BeerList != null){
            view.showList(BeerList);
        } else{
            makeApiCall();
        }



    }
        public void makeApiCall(){
            Call<List<Beer>> call = Singletons.getBeerApi().getBeerResponse();
            call.enqueue(new Callback<List<Beer>>() {
                @Override
                public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                    if(response.isSuccessful() && response.body() !=null) {
                        List<Beer> BeerList = response.body();
                        saveList(BeerList);
                        view.showList(BeerList);
                    } else
                        view.showError();
                }

                @Override
                public void onFailure(Call<List<Beer>> call, Throwable t) {
                    view.showError();
                }
            });
        }

        public void saveList(List<Beer> BeerList) {
            String jsonString = gson.toJson(BeerList);
            sharedPreferences
                    .edit()
                    .putString("JsonBeerList", jsonString)
                    .apply();
        }



    public List<Beer> getDataFromCache() {
        String jsonBeer = sharedPreferences.getString("jsonBeerList", null);

        if(jsonBeer == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Beer>>() {}.getType();
            return gson.fromJson(jsonBeer, listType);
        }
    }

    public void onItemClick(Beer beer){
        view.navigateToDetails(beer);
    }
}
