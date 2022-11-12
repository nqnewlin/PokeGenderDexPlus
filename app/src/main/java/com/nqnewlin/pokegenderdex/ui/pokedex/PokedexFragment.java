package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnewlin.pokegenderdex.MainActivity;
import com.nqnewlin.pokegenderdex.R;

import com.nqnewlin.pokegenderdex.databinding.FragmentPokedexBinding;
import com.nqnewlin.pokegenderdex.models.List.BlankItem;
import com.nqnewlin.pokegenderdex.models.List.ListItem;
import com.nqnewlin.pokegenderdex.models.List.LoadItems;
import com.nqnewlin.pokegenderdex.models.List.PokeItem;
import com.nqnewlin.pokegenderdex.models.List.RegionItem;
import com.nqnewlin.pokegenderdex.models.Pokemon;
import com.nqnewlin.pokegenderdex.models.RegionId;
import com.nqnewlin.pokegenderdex.models.constants.Types;
import com.nqnewlin.pokegenderdex.ui.pokedex.helpers.ViewAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PokedexFragment extends Fragment {

    public DrawerLayout drawerLayout;

    private FragmentPokedexBinding binding;

    private PokedexViewModel mPokedexViewModel;

    private PokemonListAdapter adapter;
    private RegionListAdapter regionAdapter;

    private List<Pokemon> mPokemons;

    private TreeMap<RegionId, List<Pokemon>> mPokeMap;

    private List<ListItem> mItems;
    private List<RegionId> mRegions;
    private String mCurrentRegion;
    private List<String> mTypes;

    GridLayoutManager layoutManager;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    RecyclerView.SmoothScroller smoothScroller;

    boolean isRotate = false;

    //Default width value
    private int GRID_WIDTH = 4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPokedexViewModel =
                new ViewModelProvider(this).get(PokedexViewModel.class);

        drawerLayout = getActivity().findViewById(R.id.my_drawer_layout);

        // Calculate grid width
        MainActivity activity = (MainActivity) getActivity();
        Bundle bundle = activity.getScreenSize();
        if (bundle != null) {
            GRID_WIDTH = calculateGrid(bundle.getInt("WIDTH"));
        }

        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewAnimation.init(binding.fabType);
        ViewAnimation.init(binding.fabGeneration);
        ViewAnimation.init(binding.fabSearch);
        ViewAnimation.init(binding.cardSearch);

        binding.floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isRotate = ViewAnimation.rotateFab(view, !isRotate);
                if (isRotate){
                    ViewAnimation.showIn(binding.fabGeneration);
                    ViewAnimation.showIn(binding.fabType);
                    ViewAnimation.showIn(binding.fabSearch);
                }else{
                    ViewAnimation.showOut(binding.fabType);
                    ViewAnimation.showOut(binding.fabGeneration);
                    ViewAnimation.showOut(binding.fabSearch);
                }
            }
        });

        binding.fabGeneration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        binding.fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.fabType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.RIGHT, true);
            }
        });

        // Load recyclerviews for pokedex list and region navigation bar
        recyclerView = ( RecyclerView) root.findViewById(R.id.recyclerview);
        RecyclerView regionTitleView = (RecyclerView) root.findViewById(R.id.regionFilterRV);

        // Load Pokemon list from DB and regions
        mPokemons = mPokedexViewModel.getAllPokemon().getValue();
        mRegions = mPokedexViewModel.getRegions();
        mTypes = Types.types;

        //Define and set adapters to appropriate RecyclerViews
        adapter = new PokemonListAdapter(mItems, mTypes, communication);
        regionAdapter = new RegionListAdapter(mRegions, communication, mCurrentRegion);

        recyclerView.setAdapter(adapter);
        regionTitleView.setAdapter(regionAdapter);

        layoutManager = new GridLayoutManager(root.getContext(),GRID_WIDTH);
        recyclerView.setLayoutManager(layoutManager);

        //Add smoothScroller to move recyclerview to selected region
        smoothScroller = new LinearSmoothScroller(getContext()) {
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };


        linearLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(root.getContext(),
                linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.divider));
        regionTitleView.addItemDecoration(dividerItemDecoration);
        regionTitleView.setLayoutManager(linearLayoutManager);

        // Load pokemon list from DB and observe any changes
        mPokedexViewModel.getAllPokemon().observe(getActivity(), new Observer<List<Pokemon>>() {
                    @Override
                    public void onChanged(List<Pokemon> pokemons) {
                        adapter.setPokemon(pokemons);
                        mPokemons = pokemons;
                        adapter.notifyDataSetChanged();

                        LoadItems loadItems = new LoadItems();
                        mPokeMap = LoadItems.loadList(mPokemons);

                        mItems = new ArrayList<>();

                        for (RegionId id : mPokeMap.keySet()) {
                            RegionItem region = new RegionItem(id);
                            mItems.add(region);
                            for (int i = 0; i < (GRID_WIDTH - 1); i++) {
                                mItems.add(new BlankItem());
                            }

                            for (Pokemon pokemon : mPokeMap.get(id)) {
                                PokeItem pokeItem = new PokeItem(pokemon);
                                mItems.add(pokeItem);
                            }
                            int pokeCount =(2 * GRID_WIDTH - (mPokeMap.get(id).size() % GRID_WIDTH));
                            for (int i = 0; i < pokeCount; i++) {
                                mItems.add(new BlankItem());
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

    /** Get region name from onClick RegionListAdapter filters and scroll
     *  or jump to selected region
     */
    PokedexFragmentCommunicator communication = new PokedexFragmentCommunicator() {
        @Override
        public void respond(int position, String regionName) {
            int index = 0;
            for (int i = 0; i < mItems.size(); i++) {
                if (mItems.get(i).getType() == ListItem.TYPE_HEADER) {
                    RegionItem regionItem = (RegionItem) mItems.get(i);
                    if (regionItem.getRegionId().getName().equalsIgnoreCase(regionName)) {
                        index = i;
                    }
                }
            }
            int current = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
            if (Math.abs(current - index) > 100) {
                recyclerView.scrollToPosition(index);
            } else {
                smoothScroller.setTargetPosition(index);
                layoutManager.startSmoothScroll(smoothScroller);
            }
        }

        @Override
        public void currentRegion(String currentRegion) {
            mCurrentRegion = currentRegion;
            regionAdapter.setRegion(mCurrentRegion);
            regionAdapter.notifyDataSetChanged();

            //TODO add scroll to regionId view header
        }

    };

    public int calculateGrid(int width) {
        return width / 400;
    }
}