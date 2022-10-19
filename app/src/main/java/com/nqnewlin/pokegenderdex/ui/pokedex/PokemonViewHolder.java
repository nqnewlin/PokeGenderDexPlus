package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nqnewlin.pokegenderdex.R;

import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    //private final TextView pokemonNameView;

    private PokemonViewHolder(View itemView) {
        super(itemView);
        //pokemonNameView = itemView.findViewById(R.id.pokemonNameView);
    }

//    public void bind(String text) {
//        pokemonNameView.setText(text);
//    }

    static PokemonViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_pokedex, parent, false);
        return new PokemonViewHolder(view);
    }


}
