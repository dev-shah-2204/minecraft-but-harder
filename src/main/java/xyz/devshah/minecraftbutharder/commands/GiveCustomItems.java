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
        if (sender instanceof Player) {
            if (!(sender.isOp())) {
                sender.sendMessage("§cOnly server operators can use that command");
                return true;
            }
        }
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
                else {
                    player = (Player) sender;
                }
            }

            if (args.length == 2) {
                player = Bukkit.getServer().getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage("Player not found");
                    return true;
                }
            }

            // Armor
            if (args[0].equalsIgnoreCase("oldSafetyHelmet")) {
                giveItem(player, Armor.oldSafetyHelmet);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("forbiddenImperviousChestplate")) {
                giveItem(player, Armor.forbiddenImperviousChestplate);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("forbiddenImperviousLeggings")) {
                giveItem(player, Armor.forbiddenImperviousLeggings);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("forbiddenImperviousHelmet")) {
                giveItem(player, Armor.forbiddenImperviousHelmet);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("forbiddenImperviousBoots")) {
                giveItem(player, Armor.forbiddenImperviousBoots);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("devilHelmet")) {
                giveItem(player, Armor.beelzebubHelmet);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("devilChestplate")) {
                giveItem(player, Armor.asmodiusChestplate);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("devilLeggings")) {
                giveItem(player, Armor.mammonLeggings);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("devilBoots")) {
                giveItem(player, Armor.belphegorBoots);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }

            // Weapons
            else if (args[0].equalsIgnoreCase("ancientVenomSword")) {
                giveItem(player, Weapons.venomBlade);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("asgardAxe")) {
                giveItem(player, Weapons.asgardAxe);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("excalibur")) {
                giveItem(player, Weapons.excalibur);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("claudiusAegis")) {
                giveItem(player, Weapons.claudiusAegis);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("devilStick")) {
                giveItem(player, Weapons.devilStick);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }

            // Tools
            else if (args[0].equalsIgnoreCase("etherPickaxe")) {
                giveItem(player, Items.etherealPickaxe);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("servantShovel")) {
                giveItem(player, Items.servantShovel);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
                return true;
            }
            else if (args[0].equalsIgnoreCase("minerFriend")) {
                giveItem(player, Items.minerFriend);
                sender.sendMessage("Gave 1 [" + args[0]+ "] to " + player.getDisplayName());
            }

            else {
                sender.sendMessage("§cUnknown item '" + args[0] + "'");
            }
            return true;


        }

        return true;
    }

}
