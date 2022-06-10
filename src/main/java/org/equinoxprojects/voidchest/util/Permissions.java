package org.equinoxprojects.voidchest.util;

import lombok.Getter;

public enum Permissions
{
    GIVE_CHEST("void.command.void.give");

    private final @Getter String permission;

    Permissions(final String permission)
    {
        this.permission = permission;
    }


}
