package org.equinoxprojects.voidchest;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.equinoxprojects.voidchest.config.FileManager;
import org.equinoxprojects.voidchest.economy.EconomyManager;

public class VoidChestPlugin extends JavaPlugin
{
    private @Getter EconomyManager economy;
    private static VoidChestPlugin plugin;
    private final String version = "V0.0.1-DEVELOPMENT";
    private final boolean release = false;

    public static VoidChestPlugin getInstance()
    {
        return plugin;
    }

    public void onEnable()
    {
        plugin = this;

        if(!release)
            System.out.println("Plugin successfully loaded!");

        FileManager.getInstance().loadFiles(getInstance());
        economy = new EconomyManager(plugin);
    }
}
