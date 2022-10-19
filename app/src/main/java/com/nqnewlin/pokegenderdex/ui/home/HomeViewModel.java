package com.nqnewlin.pokegenderdex.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.PokemonRepository;

public class HomeViewModel extends AndroidViewModel {

    private PokemonRepository mRepository;

    private final MutableLiveData<String> mText;

    private LiveData<Pokemon> mPokemon;



    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();


        //mText.setValue("This is home fragment");

        mRepository = new PokemonRepository(application);

        mPokemon = getCurrentPokemon(mRepository);
    }

    public LiveData<Pokemon> getPokemon() { return mPokemon; }

    public LiveData<Pokemon> getCurrentPokemon(PokemonRepository repository) {
        if (mPokemon == null) {
            mPokemon = repository.getSinglePokemon();
            Log.d("Getting Pokemon", "test");
        }

        return mPokemon;
    }
}