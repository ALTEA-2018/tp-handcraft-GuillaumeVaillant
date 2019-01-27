package com.miage.altea.controller;

import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepository;

import java.util.Map;

/*
    peut être appelée avec des paramêtres id ou namexz
*/
@Controller
public class PokemonTypeController {

    @RequestMapping(uri="/pokemon")
    public String sayHello(){
        return "Hello World !";
    }

    private PokemonTypeRepository repository = new PokemonTypeRepository();

    public PokemonType getPokemon(Map<String,String[]> parameters){
        // TODO
        return null;
    }

}