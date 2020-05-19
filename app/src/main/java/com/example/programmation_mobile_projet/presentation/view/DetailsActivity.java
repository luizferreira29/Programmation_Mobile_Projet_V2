package com.example.programmation_mobile_projet.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.programmation_mobile_projet.R;
import com.example.programmation_mobile_projet.Singletons;
import com.example.programmation_mobile_projet.presentation.controller.MainController;
import com.example.programmation_mobile_projet.presentation.model.Beer;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private com.example.programmation_mobile_projet.presentation.view.ListAdapter ListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;
    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String beerJson = intent.getStringExtra("beerKeyName"); //if it's a string you stored.
        Beer beer = Singletons.getGson().fromJson(beerJson, Beer.class);
        showDetail(beer);
    }

    private void showDetail(Beer beer) {
        txtDetail.setText(beer.getName());
    }

}

