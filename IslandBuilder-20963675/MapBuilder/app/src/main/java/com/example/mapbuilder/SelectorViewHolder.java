package com.example.mapbuilder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    // Define views for the selector list item
    private ImageView itemImageView;
    private TextView itemTextView;
    private int dimlight;

    public SelectorFragment selectorFragment;

    ConstraintLayout selection;
    public SelectorViewHolder(@NonNull View itemView,SelectorFragment selectorFragment) {
        super(itemView);

        // Initialize views by finding their IDs within the selector list item layout
        itemImageView = itemView.findViewById(R.id.selector_icon);
        itemTextView = itemView.findViewById(R.id.selector_title);
        selection = itemView.findViewById(R.id.selection);
        this.selectorFragment = selectorFragment;
    }





    // Getter methods for accessing the views
    public ImageView getItemImageView() {

        return itemImageView;
    }

    public TextView getItemTextView() {
        return itemTextView;
    }

    @Override
    public void onClick(View view) {
        //selectorFragment.SelectStructure(getAbsoluteAdapterPosition(),view);
    }
}
