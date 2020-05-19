package com.example.programmation_mobile_projet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter ListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences Sharedpreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sharedpreferences = getSharedPreferences("application_projet_app_mobile", Context.MODE_PRIVATE);
         gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Beer> BeerList = getDataFromCache();

        if(BeerList != null){
            showList(BeerList);
        } else{
            makeApiCall();
    }
    }

    private List<Beer> getDataFromCache() {
        String jsonBeer = Sharedpreferences.getString("jsonBeerList", null);

        if(jsonBeer == null){
            return null;
        } else {
            Type listType = new TypeToken<List<Beer>>() {}.getType();
            return gson.fromJson(jsonBeer, listType);
        }
    }

    private void showList(List<Beer> BeerList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        ListAdapter = new ListAdapter(BeerList);
        recyclerView.setAdapter(ListAdapter);
    }

    private static final String BASE_URL = "https://api.punkapi.com/";

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final BeerApi BeerApi = retrofit.create(BeerApi.class);

        Call<List<Beer>> call = BeerApi.getBeerResponse();
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if(response.isSuccessful() && response.body() !=null) {
                    List<Beer> BeerList = response.body();
                    saveList(BeerList);
                    showList(BeerList);
                } else
                    showError();
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Beer> BeerList) {
        String jsonString = gson.toJson(BeerList);
        Sharedpreferences
                .edit()
                .putString("JsonBeerList", jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
