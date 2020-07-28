package com.test.pokemons.paging;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.pokemons.R;
import com.test.pokemons.models.ApiRequest;
import com.test.pokemons.models.Pokemon;
import com.test.pokemons.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultAdapter extends PagedListAdapter<ApiRequest.Result, ResultAdapter.ResultViewHolder> {
    private Context mCtx;

    private Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public ResultAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cw = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_pokemon, parent, false);
        return new ResultViewHolder(cw);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResultViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        final TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        final ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        final ApiRequest.Result result = getItem(position);

            RetrofitClient.getInstance().getApi().getPokemon(result.name).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon pokemon = response.body();
                    if (pokemon != null) {
                        Pokemon.pokemons.add(pokemon);
                        textView.setText(pokemon.name);
                        Glide.with(mCtx)
                                .load(pokemon.sprites.frontDefault)
                                .into(imageView);
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable throwable) {

                }
            });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    private static DiffUtil.ItemCallback<ApiRequest.Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ApiRequest.Result>() {
                @Override
                public boolean areItemsTheSame(ApiRequest.Result oldResult, ApiRequest.Result newResult) {
                    return oldResult.name.equals(newResult.name);
                }


                @Override
                public boolean areContentsTheSame(ApiRequest.Result oldResult, ApiRequest.Result newResult) {
                    return oldResult.equals(newResult);
                }
            };

    public static class ResultViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        public ResultViewHolder(CardView resultView) {
            super(resultView);
            this.cardView = resultView;
        }
    }
}
