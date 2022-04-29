package xyz.devshah.minecraftbutharder.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Basic implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Heal command
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (sender instanceof Player) {
                if (!(sender.isOp())) {
                    sender.sendMessage("§cYou don't have the permission to use that command");
                    return true;
                }
            }

            if (args.length > 0) {
                Player player = sender.getServer().getPlayer(args[0]);

                if (player == null) {
                    sender.sendMessage("§cPlayer not found");
                    return true;
                }
                player.setHealth(20);
                sender.sendMessage("§aHealed " + player.getDisplayName());
            }

            else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cPlease provide a player name");
                    return true;
                }
                else {
                    ((Player) sender).setHealth(20);
                    sender.sendMessage("§aSet you to max health");
                }
            }
        }

        // Feed Command
        else if (cmd.getName().equalsIgnoreCase("feed")) {
            if (sender instanceof Player) {
                if (!(sender.isOp())) {
                    sender.sendMessage("§cYou don't have the permission to use that command");
                    return true;
                }
            }

            if (args.length > 0) {
                Player player = sender.getServer().getPlayer(args[0]);

                if (player == null) {
                    sender.sendMessage("§cPlayer not found");
                    return true;
                }
                player.setFoodLevel(20);
                sender.sendMessage("§aSet " + player.getDisplayName() + " to Max Food level");
            }

            else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cPlease provide a player name");
                    return true;
                }
                else {
                    ((Player) sender).setFoodLevel(20);
                    sender.sendMessage("§aSet you to max food level");
                }
            }
        }

         // Cords command
        else if (cmd.getName().equalsIgnoreCase("cords")) {
            Player player;
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cPlease provide a player name");
                    return true;
                }
                else { player = (Player) sender; }

                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();

                player.chat("§2I'm at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));
            }

            else {
                if (sender instanceof Player) {
                    if (!(sender.isOp())) {
                        sender.sendMessage("§cYou don't have the permission to fetch other player's coordinates");
                        return true;
                    }
                }
                player = sender.getServer().getPlayer(args[0]);

                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();

                sender.sendMessage(player.getDisplayName() + " is at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));
            }
        }




        return true;
    }

}
