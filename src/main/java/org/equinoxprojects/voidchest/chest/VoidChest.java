package org.equinoxprojects.voidchest.chest;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.equinoxprojects.voidchest.config.FileManager;
import org.equinoxprojects.voidchest.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class VoidChest
{
    private final Location loc;
    private int moneyMade = 0;
    private int itemsSold = 0;
    private int itemsPurged = 0;

    public VoidChest(Location loc)
    {
        this.loc = loc;
    }

    public ItemStack getItem(Player p, boolean usePlayer)
    {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&l&k;;&c&l Void Chest &4&l&k;;"));

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oA chest that when placed down collects"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7&oand stores all items..."));
        lore.add(" ");
        lore.add(Utils.colorize("&c&l┃ &cTotal Profits: &a$" + Utils.format(moneyMade)));
        lore.add(Utils.colorize("&c&l┃ &cTotal Items Sold: &a" + Utils.format(itemsSold)));
        lore.add(Utils.colorize("&c&l┃ &cTotal Items Purged: &a" + Utils.format(itemsPurged)));

        if(usePlayer)
        {
            lore.add(" ");
            lore.add(Utils.colorize("&c&l┃ &cOwned By: &8Evsual"));
        }

        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

        return item;
    }

    public ItemStack getItem()
    {
        return getItem(null, false);
    }

    public static ItemStack getDefaultItem(Player p)
    {
        VoidChest chest = new VoidChest(null);
        return chest.getItem(p, true);
    }

    public static ItemStack getDefaultItem()
    {
        VoidChest chest = new VoidChest(null);
        return chest.getItem();
    }

}
