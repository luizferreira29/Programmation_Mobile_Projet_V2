package com.example.programmation_mobile_projet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerApi {
    @GET("/v2/beers")
    Call<List<Beer>> getBeerResponse();
}
