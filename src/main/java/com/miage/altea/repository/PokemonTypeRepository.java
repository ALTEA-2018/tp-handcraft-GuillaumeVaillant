package com.miage.altea.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.bo.PokemonType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepository() {
        try {
            var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PokemonType findPokemonById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        return null;
        // TODO
    }

    public PokemonType findPokemonByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        return null;
        // TODO
    }

    public List<PokemonType> findAllPokemon() {
        // TODO
        return null;
    }
}