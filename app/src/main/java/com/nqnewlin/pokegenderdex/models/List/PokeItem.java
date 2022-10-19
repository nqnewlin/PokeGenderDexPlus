package com.nqnewlin.pokegenderdex.models.List;

import com.nqnewlin.pokegenderdex.models.Pokemon;

public class PokeItem extends ListItem {
    private Pokemon pokemon;

    public PokeItem(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    @Override
    public int getType() {
        return TYPE_POKE;
    }


}
