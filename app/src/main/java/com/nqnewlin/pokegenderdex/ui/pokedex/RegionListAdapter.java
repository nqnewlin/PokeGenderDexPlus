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
import androidx.cardview.widget.CardView;
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
        private CardView regionCard;

        private FragmentCommunicator mCommunicator;

        public RegionTitleViewHolder(View itemView, FragmentCommunicator communicator) {
            super(itemView);

            regionName = (TextView) itemView.findViewById(R.id.regionTitleName);
            regionCard = (CardView) itemView.findViewById(R.id.regionCard);
            context = itemView.getContext();
            mCommunicator = communicator;

        }
    }

    private List<RegionId> mRegions;

    private FragmentCommunicator mCommunicator;

    private String mCurrentRegion;


    public RegionListAdapter(List<RegionId> mRegions, FragmentCommunicator communicator, String mCurrentRegion) {
        this.mRegions = mRegions;
        this.mCommunicator = communicator;
        this.mCurrentRegion = mCurrentRegion;
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
        CardView regionCard = viewHolder.regionCard;
        regionName.setText(regionId.getName());

        System.out.println("Current region: " + mCurrentRegion);
        if (mCurrentRegion != null) {
            if (mCurrentRegion.equalsIgnoreCase(regionId.getName())) {
                int color = viewHolder.regionCard.getContext().getResources().getColor(R.color.purple_500);
                regionCard.setCardBackgroundColor(color);

            } else {
                int colorPurple = viewHolder.regionCard.getContext().getColor(R.color.purple_200);
                regionCard.setCardBackgroundColor(colorPurple);
            }
        }

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

    public void setRegion(String region) {
        this.mCurrentRegion = region;
        notifyDataSetChanged();
    }



}
