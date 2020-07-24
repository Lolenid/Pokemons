package com.test.pokemons.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pokemon {
    public static List<Pokemon> pokemons = new ArrayList<>();

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("base_experience")
    @Expose
    public Integer baseExperience;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("is_default")
    @Expose
    public Boolean isDefault;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("weight")
    @Expose
    public Integer weight;
    @SerializedName("sprites")
    @Expose
    public Sprites sprites;
    @SerializedName("stats")
    @Expose
    public List<Stats> stats;
    @SerializedName("types")
    @Expose
    public List<Types> types;
    @SerializedName("species")
    @Expose
    public Species species;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) &&
                Objects.equals(name, pokemon.name) &&
                Objects.equals(baseExperience, pokemon.baseExperience) &&
                Objects.equals(height, pokemon.height) &&
                Objects.equals(isDefault, pokemon.isDefault) &&
                Objects.equals(order, pokemon.order) &&
                Objects.equals(weight, pokemon.weight) &&
                Objects.equals(stats, pokemon.stats) &&
                Objects.equals(types, pokemon.types) &&
                Objects.equals(sprites, pokemon.sprites) &&
                Objects.equals(species, pokemon.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, baseExperience, height, isDefault, order, weight, sprites, stats, types, species);
    }


    public class Species {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("url")
        @Expose
        public String url;

    }

    public class Stats {

        @SerializedName("base_stat")
        @Expose
        public Integer baseStat;
        @SerializedName("effort")
        @Expose
        public Integer effort;
        @SerializedName("stat")
        @Expose
        public Stat stat;

    }

    public class Stat {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("url")
        @Expose
        public String url;

    }

    public class Types {

        @SerializedName("slot")
        @Expose
        public Integer slot;
        @SerializedName("type")
        @Expose
        public Type type;

    }

    public class Type {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("url")
        @Expose
        public String url;

    }

    public class Sprites {

        @SerializedName("back_female")
        @Expose
        public String backFemale;
        @SerializedName("back_shiny_female")
        @Expose
        public String backShinyFemale;
        @SerializedName("back_default")
        @Expose
        public String backDefault;
        @SerializedName("front_female")
        @Expose
        public String frontFemale;
        @SerializedName("front_shiny_female")
        @Expose
        public String frontShinyFemale;
        @SerializedName("back_shiny")
        @Expose
        public String backShiny;
        @SerializedName("front_default")
        @Expose
        public String frontDefault;
        @SerializedName("front_shiny")
        @Expose
        public String frontShiny;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Sprites sprites = (Sprites) o;
            return Objects.equals(backFemale, sprites.backFemale) &&
                    Objects.equals(backShinyFemale, sprites.backShinyFemale) &&
                    Objects.equals(backDefault, sprites.backDefault) &&
                    Objects.equals(frontFemale, sprites.frontFemale) &&
                    Objects.equals(frontShinyFemale, sprites.frontShinyFemale) &&
                    Objects.equals(backShiny, sprites.backShiny) &&
                    Objects.equals(frontDefault, sprites.frontDefault) &&
                    Objects.equals(frontShiny, sprites.frontShiny);
        }

        @Override
        public int hashCode() {
            return Objects.hash(backFemale, backShinyFemale, backDefault, frontFemale, frontShinyFemale, backShiny, frontDefault, frontShiny);
        }
    }
}
