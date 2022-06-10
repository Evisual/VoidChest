package org.equinoxprojects.voidchest.commands;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class AlternativeCommandHandler
{
    private final JavaPlugin mainclass;
    private ArrayList<IVoidCommand> voidCommands = new ArrayList<>();

    public AlternativeCommandHandler(final JavaPlugin mainclass)
    {
        this.mainclass = mainclass;
    }

    public void registerCommand(IVoidCommand command) { voidCommands.add(command); }

    public IVoidCommand findCommand(String name)
    {
        for(IVoidCommand cmd : voidCommands)
        {
            if(cmd.getName().equalsIgnoreCase(name))
                return cmd;
        }

        return null;
    }


}
