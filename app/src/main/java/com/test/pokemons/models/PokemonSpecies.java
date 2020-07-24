package com.test.pokemons.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonSpecies {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("gender_rate")
    @Expose
    public Integer genderRate;
    @SerializedName("capture_rate")
    @Expose
    public Integer captureRate;
    @SerializedName("base_happiness")
    @Expose
    public Integer baseHappiness;
    @SerializedName("is_baby")
    @Expose
    public Boolean isBaby;
    @SerializedName("hatch_counter")
    @Expose
    public Integer hatchCounter;
    @SerializedName("has_gender_differences")
    @Expose
    public Boolean hasGenderDifferences;
    @SerializedName("forms_switchable")
    @Expose
    public Boolean formsSwitchable;


    @SerializedName("genera")
    @Expose
    public List<Genera> genera = null;

    public class Genera {

        @SerializedName("genus")
        @Expose
        public String genus;

    }
}
