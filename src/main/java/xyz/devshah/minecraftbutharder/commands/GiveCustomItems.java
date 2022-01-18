package xyz.devshah.minecraftbutharder.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.devshah.minecraftbutharder.items.Armor;
import xyz.devshah.minecraftbutharder.items.Items;
import xyz.devshah.minecraftbutharder.items.Weapons;

public class GiveCustomItems implements CommandExecutor {
    private void giveItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        } else {
            player.getInventory().addItem(item);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("givecustom")){
            if (args.length == 0) {
                sender.sendMessage("Command incomplete. Correct usage is /givecustom <item> [player]");
                return true;
            }

            Player player = null;

            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cPlease provide a player name");
                    return true;
                }
                player = (Player) sender;
            }

            if (args.length == 2) {
                player = Bukkit.getServer().getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage("Player not found");
                    return true;
                }
            }

            // Armor
            if (args[0].equalsIgnoreCase("oldSafetyHelmet")) giveItem(player, Armor.oldSafetyHelmet);
            if (args[0].equalsIgnoreCase("imperviousChestplate")) giveItem(player, Armor.forbiddenImperviousChestplate);
            if (args[0].equalsIgnoreCase("imperviousLeggings")) giveItem(player, Armor.forbiddenImperviousLeggings);
            if (args[0].equalsIgnoreCase("imperviousHelmet")) giveItem(player, Armor.forbiddenImperviousHelmet);
            if (args[0].equalsIgnoreCase("imperviousBoots")) giveItem(player, Armor.forbiddenImperviousBoots);
            if (args[0].equalsIgnoreCase("devilHelmet")) giveItem(player, Armor.beelzebubHelmet);
            if (args[0].equalsIgnoreCase("devilChestplate")) giveItem(player, Armor.asmodiusChestplate);
            if (args[0].equalsIgnoreCase("devilLeggings")) giveItem(player, Armor.mammonLeggings);
            if (args[0].equalsIgnoreCase("devilBoots")) giveItem(player, Armor.belphegorBoots);

            // Weapons
            if (args[0].equalsIgnoreCase("ancientVenomSword")) giveItem(player, Weapons.venomBlade);
            if (args[0].equalsIgnoreCase("asgardAxe")) giveItem(player, Weapons.asgardAxe);
            if (args[0].equalsIgnoreCase("excalibur")) giveItem(player, Weapons.excalibur);
            if (args[0].equalsIgnoreCase("claudiusAegis")) giveItem(player, Weapons.claudiusAegis);
            if (args[0].equalsIgnoreCase("devilStick")) giveItem(player, Weapons.devilStick);

            // Tools
            if (args[0].equalsIgnoreCase("etherPickaxe")) giveItem(player, Items.etherealPickaxe);
            if (args[0].equalsIgnoreCase("servantShovel")) giveItem(player, Items.servantShovel);
            if (args[0].equalsIgnoreCase("minerFriend")) giveItem(player, Items.minerFriend);

            return true;
        }

        return true;
    }

}
