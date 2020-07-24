package com.test.pokemons;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.pokemons.models.Pokemon;
import com.test.pokemons.models.PokemonSpecies;
import com.test.pokemons.retrofit.RetrofitClient;
import com.test.pokemons.typeRecycler.AdapterType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetail extends AppCompatActivity {
    public static final String EXTRA_POKEMON_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        Intent intent = getIntent();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final int id = intent.getExtras().getInt(EXTRA_POKEMON_ID) + 1;
        final TextView textName = (TextView) findViewById(R.id.info_name);
        final TextView textHeight = (TextView) findViewById(R.id.info_height);
        final TextView textWeight = (TextView) findViewById(R.id.info_weight);
        final TextView textHP = (TextView) findViewById(R.id.info_hp);
        final TextView textAttack = (TextView) findViewById(R.id.info_attack);
        final TextView textDefense = (TextView) findViewById(R.id.info_defense);
        final ImageView image = (ImageView) findViewById(R.id.image_info);

        final TextView textCategory = (TextView) findViewById(R.id.category_info);
        final RecyclerView recyclerView = findViewById(R.id.recyclerType);

        RetrofitClient.getInstance().getApi().getPokemon(id).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = response.body();
                if (pokemon != null) {
                    textName.setText(pokemon.name);
                    textHeight.setText((pokemon.height / 10.0) + " м");
                    textWeight.setText((pokemon.weight / 10.0) + " кг");
                    Glide.with(PokemonDetail.this)
                            .load(pokemon.sprites.frontDefault)
                            .into(image);
                    textHP.setText(pokemon.stats.get(0).baseStat.toString());
                    textAttack.setText(pokemon.stats.get(1).baseStat.toString());
                    textDefense.setText(pokemon.stats.get(2).baseStat.toString());

                    AdapterType adapterType = new AdapterType(pokemon.types);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PokemonDetail.this);

                    recyclerView.setAdapter(adapterType);
                    recyclerView.setLayoutManager(linearLayoutManager);

                }
                RetrofitClient.getInstance().getApi().getPokemonSpecies(pokemon.id).enqueue(new Callback<PokemonSpecies>() {
                    @Override
                    public void onResponse(Call<PokemonSpecies> call, final Response<PokemonSpecies> response) {
                        PokemonSpecies pokemonSpecies = response.body();
                        if (pokemonSpecies != null){
                            textCategory.setText(pokemonSpecies.genera.get(7).genus);
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonSpecies> call, Throwable throwable) {

                    }
                });
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable throwable) {

            }
        });
    }
}
