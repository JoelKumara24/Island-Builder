package com.example.mapbuilder;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    // Define views for the grid cell
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;

    private MapFragment mapFragment;

    public MapViewHolder(@NonNull View itemView,MapFragment mapFragment) {
        super(itemView);

        // Initialize views by finding their IDs within the grid cell layout
        imageView1 = itemView.findViewById(R.id.mapCell1);
        imageView2 = itemView.findViewById(R.id.mapCell2);
        imageView3 = itemView.findViewById(R.id.mapCell3);
        imageView4 = itemView.findViewById(R.id.mapCell4);
        imageView5 = itemView.findViewById(R.id.mapCell5);
        this.mapFragment= mapFragment;
    }

    // Getter methods for accessing the ImageView elements
    public ImageView getImageView1() {
        return imageView1;
    }

    public ImageView getImageView2() {
        return imageView2;
    }

    public ImageView getImageView3() {
        return imageView3;
    }

    public ImageView getImageView4() {
        return imageView4;
    }

    public ImageView getImageView5() {
        return imageView5;
    }

    @Override
    public void onClick(View view) {
        //mapFragment.setSelectedStructure(getAbsoluteAdapterPosition(),view);
    }
}
