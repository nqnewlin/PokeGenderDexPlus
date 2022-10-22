package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nqnewlin.pokegenderdex.R;

import com.nqnewlin.pokegenderdex.databinding.FragmentPokedexBinding;
import com.nqnewlin.pokegenderdex.models.List.ListItem;
import com.nqnewlin.pokegenderdex.models.List.LoadItems;
import com.nqnewlin.pokegenderdex.models.List.PokeItem;
import com.nqnewlin.pokegenderdex.models.List.RegionItem;
import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.RegionId;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;

    private PokedexViewModel mPokedexViewModel;

    private PokemonListAdapter adapter;

    private List<Pokemon> mPokemons;

    private TreeMap<RegionId, List<Pokemon>> mPokeMap;

    private List<ListItem> mItems;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPokedexViewModel =
                new ViewModelProvider(this).get(PokedexViewModel.class);


        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = ( RecyclerView) root.findViewById(R.id.recyclerview);

        mPokemons = mPokedexViewModel.getAllPokemon().getValue();


//        adapter = new PokemonListAdapter(mPokemons);
        adapter = new PokemonListAdapter(mItems);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager=new GridLayoutManager(root.getContext(),4);
        recyclerView.setLayoutManager(layoutManager);


        //recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mPokedexViewModel.getAllPokemon().observe(getActivity(), new Observer<List<Pokemon>>() {
                    @Override
                    public void onChanged(List<Pokemon> pokemons) {
                        adapter.setPokemon(pokemons);
                        mPokemons = pokemons;
                        adapter.notifyDataSetChanged();

                        System.out.println("Fragment pokemon list: " + mPokemons);

                        LoadItems loadItems = new LoadItems();
                        mPokeMap = LoadItems.loadList(mPokemons);



                        mItems = new ArrayList<>();

                        for (RegionId id : mPokeMap.keySet()) {
                            RegionItem region = new RegionItem(id);
                            mItems.add(region);
                            for (Pokemon pokemon : mPokeMap.get(id)) {
                                PokeItem pokeItem = new PokeItem(pokemon);
                                mItems.add(pokeItem);
                            }
                        }

                        adapter.setList(mItems);
                        adapter.notifyDataSetChanged();

                    }
                });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}