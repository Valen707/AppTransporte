package com.example.apptransporte.interfaces;

import com.example.apptransporte.modulePokemon.Pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface peticiones {
    @GET("pokemon/{id}")
    Call<Pokedex> consultar(@Path("id")String id);
}
