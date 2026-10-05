package xyz.devshah.minecraftbutharder.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("givecustom")) {
            if (args.length == 1) {
                List<String> items = new ArrayList<>();
                items.add("oldSafetyHelmet");
                items.add("ancientVenomSword");
                items.add("forbiddenImperviousChestplate");
                items.add("asgardAxe");
                items.add("forbiddenImperviousLeggings");
                items.add("etherPickaxe");
                items.add("forbiddenImperviousHelmet");
                items.add("excalibur");
                items.add("forbiddenImperviousBoots");
                items.add("claudiusAegis");
                items.add("servantShovel");
                items.add("minerFriend");
                items.add("devilStick");
                items.add("devilHelmet");
                items.add("devilChestplate");
                items.add("devilLeggings");
                items.add("devilBoots");
                return StringUtil.copyPartialMatches(args[0], items, new ArrayList<>());
            }
        }
        return null;
    }
}
