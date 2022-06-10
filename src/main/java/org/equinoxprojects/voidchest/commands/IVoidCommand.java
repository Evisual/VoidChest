package org.equinoxprojects.voidchest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.equinoxprojects.voidchest.commands.exceptions.NoPermissionsException;
import org.equinoxprojects.voidchest.commands.exceptions.NotEnoughArgumentsException;
import org.equinoxprojects.voidchest.commands.exceptions.UnknownCommandException;

import java.util.Map;

public interface IVoidCommand
{
    void execute(final CommandSender sender, final Command cmd, final String label, final String[] args, final JavaPlugin mainclass) throws UnknownCommandException, NotEnoughArgumentsException, NoPermissionsException;

    String getName();

    Map<String, String> getUsageStrings();

    String getUsage();


}
