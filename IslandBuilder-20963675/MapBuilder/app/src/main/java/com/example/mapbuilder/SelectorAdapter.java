package com.example.mapbuilder;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SelectorAdapter extends RecyclerView.Adapter<SelectorViewHolder> {
    private StructureData itemList;
    private SelectorFragment context;
    //private OnItemSelectedListener itemSelectedListener;

    public SelectorAdapter(StructureData itemList, SelectorFragment context) {
        this.itemList = itemList;
        this.context = context;
    }




    @NonNull
    @Override
    public SelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_selection, parent, false);
        return new SelectorViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectorViewHolder holder, int position) {
        holder.getItemImageView().setImageResource(itemList.get(position).getDrawableId());
        holder.getItemTextView().setText(itemList.get(position).getLabel());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String IMAGEVIEW_TAG = "icon bitmap";
                int clickedPosition = holder.getAbsoluteAdapterPosition() ;
                Structure selectedStructure = itemList.get(clickedPosition);

                // Get the long-clicked item's structure from the itemView's tag
                //Structure selectedStructure = (Structure) view.getTag();

                // Create a new ClipData.Item from the selected structure
                ClipData.Item item = new ClipData.Item(selectedStructure.getLabel());

                // Create a new ClipData using the structure's label as a label, a custom MIME type,
                // and the already-created item.
                ClipData dragData = new ClipData(
                        selectedStructure.getLabel(), // Label (you can customize this)
                        new String[] { "application/your.custom.mimetype" }, // Custom MIME type
                        item);

                // Instantiate the drag shadow builder.
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(holder.itemView);

                // Start the drag.
                view.startDragAndDrop(dragData, myShadow, null, 0);

                // Indicate that the long-click is handled.
                return true;
            }
        });

        // Highlight the item when selected
        if (context.getHighlight() == position) {
            holder.selection.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.selection.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item's position
                int clickedPosition = holder.getAbsoluteAdapterPosition() ;

                // Check if the position is valid
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // Retrieve the selected structure from your data source (e.g., itemList)
                    Structure selectedStructure = itemList.get(clickedPosition);

                    // Call your method to handle the structure click
                    context.SelectStructure(selectedStructure);
                    context.setHighlight(clickedPosition);

                    int previousPosition = context.previouslySelectedItemPosition;
                    context.previouslySelectedItemPosition = clickedPosition;

                    // Notify the adapter of the data change to remove the highlight
                    if (previousPosition != -1) {
                        notifyItemChanged(previousPosition);
                    }

                    // Highlight the current item
                    notifyItemChanged(clickedPosition);
                }

            }
        });

        if(context.getHighlight() == position ){

            holder.selection.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }  else    {
            holder.selection.setBackgroundColor(Color.TRANSPARENT);
        }  //


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
