package com.example.programmation_mobile_projet.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.programmation_mobile_projet.R;
import com.example.programmation_mobile_projet.Singletons;
import com.example.programmation_mobile_projet.data.BeerApi;
import com.example.programmation_mobile_projet.presentation.controller.MainController;
import com.example.programmation_mobile_projet.presentation.model.Beer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.programmation_mobile_projet.presentation.view.ListAdapter ListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferencesInstance(getApplicationContext())
        );
        controller.onStart();

    }

    public void showList(List<Beer> BeerList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        ListAdapter = new ListAdapter(BeerList);
        recyclerView.setAdapter(ListAdapter);
    }



    private static final String BASE_URL = "https://api.punkapi.com/";

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
