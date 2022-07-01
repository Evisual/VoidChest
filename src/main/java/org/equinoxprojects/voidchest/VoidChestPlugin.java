package org.equinoxprojects.voidchest;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.equinoxprojects.voidchest.chest.handlers.ChestPlaceHandler;
import org.equinoxprojects.voidchest.commands.AlternativeCommandHandler;
import org.equinoxprojects.voidchest.commands.IVoidCommand;
import org.equinoxprojects.voidchest.commands.exceptions.NoPermissionsException;
import org.equinoxprojects.voidchest.commands.exceptions.NotEnoughArgumentsException;
import org.equinoxprojects.voidchest.commands.exceptions.UnknownCommandException;
import org.equinoxprojects.voidchest.commands.subcommands.VoidGiveCommand;
import org.equinoxprojects.voidchest.commands.subcommands.VoidHelpCommand;
import org.equinoxprojects.voidchest.config.FileManager;
import org.equinoxprojects.voidchest.economy.EconomyManager;
import org.equinoxprojects.voidchest.util.Utils;

public class VoidChestPlugin extends JavaPlugin
{
    private @Getter EconomyManager economy;
    private static VoidChestPlugin plugin;
    private AlternativeCommandHandler commandHandler = new AlternativeCommandHandler(this);

    private final String version = "V0.0.1-DEVELOPMENT";
    private final boolean release = false;
    private static final @Getter String prefix = Utils.colorize("&4&lVoid &7» ");


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

        registerCommands();
        registerListeners();
    }

    public void registerCommands()
    {
        commandHandler.registerCommand(new VoidGiveCommand());
        commandHandler.registerCommand(new VoidHelpCommand());
    }

    public void registerListeners()
    {
        Bukkit.getPluginManager().registerEvents(new ChestPlaceHandler(), this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!cmd.getName().equalsIgnoreCase("void"))
            return true;

        String unknownCommand = prefix + ChatColor.RED + "Unknown command. Type " + ChatColor.YELLOW + "/void help " + ChatColor.RED + "for a list of commands.";

        if(args.length == 0)
        {
            sender.sendMessage(unknownCommand);
            return true;
        }

        IVoidCommand command = commandHandler.findCommand(args[0]);

        if(command == null)
        {
            sender.sendMessage(unknownCommand);
            return true;
        }

        try {
            command.execute(sender, cmd, label, args, this);
        } catch(UnknownCommandException e)
        {
            sender.sendMessage(unknownCommand);
        } catch (NotEnoughArgumentsException e)
        {
            sender.sendMessage(prefix + ChatColor.RED + "Not enough arguments. Usage: " + command.getUsage());
        } catch(NoPermissionsException e)
        {
            sender.sendMessage(prefix + ChatColor.RED + "No permission.");
        }

        return true;
    }

}
