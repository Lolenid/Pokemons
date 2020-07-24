package com.test.pokemons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.pokemons.models.ApiRequest;
import com.test.pokemons.paging.ResultAdapter;
import com.test.pokemons.paging.ResultDataSource;
import com.test.pokemons.paging.ResultViewModel;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ResultViewModel itemViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        final ResultAdapter adapter = new ResultAdapter(this);
        adapter.setListener(new ResultAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, PokemonDetail.class);
                intent.putExtra(PokemonDetail.EXTRA_POKEMON_ID, position);
                startActivity(intent);
            }
        });

        itemViewModel.resultPagedList.observe(this, new Observer<PagedList<ApiRequest.Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<ApiRequest.Result> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
