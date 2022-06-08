package org.equinoxprojects.voidchest.chest;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class VoidChest
{
    private HashMap<Material, Integer> itemAmounts = new HashMap<>();
    private final Location loc;

    public VoidChest(Location loc)
    {
        this.loc = loc;
        loadItemAmounts();
    }

    private void loadItemAmounts()
    {

    }



}
