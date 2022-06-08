package org.equinoxprojects.voidchest.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config
{
    private final JavaPlugin plugin;
    private final File file;
    private final FileConfiguration config;

    public Config(final JavaPlugin plugin, final String file)
    {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), file);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save()
    {
        try
        {
            config.save(file);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void reload()
    {
        try
        {
            config.load(file);
        } catch(IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }



}
