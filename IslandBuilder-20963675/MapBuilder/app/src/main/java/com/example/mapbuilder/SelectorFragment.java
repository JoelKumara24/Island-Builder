package com.example.mapbuilder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Struct;

public class SelectorFragment extends Fragment {
    private RecyclerView recyclerView;
    private SelectorAdapter adapter;

    static Structure structure;

    static int highlight;
    public int previouslySelectedItemPosition = -1;

    private MapFragment mapFragment;






    StructureData structureData;

    public SelectorFragment() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the adapter with your StructureData and the current context
        adapter = new SelectorAdapter(StructureData.get(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selector, container, false);

        // Find the RecyclerView in the fragment's layout
        recyclerView = view.findViewById(R.id.selectorRecyclerView);

        // Set up the RecyclerView with a GridLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false)); // You can adjust the span count (3 in this case)

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(adapter);
        //itemTouchHelper.attachToRecyclerView(recyclerView);



        return view;
    }

    public void SelectStructure(Structure stuct) {

        structure = stuct;
        MapFragment.Remove=false;


    }

   /* ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.START | ItemTouchHelper.END | ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
            // Get the positions of the source and target items
            int fromPosition = source.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            //int row = toPosition % MapData.HEIGHT;
            //int col = toPosition / MapData.HEIGHT;


            // Move the item in your data source (StructureData) if needed
            //structureData.moveItem(fromPosition, toPosition);

            mapFragment.setSelectedStructure(toPosition);

            // Notify the adapter about the move
            adapter.notifyItemMoved(fromPosition, toPosition);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            // Handle item swiping if needed
        }
    });*/





    public void setHighlight(int position)
    {

        int prevPos = highlight;
        highlight=position;




        adapter.notifyItemChanged(prevPos);
        adapter.notifyItemChanged(position);
    }

    public int getHighlight()
    {
        return highlight;
    }


    public static Structure getStructure() {
        return structure;
    }


}

