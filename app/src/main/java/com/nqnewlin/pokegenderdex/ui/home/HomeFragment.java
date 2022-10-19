package com.nqnewlin.pokegenderdex.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nqnewlin.pokegenderdex.databinding.FragmentHomeBinding;
import com.nqnewlin.pokegenderdex.models.Pokemon;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        final Observer<Pokemon> pokemonObserver = new Observer<Pokemon>() {
            @Override
            public void onChanged(@Nullable final Pokemon newPokemon) {
                // Update the UI, in this case, a TextView.
                textView.setText(newPokemon.getName());
            }
        };

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}