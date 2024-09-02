package com.example.mapbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MapAdapter extends RecyclerView.Adapter<MapViewHolder> {



    private final MapFragment mapFragment;

    public MapAdapter(  MapFragment mapFragment) {

        this.mapFragment = mapFragment;
    }

    @NonNull
    @Override
    public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the grid cell layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell, parent, false);
        int size = parent.getMeasuredHeight() / MapData.HEIGHT + 1;
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        lp.width = size;
        lp.height = size;
        return new MapViewHolder(itemView,mapFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull MapViewHolder holder, int position) {
        // Get the MapElement at the current position
        int row = position % MapData.HEIGHT;
        int col = position / MapData.HEIGHT;
        MapElement mapElement = MapData.get(row,col);

        // Set terrain and structure images for the grid cell
        holder.getImageView1().setImageResource(mapElement.getNorthWest());
        holder.getImageView2().setImageResource(mapElement.getNorthEast());
        holder.getImageView3().setImageResource(mapElement.getSouthWest());
        holder.getImageView4().setImageResource(mapElement.getSouthEast());
        holder.getImageView5().setImageResource(android.R.color.transparent);


        Structure structure = mapElement.getStructure();
        if (structure != null) {
            holder.getImageView5().setImageResource(structure.getDrawableId());
        } else {
            // If no structure is present, you can set a default image or leave it empty
            holder.getImageView5().setImageResource(android.R.color.transparent);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                // You can get the position of the clicked item if needed
                int clickedPosition = holder.getAbsoluteAdapterPosition();
                mapFragment.setSelectedStructure(clickedPosition);
                // Perform any actions or handle the click event as needed

            }

        });
    }

    @Override
    public int getItemCount() {
        return MapData.HEIGHT * MapData.WIDTH;
    }

}

