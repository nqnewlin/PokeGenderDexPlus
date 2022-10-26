package com.nqnewlin.pokegenderdex.ui.pokedex;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnewlin.pokegenderdex.R;
import com.nqnewlin.pokegenderdex.models.RegionId;

import java.util.Arrays;
import java.util.List;

public class RegionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class RegionTitleViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        private TextView regionName;

        private FragmentCommunicator mCommunicator;

        public RegionTitleViewHolder(View itemView, FragmentCommunicator communicator) {
            super(itemView);

            regionName = (TextView) itemView.findViewById(R.id.regionTitleName);
            context = itemView.getContext();
            mCommunicator = communicator;

        }
    }

    private List<RegionId> mRegions;

    private FragmentCommunicator mCommunicator;


    public RegionListAdapter(List<RegionId> mRegions, FragmentCommunicator communicator) {
        this.mRegions = mRegions;
        this.mCommunicator = communicator;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.recyclerview_regiontitle, parent, false);
        RegionTitleViewHolder viewHolder = new RegionTitleViewHolder(itemView, mCommunicator);

//        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        mCommunicator.cardSize(width / 250);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RegionId regionId = mRegions.get(position);
        RegionTitleViewHolder viewHolder = (RegionTitleViewHolder) holder;
        TextView regionName = viewHolder.regionName;
        regionName.setText(regionId.getName());

        Bundle bundle = new Bundle();
        bundle.putString("NAME", regionId.getName());
        PokedexFragment pokedexFragment = new PokedexFragment();
        pokedexFragment.setArguments(bundle);



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = regionId.getName();
                mCommunicator.respond(viewHolder.getAdapterPosition(), name);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mRegions != null) {
            return mRegions.size();
        }
        return 0;
    }



}
