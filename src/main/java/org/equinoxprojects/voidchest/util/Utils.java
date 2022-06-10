package org.equinoxprojects.voidchest.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class Utils
{

    public static String colorize(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String format(int i)
    {
        DecimalFormat format = new DecimalFormat();
        return format.format(i);
    }

}
