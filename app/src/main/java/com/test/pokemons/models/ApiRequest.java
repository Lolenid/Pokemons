package com.test.pokemons.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class ApiRequest {
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("next")
    @Expose
    public String next;
    @SerializedName("previous")
    @Expose
    public String previous;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    public class Result {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("url")
        @Expose
        public String url;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return Objects.equals(name, result.name) &&
                    Objects.equals(url, result.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, url);
        }
    }
}

