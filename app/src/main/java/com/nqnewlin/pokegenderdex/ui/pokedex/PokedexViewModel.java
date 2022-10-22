package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nqnewlin.pokegenderdex.models.List.ListItem;
import com.nqnewlin.pokegenderdex.models.List.LoadItems;
import com.nqnewlin.pokegenderdex.models.List.PokeItem;
import com.nqnewlin.pokegenderdex.models.List.RegionItem;
import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.PokemonRepository;
import com.nqnewlin.pokegenderdex.models.RegionId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class PokedexViewModel extends AndroidViewModel {

    private PokemonRepository mRepository;

    private LiveData<List<Pokemon>> mPokemon;

    private List<RegionId> mRegions;

    private final static List<String> REGIONS = Arrays.asList("Kanto", "Johto", "Hoenn", "Sinnoh", "Unova",
            "Kalos", "Alola", "Galar");



    public PokedexViewModel(Application application) {
        super(application);
        mRepository = new PokemonRepository(application);

        mRegions = loadRegions();

        mPokemon = mRepository.getAllPokemon();
    }

   public LiveData<List<Pokemon>> getAllPokemon() {

        return mPokemon;
    }

    public List<ListItem> getAllItems() {
        LoadItems loadItems = new LoadItems();
        TreeMap<RegionId, List<Pokemon>> mPokeMap = LoadItems.loadList(mPokemon.getValue());

        List<ListItem> items = new ArrayList<>();

        for (RegionId id : mPokeMap.keySet()) {
            RegionItem region = new RegionItem(id);
            items.add(region);
            for (Pokemon pokemon : mPokeMap.get(id)) {
                PokeItem pokeItem = new PokeItem(pokemon);
                items.add(pokeItem);
            }
        }
        return items;
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

    public List<RegionId> getRegions() {
//        for (int i = 0; i < REGIONS.size(); i++) {
//            mRegions.add(new RegionId(i, REGIONS.get(i)));
//        }

        return mRegions;
    }

    public List<RegionId> loadRegions() {
        mRegions = new ArrayList<>();
        for (int i = 0; i < REGIONS.size(); i++) {
            mRegions.add(new RegionId(i, REGIONS.get(i)));
        }
        return mRegions;
    }




}