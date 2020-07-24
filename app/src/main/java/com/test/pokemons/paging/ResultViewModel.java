package com.test.pokemons.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.test.pokemons.models.ApiRequest;

public class ResultViewModel extends ViewModel {
    public LiveData<PagedList<ApiRequest.Result>> resultPagedList;
    MutableLiveData<ResultDataSource> liveDataSource;

    public ResultViewModel() {
        ResultDataSourceFactory resultDataSourceFactory = new ResultDataSourceFactory();
        liveDataSource = resultDataSourceFactory.getResultLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ResultDataSource.LIMIT).build();

        resultPagedList = (new LivePagedListBuilder(resultDataSourceFactory, pagedListConfig))
                .build();
    }
}
