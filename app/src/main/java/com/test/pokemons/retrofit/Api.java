package com.test.pokemons.retrofit;

import com.test.pokemons.models.ApiRequest;
import com.test.pokemons.models.Pokemon;
import com.test.pokemons.models.PokemonSpecies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("pokemon")
    Call<ApiRequest> getResults(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{name}/")
    Call<Pokemon> getPokemon(@Path("name") String name);
    @GET("pokemon/{id}/")
    Call<Pokemon> getPokemon(@Path("id") int id);

    @GET("pokemon-species/{id}/")
    Call<PokemonSpecies> getPokemonSpecies(@Path("id") int id);
}
