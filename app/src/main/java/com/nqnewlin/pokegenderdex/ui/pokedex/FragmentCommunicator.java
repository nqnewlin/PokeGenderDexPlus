package com.nqnewlin.pokegenderdex.ui.pokedex;

public interface FragmentCommunicator {
    void respond(int position,String regionName);

    void currentRegion(String currentRegion);
}
