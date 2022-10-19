package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.PokemonRepository;

import java.util.List;

public class PokedexViewModel extends AndroidViewModel {

    private PokemonRepository mRepository;

    private LiveData<List<Pokemon>> mPokemon;



    public PokedexViewModel(Application application) {
        super(application);
        mRepository = new PokemonRepository(application);

        mPokemon = mRepository.getAllPokemon();
    }

   public LiveData<List<Pokemon>> getAllPokemon() {

        return mPokemon;
    }

   void insert(Pokemon pokemon) {
        mRepository.insert(pokemon);
    }

    void delete(Pokemon pokemon) {
        mRepository.delete(pokemon);
    }

    void deleteAll() {
        mRepository.deleteAllPokemon();
    }


}