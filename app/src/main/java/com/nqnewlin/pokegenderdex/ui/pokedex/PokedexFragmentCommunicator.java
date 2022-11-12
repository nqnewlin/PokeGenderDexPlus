package com.nqnewlin.pokegenderdex.ui.pokedex;

import com.nqnewlin.pokegenderdex.models.constants.Types;

import java.util.List;

public interface PokedexFragmentCommunicator {
    void respond(int position,String regionName);

    void currentRegion(String currentRegion);
}
