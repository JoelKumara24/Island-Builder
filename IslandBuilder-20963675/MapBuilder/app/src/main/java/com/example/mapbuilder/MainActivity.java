package com.example.mapbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    static boolean highlight;
    MapFragment mapFragment;
    SelectorFragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapData mapData = MapData.get();
        StructureData structureData = StructureData.get();
        mapFragment = new MapFragment();
        selectorFragment = new SelectorFragment();

        // Load the MapFragment into the first FrameLayout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.map_frame, mapFragment)
                .commit();

        // Load the SelectorFragment into the second FrameLayout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.selector_frame, selectorFragment, "SelFragmentTag")
                .commit();

        // Set the drag listener for the map_frame FrameLayout
        View mapFrame = findViewById(R.id.map_frame);
        mapFrame.setOnDragListener(mapFrameDragListener);

        // Set the drag listener for the selector_frame FrameLayout
        View selectorFrame = findViewById(R.id.selector_frame);
        selectorFrame.setOnDragListener(selectorFrameDragListener);
    }

    // Define the drag listener for the map_frame
    View.OnDragListener mapFrameDragListener = new View.OnDragListener() {


        @Override
        public boolean onDrag(View view, DragEvent event) {
            // Implement your drag and drop logic for the map_frame here
            switch (event.getAction()) {
                // Handle various drag events
                case DragEvent.ACTION_DRAG_STARTED:
                    // Handle drag started event
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Handle drag entered event
                    view.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    // Handle drag location event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    // Handle drag exited event
                    view.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    // Handle drag drop event
                    // Access dropped data and perform actions
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    String dragData = item.getText().toString();

                    // Retrieve the Structure using the label from dragData
                    StructureData structureData = StructureData.get();
                    Structure droppedStructure = structureData.getStructureByLabel(dragData);
                    selectorFragment.SelectStructure(droppedStructure);

                    if (droppedStructure != null) {

                        float x = event.getX();
                        float y = event.getY();

                        // Use the x and y coordinates to determine the position within the RecyclerView
                        int position = findPositionInRecyclerView(x, y);

                        // Check if the position is valid
                        if (position != RecyclerView.NO_POSITION) {

                            mapFragment.setSelectedStructure(position);
                        }
                        // The droppedStructure is the Structure that was dragged and dropped.
                        // You can now use this structure as needed.

                        // Example: Add the droppedStructure to the map
                        //mapFragment.addStructureToMap(droppedStructure);

                        // Return true to indicate that the drop was handled.
                        return true;
                    } else {
                        // Handle the case where the dropped structure couldn't be retrieved.
                        // You can display an error message or take appropriate action.
                        return false;
                    }

                default:
                    return false;
            }
        }
    };

    // Define the drag listener for the selector_frame
    View.OnDragListener selectorFrameDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            // Implement your drag and drop logic for the selector_frame here
            switch (event.getAction()) {
                // Handle various drag events
                case DragEvent.ACTION_DRAG_STARTED:
                    // Handle drag started event
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Handle drag entered event
                    view.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:
                    // Handle drag location event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:
                    // Handle drag exited event
                    view.invalidate();
                    return true;

                case DragEvent.ACTION_DROP:
                    // Handle drag drop event
                    // Access dropped data and perform actions
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    String dragData = item.getText().toString();
                    // Handle the drop logic for selector_frame here
                    // Example: Add the dropped item to the selector

                    return true;

                default:
                    return false;
            }
        }
    };
    private int findPositionInRecyclerView(float x, float y) {
        RecyclerView recyclerView = findViewById(R.id.mapRecyclerView); // Replace with your RecyclerView's ID

        if (recyclerView != null) {
            View childView = recyclerView.findChildViewUnder(x, y);

            if (childView != null) {
                int position = recyclerView.getChildLayoutPosition(childView);
                return position;
            }
        }

        return RecyclerView.NO_POSITION; // Return NO_POSITION if position cannot be determined
    }

}
