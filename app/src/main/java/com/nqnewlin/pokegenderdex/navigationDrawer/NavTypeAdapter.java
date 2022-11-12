package com.nqnewlin.pokegenderdex.navigationDrawer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnewlin.pokegenderdex.R;
import com.nqnewlin.pokegenderdex.helpers.LoadImage;
import com.nqnewlin.pokegenderdex.models.constants.Types;
import com.nqnewlin.pokegenderdex.ui.pokedex.TypeColorValidator;

import java.util.List;

public class NavTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public class NavRegionViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        private TextView typeNameText;
        private ImageView typeIcon;
        private CardView typeCard;

        private LoadImage loadImage;

        private TypeFragmentCommunicator mCommunicator;

        public NavRegionViewHolder(@NonNull View itemView) {
            super(itemView);

            typeNameText = (TextView) itemView.findViewById(R.id.typeSelectName);
            typeIcon = (ImageView) itemView.findViewById(R.id.typeIcon);
            typeCard = (CardView) itemView.findViewById(R.id.typeCard);

            loadImage = new LoadImage();
            context = itemView.getContext();
        }
    }

    private List<Types> mTypes;
    private TypeFragmentCommunicator mCommunicator;

    public NavTypeAdapter(List<Types> mTypes,
                          TypeFragmentCommunicator mCommunicator) {
        this.mTypes = mTypes;
        this.mCommunicator = mCommunicator;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.recyclerview_navbar, parent, false);

        NavRegionViewHolder viewHolder = new NavRegionViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NavRegionViewHolder viewHolder = (NavRegionViewHolder) holder;
        TextView typeName = viewHolder.typeNameText;
        ImageView typeIcon = viewHolder.typeIcon;
        CardView typeCard = viewHolder.typeCard;
        LoadImage loadImage = viewHolder.loadImage;

        Types current = mTypes.get(position);
        typeName.setText(current.getName());

        if (current.getSelected()) {
            typeCard.setAlpha(1.0F);
        } else {
            typeCard.setAlpha(0.15F);
        }

        int color = ((NavRegionViewHolder) viewHolder).typeCard.getContext().getResources()
                .getColor(TypeColorValidator.colorValidator(current.getName().toLowerCase()));
        typeCard.setCardBackgroundColor(color);

        try {
            typeIcon.setImageDrawable(loadImage.loadType(viewHolder.context, current.getName()));
        } catch (Exception e) {
            Log.e("ERROR", "Image load error");
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current.setSelected(!current.getSelected());
                if (current.getSelected()) {
                    typeCard.setAlpha(1.0F);
                    mCommunicator.addType(current.getName());
                } else {
                    typeCard.setAlpha(0.15F);
                    mCommunicator.removeType(current.getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mTypes != null) {
            return mTypes.size();
        }
        return 0;
    }
}
