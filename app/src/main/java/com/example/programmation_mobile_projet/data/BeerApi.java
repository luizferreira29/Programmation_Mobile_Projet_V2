package com.example.programmation_mobile_projet.data;

import com.example.programmation_mobile_projet.presentation.model.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerApi {
    @GET("/v2/beers")
    Call<List<Beer>> getBeerResponse();
}
