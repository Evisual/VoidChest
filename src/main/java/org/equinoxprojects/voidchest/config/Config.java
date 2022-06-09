package org.equinoxprojects.voidchest.config;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config
{
    private final JavaPlugin plugin;
    private final @Getter File file;
    private final @Getter FileConfiguration config;

    public Config(final JavaPlugin plugin, final String file)
    {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), file);
        this.config = YamlConfiguration.loadConfiguration(this.file);

        if(createFile()) return;
        save();
    }

    public void save()
    {
        try {
            config.save(file);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void reload()
    {
        try {
            config.load(file);
        } catch(IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    private boolean createFile()
    {
        try {
            return file.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return false;
    }




}
