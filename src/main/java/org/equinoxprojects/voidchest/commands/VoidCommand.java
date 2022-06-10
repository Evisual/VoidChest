package org.equinoxprojects.voidchest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Map;

public abstract class VoidCommand implements IVoidCommand
{
    protected final String name;

    protected VoidCommand(final String name) { this.name = name; }

    @Override
    public String getName() { return name; }

    @Override
    public Map<String, String> getUsageStrings() { return null; }  //TODO: setup this
}
