package org.equinoxprojects.voidchest;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class VoidChestPlugin extends JavaPlugin
{
    private static VoidChestPlugin plugin;
    private final String version = "V0.0.1-DEVELOPMENT";
    private final boolean release = false;

    public static VoidChestPlugin getInstance()
    {
        return plugin == null ? plugin = VoidChestPlugin.getPlugin(VoidChestPlugin.class) : plugin;
    }

    public void onEnable()
    {
        if(!release)
            System.out.println("Plugin successfully loaded!");
    }
}
