package com.test.pokemons.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.test.pokemons.models.ApiRequest;
import com.test.pokemons.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDataSource extends PageKeyedDataSource<Integer, ApiRequest.Result>{

    public static final int LIMIT = 30;

    private static final int OFFSET = 0;
    private static int step = 0;

    public static void setStep(int step) {
        ResultDataSource.step = step;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull final LoadInitialCallback<Integer, ApiRequest.Result> loadInitialCallback) {
        RetrofitClient.getInstance()
                .getApi().getResults(OFFSET, LIMIT)
                .enqueue(new Callback<ApiRequest>() {
                    @Override
                    public void onResponse(Call<ApiRequest> call, Response<ApiRequest> response) {
                        if (response.body() != null) {
                            loadInitialCallback.onResult(response.body().results, null, OFFSET + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiRequest> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> loadParams, @NonNull final LoadCallback<Integer, ApiRequest.Result> loadCallback) {
        RetrofitClient.getInstance()
                .getApi().getResults(step - LIMIT, LIMIT)
                .enqueue(new Callback<ApiRequest>() {
                    @Override
                    public void onResponse(Call<ApiRequest> call, Response<ApiRequest> response) {

                        Integer adjacentKey = (loadParams.key > 1) ? loadParams.key - 1 : null;
                        if (response.body() != null) {

                            loadCallback.onResult(response.body().results, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiRequest> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> loadParams, @NonNull final LoadCallback<Integer, ApiRequest.Result> loadCallback) {
        step += LIMIT;
        RetrofitClient.getInstance()
                .getApi()
                .getResults(step, LIMIT)
                .enqueue(new Callback<ApiRequest>() {
                    @Override
                    public void onResponse(Call<ApiRequest> call, Response<ApiRequest> response) {

                        if (response.body() != null) {
                            Integer key = (response.body().count > 1) ? loadParams.key + 1 : null;
                            loadCallback.onResult(response.body().results, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiRequest> call, Throwable t) {

                    }
                });
    }
}
