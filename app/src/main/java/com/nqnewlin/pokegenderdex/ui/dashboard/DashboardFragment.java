package com.nqnewlin.pokegenderdex.ui.dashboard;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nqnewlin.pokegenderdex.MainActivity;
import com.nqnewlin.pokegenderdex.databinding.ActivityMainBinding;
import com.nqnewlin.pokegenderdex.databinding.FragmentDashboardBinding;
import com.nqnewlin.pokegenderdex.ui.pokedex.PokedexFragment;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private Button loadCsv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        //get display size
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        Bundle bundle = new Bundle();
        bundle.putInt("WIDTH", width);
        MainActivity activity = (MainActivity) getActivity();
        getParentFragmentManager().setFragmentResult("requestKey", bundle);


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;



        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}