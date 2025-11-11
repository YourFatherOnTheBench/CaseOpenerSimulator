package com.example.caseopener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private final List<Skin> items;

    public GridAdapter(List<Skin> items) {
        this.items = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView SkinName, Rarity, Wear;

        public ViewHolder(View itemView) {
            super(itemView);
            SkinName = itemView.findViewById(R.id.nameSkin);
            Rarity = itemView.findViewById(R.id.raritySkin);
            Wear = itemView.findViewById(R.id.wearSkin);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.block, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.ViewHolder holder, int position) {
        Skin item = items.get(position);
        holder.SkinName.setText(item.getName());
        holder.Rarity.setText(item.getRarity());
        holder.Wear.setText(item.getWear());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Add new items
    public void addItem(Skin item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }
}
