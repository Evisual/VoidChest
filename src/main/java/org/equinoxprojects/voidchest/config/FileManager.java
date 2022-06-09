package org.equinoxprojects.voidchest.config;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class FileManager
{
    private static FileManager instance;

    public static FileManager getInstance()
    {
        return instance == null ? instance = new FileManager() : instance;
    }

    private @Getter Config chestsConfig;

    public void loadFiles(final JavaPlugin plugin)
    {
        chestsConfig = new Config(plugin, "chests.yml");
    }

}
