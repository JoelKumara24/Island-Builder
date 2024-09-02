package com.example.mapbuilder;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;


public class MapFragment extends Fragment {

    private RecyclerView recyclerView;
    MapData data;
    MapAdapter mapAdapter;
    Context context;

    static boolean Remove;



    public MapFragment() {

        Remove = false;
        data=MapData.get();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        Button mapButton = view.findViewById(R.id.removeButton);
        Button regenButton = view.findViewById(R.id.regenerateButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Remove = true;
            }
        });

        regenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.regenerate();

                // Notify the adapter that the data has changed
                mapAdapter.notifyItemRangeChanged(0, MapData.WIDTH * MapData.HEIGHT);
            }
        });

        mapAdapter = new MapAdapter( this);
        recyclerView = view.findViewById(R.id.mapRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MapData.HEIGHT, GridLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mapAdapter);

        return view;
    }

    public void setSelectedStructure(int position) {
        int row = position % MapData.HEIGHT;
        int col = position / MapData.HEIGHT;
        SelectorFragment selectorFragment = (SelectorFragment) getActivity().getSupportFragmentManager().findFragmentByTag("SelFragmentTag");

        if(Remove)
        {
            data.get(row, col).setStructure(null);
            mapAdapter.notifyItemChanged(position);
            Toast.makeText(getContext(), "Removed!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (selectorFragment != null) {
                // Call the SelectStructure method of the Selector Fragment
                //selectorFragment.SelectStructure(selectedStructure);
                if (SelectorFragment.getStructure() != null)
                {
                    if (data.get(row, col).isBuildable())
                    {
                        data.get(row, col).setStructure(SelectorFragment.getStructure());
                        mapAdapter.notifyItemChanged(position);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Can't build there!", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(getContext(), "Select an Item!", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }
}
