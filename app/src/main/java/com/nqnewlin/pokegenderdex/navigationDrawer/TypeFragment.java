package com.nqnewlin.pokegenderdex.navigationDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnewlin.pokegenderdex.R;
import com.nqnewlin.pokegenderdex.databinding.FragmentRegionBinding;
import com.nqnewlin.pokegenderdex.models.constants.Types;
import com.nqnewlin.pokegenderdex.ui.pokedex.PokedexFragment;
import com.nqnewlin.pokegenderdex.ui.pokedex.PokedexFragmentCommunicator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TypeFragment extends Fragment {

    private FragmentRegionBinding binding;

    private TextView selectText;
    boolean isSelect = true;
    private NavTypeAdapter adapter;

    private List<Types> mTypes;
    private PokedexFragmentCommunicator pokedexFragmentCommunicator;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mTypes = Types.buildList();

        RecyclerView recyclerView = ( RecyclerView) root.findViewById(R.id.rvnav);
        adapter = new NavTypeAdapter(mTypes, typeFragmentCommunicator);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        binding.selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelect = (!isSelect);
                if (isSelect) {
                    binding.selectAll.setText("Deselect All");
                    for (Types type : mTypes) {
                        type.setSelected(true);
                    }
                } else {
                    binding.selectAll.setText("Select All");
                    for (Types type : mTypes) {
                        type.setSelected(false);
                    }
                }
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

    TypeFragmentCommunicator typeFragmentCommunicator = new TypeFragmentCommunicator() {
        @Override
        public void addType(String type) {

        }

        @Override
        public void removeType(String type) {

        }
    };

}
