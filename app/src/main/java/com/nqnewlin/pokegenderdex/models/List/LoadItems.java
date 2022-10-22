package com.nqnewlin.pokegenderdex.models.List;

import com.nqnewlin.pokegenderdex.R;
import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.RegionId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class LoadItems {

    private final static List<String> REGIONS = Arrays.asList("kanto", "johto", "hoenn", "sinnoh", "unova",
            "kalos", "alola", "galar");



    public static TreeMap<RegionId, List<Pokemon>> loadList(List<Pokemon> pokemons) {
        TreeMap<RegionId, List<Pokemon>> map = new TreeMap<>();
        List<Pokemon> pokemonList = new ArrayList<>();


        int index = 0;
        boolean match = true;
        for (int i = 0; i < REGIONS.size(); i++) {
            RegionId regionId = new RegionId(i, REGIONS.get(i));

            match = true;

            while (match) {
                if (!pokemons.get(index).getRegion().equals(REGIONS.get(i))) {
                    match = false;
                    map.put(regionId, pokemonList);
//                    pokemonList.clear();
                    pokemonList = new ArrayList<>();
                }
                pokemonList.add(pokemons.get(index));
                index++;
            }
        }
        return map;

    }
}
