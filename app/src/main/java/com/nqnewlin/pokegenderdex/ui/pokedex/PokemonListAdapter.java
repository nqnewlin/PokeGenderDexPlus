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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnewlin.pokegenderdex.R;
import com.nqnewlin.pokegenderdex.helpers.LoadImage;
import com.nqnewlin.pokegenderdex.models.List.BlankItem;
import com.nqnewlin.pokegenderdex.models.List.ListItem;
import com.nqnewlin.pokegenderdex.models.List.PokeItem;
import com.nqnewlin.pokegenderdex.models.List.RegionItem;
import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.RegionId;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PokemonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private LoadImage loadImage;

        private TextView pokemonNameText;
        private TextView pokemonIdText;
        private ImageView pokemonImage;
        private ImageView maleGenderIcon;
        private ImageView femaleGenderIcon;
        private ImageView shinyIcon;


        public ViewHolder(View itemView) {
            super(itemView);

            //pokemonNameText = (TextView) itemView.findViewById(R.id.pokemonNameView);
            //pokemonIdText = (TextView) itemView.findViewById(R.id.pokemonIdView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.pokemonImage);
            maleGenderIcon = (ImageView) itemView.findViewById(R.id.maleIcon);
            femaleGenderIcon = (ImageView) itemView.findViewById(R.id.femaleIcon);
            shinyIcon = (ImageView) itemView.findViewById(R.id.shinyIcon);

            loadImage = new LoadImage();

            context = itemView.getContext();
        }
    }
    public class RegionViewHolder extends RecyclerView.ViewHolder {
        private TextView regionNameText;
        public RegionViewHolder(View itemView) {
            super(itemView);

            regionNameText = (TextView) itemView.findViewById(R.id.regionName);
        }
    }
    public class BlankViewHolder extends RecyclerView.ViewHolder {
        private CardView blankCard;
        public BlankViewHolder(View itemView) {
            super(itemView);

            blankCard = (CardView) itemView.findViewById(R.id.blankCard);
        }
    }

    private List<Pokemon> mPokemon;

    private List<ListItem> mItems;

    public PokemonListAdapter(List<ListItem> mItems) { this.mItems = mItems; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case ListItem.TYPE_HEADER: {
                View itemView = inflater.inflate(R.layout.recyclerview_region, parent,false);
                RegionViewHolder regionViewHolder = new RegionViewHolder(itemView);
                return regionViewHolder;
            }
            case ListItem.TYPE_POKE: {
                View scannedView = inflater.inflate(R.layout.recyclerview_pokedex, parent, false);
                ViewHolder viewHolder = new ViewHolder(scannedView);
                return viewHolder;
            }
            case ListItem.TYPE_EMPTY: {
                View itemView = inflater.inflate(R.layout.recyclerview_blank, parent, false);
                BlankViewHolder blankViewHolder = new BlankViewHolder(itemView);
                return blankViewHolder;
            }
            default:
                throw new IllegalStateException("unsupported item type");
        }


    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ListItem.TYPE_HEADER: {
                RegionItem regionItem = (RegionItem) mItems.get(position);
                RegionViewHolder holder = (RegionViewHolder) viewHolder;
                RegionId regionId = regionItem.getRegionId();
                TextView regionName = holder.regionNameText;
                regionName.setText(regionId.getName().substring(0,1).toUpperCase(Locale.ROOT)
                        + regionId.getName().substring(1));
                break;

            }
            case ListItem.TYPE_EMPTY: {
                BlankItem blankItem = (BlankItem) mItems.get(position);
                BlankViewHolder holder = (BlankViewHolder) viewHolder;
                CardView blankCard = holder.blankCard;
                blankCard.setVisibility(View.INVISIBLE);


                break;
            }
            case ListItem.TYPE_POKE: {
                PokeItem pokeItem = (PokeItem) mItems.get(position);
                ViewHolder holder = (ViewHolder) viewHolder;
                Pokemon pokemon = pokeItem.getPokemon();
                TextView pokeName = holder.pokemonNameText;
                //TextView pokeId = holder.pokemonIdText;
                ImageView pokeImage = holder.pokemonImage;
                ImageView maleIcon = holder.maleGenderIcon;
                ImageView femaleIcon = holder.femaleGenderIcon;
                ImageView shinyIcon = holder.shinyIcon;
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
//                        Random maleRandom = new Random();
//                        Random femaleRand = new Random();
//                        if (!maleRandom.nextBoolean()) {
//                            maleIcon.setColorFilter(filter);
//                        }
//                        if (!femaleRand.nextBoolean()) {
//                            femaleIcon.setColorFilter(filter);
//                        }

//                        //TODO implement gender icon visibility
                        maleIcon.setVisibility(View.VISIBLE);
                        femaleIcon.setVisibility(View.VISIBLE);
                        shinyIcon.setVisibility(View.INVISIBLE);
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

                        //TODO open pokemon view activity
                    }

                });
                break;

            }
            default:
                throw new IllegalStateException("unsupported item type");
        }


        //TODO add logic to add pokemon to recyclerview textviews
    }


    //TODO correct size calculation
//    @Override
//    public int getItemCount() {
//        if (mPokemon != null) {
//            int size = 0;
//            for (Pokemon pokemon: mPokemon) {
//                if (!pokemon.isForm()) { size++; }
//            }
//            return size;
//            //return mPokemon.size();
//        }
//        return 0;
//    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            int size = 0;
            for (int i = 0; i < mItems.size(); i++) {
                if (mItems.get(i).getType() == 1) {
                    PokeItem pokeItem = (PokeItem) mItems.get(i);
                    if (!pokeItem.getPokemon().isForm()) {
                        size++;
                    }
                } else if (mItems.get(i).getType() == 0) {
                    size++;
                }
            }
            return size;
        }
        return 0;
//        if (mItems != null) {
//            return mItems.size();
//        }
//        return 0;
    }

    //FUCNTION TO GET LIVE DATA HERE
    public void setPokemon(List<Pokemon> pokemons){
        this.mPokemon = pokemons;
        notifyDataSetChanged();
    }

    public void setList(List<ListItem> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }
}


