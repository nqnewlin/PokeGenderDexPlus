package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnewlin.pokegenderdex.R;
import com.nqnewlin.pokegenderdex.helpers.LoadImage;
import com.nqnewlin.pokegenderdex.models.Pokemon;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private LoadImage loadImage;

        private TextView pokemonNameText;
        private TextView pokemonIdText;
        private ImageView pokemonImage;
        private ImageView maleGenderIcon;
        private ImageView femaleGenderIcon;


        public ViewHolder(View itemView) {
            super(itemView);

            //pokemonNameText = (TextView) itemView.findViewById(R.id.pokemonNameView);
            //pokemonIdText = (TextView) itemView.findViewById(R.id.pokemonIdView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.pokemonImage);
            maleGenderIcon = (ImageView) itemView.findViewById(R.id.maleIcon);
            femaleGenderIcon = (ImageView) itemView.findViewById(R.id.femaleIcon);

            loadImage = new LoadImage();

            context = itemView.getContext();
        }
    }

    private List<Pokemon> mPokemon;

    public PokemonListAdapter(List<Pokemon> mPokemon) { this.mPokemon = mPokemon; }

    @NonNull
    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View scannedView = inflater.inflate(R.layout.recyclerview_pokedex, parent, false);

        ViewHolder viewHolder = new ViewHolder(scannedView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonListAdapter.ViewHolder holder, int position) {
        Pokemon pokemon = mPokemon.get(position);
        TextView pokeName = holder.pokemonNameText;
        //TextView pokeId = holder.pokemonIdText;
        ImageView pokeImage = holder.pokemonImage;
        ImageView maleIcon = holder.maleGenderIcon;
        ImageView femaleIcon = holder.femaleGenderIcon;
        LoadImage loadImage = holder.loadImage;

        /**
         * hide pokemon if isForm true
         */
        if (!pokemon.isForm()) {
            //pokeName.setText(pokemon.getName());
            //pokeId.setText(String.valueOf(pokemon.getId()));
            try {
                Drawable imae = loadImage.loadImage(holder.context, pokemon.getId());
                pokeImage.setImageDrawable(loadImage.loadImage(holder.context, pokemon.getId()));

                /**TODO implement greyscale for unowned pokemon
                 */
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                //pokeImage.setColorFilter(filter);
                //maleIcon.setColorFilter(filter);
                //femaleIcon.setColorFilter(filter);

                //TODO temp grey icon
                Random maleRandom = new Random();
                Random femaleRand = new Random();
                if (!maleRandom.nextBoolean()) {
                    maleIcon.setColorFilter(filter);
                }
                if (!femaleRand.nextBoolean()) {
                    femaleIcon.setColorFilter(filter);
                }


                if (pokemon.getGenderRate() < 0) {
                    maleIcon.setVisibility(View.INVISIBLE);
                    femaleIcon.setVisibility(View.INVISIBLE);
                } else if (pokemon.getGenderRate() == 0) {
                    femaleIcon.setVisibility(View.INVISIBLE);
                } else if (pokemon.getGenderRate() == 8) {
                    maleIcon.setVisibility(View.INVISIBLE);
                }
            } catch (IOException ex) {
                Log.e("ERROR", "Image load error");
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toast = "#" + pokemon.getId() + ", " + pokemon.getName();
                Toast.makeText(v.getContext(), toast, Toast.LENGTH_SHORT).show();

            }

        });

        //TODO add logic to add pokemon to recyclerview textviews
    }


    //TODO correct size calculation
    @Override
    public int getItemCount() {
        if (mPokemon != null) {
            int size = 0;
            for (Pokemon pokemon: mPokemon) {
                if (!pokemon.isForm()) { size++; }
            }
            return size;
            //return mPokemon.size();
        }
        return 0;
    }

    //FUCNTION TO GET LIVE DATA HERE
    public void setPokemon(List<Pokemon> pokemons){
        this.mPokemon = pokemons;
        notifyDataSetChanged();
    }
}


