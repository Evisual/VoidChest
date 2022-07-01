package org.equinoxprojects.voidchest.chest;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.equinoxprojects.voidchest.config.FileManager;
import org.equinoxprojects.voidchest.util.NBTEditor;
import org.equinoxprojects.voidchest.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoidChest
{
    private final @Getter Location loc;
    private @Getter int moneyMade = 0;
    private @Getter int itemsSold = 0;
    private @Getter int itemsPurged = 0;
    private final UUID owner;

    public void setMoneyMade(int amount)
    {
        moneyMade = amount;
        update();
    }

    public void setItemsSold(int amount)
    {
        itemsSold = amount;
        update();
    }

    public void setItemsPurged(int amount)
    {
        itemsPurged = amount;
        update();
    }

    public VoidChest(final Location loc, final UUID owner)
    {
        this.loc = loc;
        this.owner = owner;

        if(loc == null || owner == null) return;

        update();
    }

    private void update()
    {
        FileConfiguration config = FileManager.getInstance().getChestsConfig().getConfig();

        int chestsNum = config.getInt("chests." + owner.toString() + "numofchests");
        chestsNum++;
        config.set("chests." + owner + ".numofchests", chestsNum);

        String basePath = "chests." + owner + "." + chestsNum;

        config.set(basePath + ".location.x", loc.getBlockX());
        config.set(basePath + ".location.y", loc.getBlockX());
        config.set(basePath + ".location.z", loc.getBlockX());
        config.set(basePath + ".location.world", loc.getWorld().getName());

        config.set(basePath + "." + "moneymade", 0);
        config.set(basePath + "." + "itemssold", 0);
        config.set(basePath + "." + "itemspurged", 0);

        FileManager.getInstance().getChestsConfig().save();
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

        item = NBTEditor.set(item, "true", "voidchest");

        return item;
    }

    public ItemStack getItem()
    {
        return getItem(null, false);
    }

    public static ItemStack getDefaultItem(Player p)
    {
        VoidChest chest = new VoidChest(null, null);
        return chest.getItem(p, true);
    }

    public static ItemStack getDefaultItem()
    {
        VoidChest chest = new VoidChest(null, null);
        return chest.getItem();
    }

    public static VoidChest getChest(Location loc)
    {
        FileConfiguration config = FileManager.getInstance().getChestsConfig().getConfig();

        for(String owner : config.getConfigurationSection("chests").getKeys(false))
        {
            for(String str : config.getConfigurationSection("chests." + owner).getKeys(false))
            {
                final String basePath = "chests." + owner + "." + str;
                if(config.getString(".location.world").equals(loc.getWorld().getName())) continue;
                if(config.getInt(basePath + ".location.x") != loc.getBlockX()) continue;
                if(config.getInt(basePath + ".location.y") != loc.getBlockY()) continue;
                if(config.getInt(basePath + ".location.z") != loc.getBlockZ()) continue;

                VoidChest chest = new VoidChest(new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()), UUID.fromString(owner));
                chest.setMoneyMade(config.getInt(basePath + ".moneymade"));
                chest.setItemsSold(config.getInt(basePath + ".itemssold"));
                chest.setItemsPurged(config.getInt(basePath + ".itemspurged"));

                return chest;
            }

        }

        return null;
    }

}
