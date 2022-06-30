package org.equinoxprojects.voidchest.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.equinoxprojects.voidchest.VoidChestPlugin;
import org.equinoxprojects.voidchest.commands.VoidCommand;
import org.equinoxprojects.voidchest.commands.exceptions.NoPermissionsException;
import org.equinoxprojects.voidchest.commands.exceptions.NotEnoughArgumentsException;
import org.equinoxprojects.voidchest.commands.exceptions.UnknownCommandException;
import org.equinoxprojects.voidchest.util.Utils;

public class VoidHelpCommand extends VoidCommand {

    public VoidHelpCommand()
    {
        super("help");
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args, JavaPlugin mainclass) throws UnknownCommandException, NotEnoughArgumentsException, NoPermissionsException
    {
        sender.sendMessage(VoidChestPlugin.getPrefix() + Utils.colorize("&c&m---------------------------------"));
        sender.sendMessage(VoidChestPlugin.getPrefix() + Utils.colorize("&c/void help &l- &8Displays this message"));
        sender.sendMessage(VoidChestPlugin.getPrefix() + Utils.colorize("&c/void give <player> [amount] &l- &8Give a player void chests"));
        sender.sendMessage(VoidChestPlugin.getPrefix() + Utils.colorize("&c&m---------------------------------"));
    }

    @Override
    public String getUsage() {
        return "/void help";
    }
}
