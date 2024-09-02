package com.example.mapbuilder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stores the list of possible structures. This has a static get() method for retrieving an
 * instance, rather than calling the constructor directly.
 *
 * The remaining methods -- get(int), size(), add(Structure) and remove(int) -- provide
 * minimalistic list functionality.
 *
 * There is a static int array called DRAWABLES, which stores all the drawable integer references,
 * some of which are not actually used (yet) in a Structure object.
 */
public class StructureData
{
    public static final int[] DRAWABLES = {
        0, // No structure
        R.drawable.ic_building1, R.drawable.ic_building2, R.drawable.ic_building3,
        R.drawable.ic_building4, R.drawable.ic_building5, R.drawable.ic_building6,
        R.drawable.ic_building7, R.drawable.ic_building8,
        R.drawable.ic_road_ns, R.drawable.ic_road_ew, R.drawable.ic_road_nsew,
        R.drawable.ic_road_ne, R.drawable.ic_road_nw, R.drawable.ic_road_se, R.drawable.ic_road_sw,
        R.drawable.ic_road_n, R.drawable.ic_road_e, R.drawable.ic_road_s, R.drawable.ic_road_w,
        R.drawable.ic_road_nse, R.drawable.ic_road_nsw, R.drawable.ic_road_new, R.drawable.ic_road_sew,
        R.drawable.ic_tree1, R.drawable.ic_tree2, R.drawable.ic_tree3, R.drawable.ic_tree4};

    private List<Structure> structureList = new ArrayList<>(Arrays.asList(new Structure[] {
            new Structure(R.drawable.ic_building1, "House1"),
            new Structure(R.drawable.ic_building2, "House2"),
            new Structure(R.drawable.ic_road_ns, "Road1"),
            new Structure(R.drawable.ic_road_ew, "Road2"),
            new Structure(R.drawable.ic_road_nsew, "Road3"),
            new Structure(R.drawable.ic_road_ne, "Road4"),
            new Structure(R.drawable.ic_road_nw, "Road5"),
            new Structure(R.drawable.ic_road_se, "Road6"),
            new Structure(R.drawable.ic_road_sw, "Road7"),
            new Structure(R.drawable.ic_road_n, "Road8"),
            new Structure(R.drawable.ic_road_e, "Road9"),
            new Structure(R.drawable.ic_road_s, "Road10"),
            new Structure(R.drawable.ic_road_w, "Road11"),
            new Structure(R.drawable.ic_road_nse, "Road12"),
            new Structure(R.drawable.ic_road_nsw, "Road13"),
            new Structure(R.drawable.ic_road_new, "Road14"),
            new Structure(R.drawable.ic_road_sew, "Road15"),
            new Structure(R.drawable.ic_tree1, "Tree1"),
            new Structure(R.drawable.ic_tree2, "Tree2"),
            new Structure(R.drawable.ic_tree3, "Tree3"),
            new Structure(R.drawable.ic_tree4, "Tree4")

    }));

    private static StructureData instance = null;

    public static StructureData get()
    {
        if(instance == null)
        {
            instance = new StructureData();
        }
        return instance;
    }

    protected StructureData() {}

    public Structure get(int i)
    {
        return structureList.get(i);
    }

    public int size()
    {
        return structureList.size();
    }

    public void add(Structure s)
    {
        structureList.add(0, s);
    }

    public void remove(int i)
    {
        structureList.remove(i);
    }
    public void moveItem(int fromPosition, int toPosition) {
        // Move the item within your data structure
        Structure movedItem = structureList.remove(fromPosition);
        structureList.add(toPosition, movedItem);
    }

    public Structure getStructureByLabel(String label) {
        for (Structure structure : structureList) {
            if (structure.getLabel().equals(label)) {
                return structure;
            }
        }
        // If the structure with the given label is not found, return null or throw an exception as needed.
        return null;
    }


}
