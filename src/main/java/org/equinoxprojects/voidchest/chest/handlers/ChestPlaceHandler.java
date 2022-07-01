package org.equinoxprojects.voidchest.chest.handlers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.equinoxprojects.voidchest.VoidChestPlugin;
import org.equinoxprojects.voidchest.chest.VoidChest;
import org.equinoxprojects.voidchest.util.NBTEditor;

import java.util.Objects;

public class ChestPlaceHandler implements Listener
{

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();

        if(e.isCancelled()) return;

        if(!Objects.equals(NBTEditor.getString(item, "voidchest"), "true")) return;

        Location loc = e.getBlockPlaced().getLocation();
        VoidChest chest = new VoidChest(loc, p.getUniqueId());

        p.sendMessage(VoidChestPlugin.getPrefix() + ChatColor.GREEN + "Successfully placed your void chest!");

        //TODO: Check if there's another chest in the chunk before placing
    }

}
