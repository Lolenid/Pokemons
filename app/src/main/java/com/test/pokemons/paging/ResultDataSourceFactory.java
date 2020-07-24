package com.test.pokemons.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.test.pokemons.models.ApiRequest;

public class ResultDataSourceFactory extends DataSource.Factory<Integer, ApiRequest.Result> {

    private MutableLiveData<ResultDataSource> resultLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, ApiRequest.Result> create() {
        ResultDataSource itemDataSource = new ResultDataSource();

        resultLiveDataSource.postValue(itemDataSource);

        return itemDataSource;
    }


    public MutableLiveData<ResultDataSource> getResultLiveDataSource() {
        return resultLiveDataSource;
    }
}
