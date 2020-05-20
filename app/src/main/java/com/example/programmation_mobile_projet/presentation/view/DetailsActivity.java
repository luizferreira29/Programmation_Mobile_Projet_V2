package com.example.programmation_mobile_projet.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    public ImageView imageView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txtName);
        Intent intent = getIntent();
        String beerJson = intent.getStringExtra("beerKeyName"); //if it's a string you stored.
        Beer beer = Singletons.getGson().fromJson(beerJson, Beer.class);
        showDetail(beer);

        txtDetail = findViewById(R.id.detail_txtTagline);
        showDetailTagline(beer);

        txtDetail = findViewById(R.id.detail_txtBrewersTips);
        showDetailBrewersTips(beer);

        txtDetail = findViewById(R.id.detail_txtDescription);
        showDetailDescription(beer);

        txtDetail = findViewById(R.id.detail_txtAbv);
        showDetailAbv(beer);

        txtDetail = findViewById(R.id.detail_txtId);
        showDetailId(beer);

        txtDetail = findViewById(R.id.detail_txtPh);
        showDetailPh(beer);

        imageView = findViewById(R.id.picture);
        showPicture(beer);

        imageView = findViewById(R.id.picture2);
        showPicture(beer);


    }

    private void showDetail(Beer beer) {
        txtDetail.setText(beer.getName());
    }

    @SuppressLint("SetTextI18n")
    private void showDetailTagline(Beer beer) {
        txtDetail.setText("Tagline : " +beer.getTagline());
    }

    @SuppressLint("SetTextI18n")
    private void showDetailDescription(Beer beer) {
        txtDetail.setText("Description : " +beer.getDescription());
    }

    @SuppressLint("SetTextI18n")
    private void showDetailBrewersTips(Beer beer) {
        txtDetail.setText("Brewers tips : " +beer.getBrewers_tips());
    }

    private void showDetailAbv(Beer beer) {
        String s = beer.getAbv().toString();
        txtDetail.setText("Abv (in %): " +s);
    }

    private void showDetailId(Beer beer) {
        String s = beer.getId().toString();
        txtDetail.setText("Id : " +s);
    }

    private void showDetailPh(Beer beer) {
        String s = beer.getPh().toString();
        txtDetail.setText("Ph : " +s);
    }

    private void showPicture(Beer beer) {
        String Url = beer.getImage_url();
        Glide.with(this)
                .load(Url)
                .into(imageView);
    }

}




