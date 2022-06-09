package org.equinoxprojects.voidchest.economy;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.equinoxprojects.voidchest.VoidChestPlugin;
import org.equinoxprojects.voidchest.config.FileManager;

import java.util.HashMap;

public class EconomyManager
{
    private final HashMap<Material, Integer> itemPrices = new HashMap<>();
    private final VoidChestPlugin plugin;

    public EconomyManager(VoidChestPlugin plugin)
    {
        this.plugin = plugin;
        loadItemAmounts();
    }

    private void loadItemAmounts()
    {
        FileConfiguration config = FileManager.getInstance().getChestsConfig().getConfig();
        if(!config.contains("items")) return;
        for(String s : config.getConfigurationSection("items").getKeys(false))
        {
            Material mat = Material.getMaterial(s);
            int price = config.getInt("items." + s);

            System.out.println("Mat: " + mat.name());
            System.out.println("Price: " + price);

            itemPrices.put(mat, price);
        }
    }


}
