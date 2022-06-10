package org.equinoxprojects.voidchest.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.equinoxprojects.voidchest.VoidChestPlugin;
import org.equinoxprojects.voidchest.chest.VoidChest;
import org.equinoxprojects.voidchest.commands.VoidCommand;
import org.equinoxprojects.voidchest.commands.exceptions.NoPermissionsException;
import org.equinoxprojects.voidchest.commands.exceptions.NotEnoughArgumentsException;
import org.equinoxprojects.voidchest.commands.exceptions.UnknownCommandException;
import org.equinoxprojects.voidchest.util.Permissions;
import org.equinoxprojects.voidchest.util.Utils;

public class VoidGiveCommand extends VoidCommand
{

    public VoidGiveCommand() { super("give"); }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args, JavaPlugin mainclass) throws UnknownCommandException, NotEnoughArgumentsException, NoPermissionsException
    {
        if(!sender.hasPermission(Permissions.GIVE_CHEST.getPermission())) { throw new NoPermissionsException(); }

        if(args.length < 2) { throw new NotEnoughArgumentsException(); }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null)
        {
            sender.sendMessage(Utils.colorize(VoidChestPlugin.getPrefix() + "&cPlayer &8" + args[1] + " &cdoes not exist!"));
            return;
        }

        int amount = 1;
        if(args.length >= 3)
        {
            try {
                amount = Integer.parseInt(args[2]);
            } catch(NumberFormatException ignored)
            {
                sender.sendMessage(VoidChestPlugin.getPrefix() + Utils.colorize("&cThat is not a valid number! Please try again"));
                return;
            }
        }

        ItemStack voidChest = VoidChest.getDefaultItem(target);

        target.getInventory().addItem(voidChest);
        target.sendMessage(Utils.colorize(VoidChestPlugin.getPrefix() + "&aYou have been given &e" + amount + " " + voidChest.getItemMeta().getDisplayName()));
        sender.sendMessage(Utils.colorize(VoidChestPlugin.getPrefix() + "&aYou have given &e" + amount + " " +
                            voidChest.getItemMeta().getDisplayName()) + " &r&ato &e" + target.getName());
    }

    @Override
    public String getUsage()
    {
        return "/void give <player> [amount]";
    }
}
