package com.hypernite.mc.hnmc.core.misc.permission;

import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * 權限 API
 */
public class Perm {
    public static final String ADMIN = "hypernite.admin";
    public static final String HELPER = "hypernite.helper";
    public static final String MOD = "hypernite.mod";
    public static final String BUILDER = "hypernite.builder";
    public static final String DONOR = "hypernite.donor";
    public static final String OWNER = "*";

    public static boolean hasPermission(CommandSender sender, String permission) {
        boolean reuslt = false;
        if (permission == null) return true;
        for (String Pnode : getPermHierarchy(permission)) {
            reuslt = reuslt || sender.hasPermission(Pnode);
        }
        return reuslt;
    }

    public static List<String> getPermHierarchy(@Nonnull String string) {
        String[] node = string.split("\\.");
        List<String> permissionNode = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < node.length; i++) {
            stringBuilder.append(node[i]);
            permissionNode.add(stringBuilder.toString());
            if (i != node.length - 1) stringBuilder.append(".");
        }
        return permissionNode;
    }
}
