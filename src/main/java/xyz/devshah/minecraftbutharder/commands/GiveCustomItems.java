package xyz.devshah.minecraftbutharder.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GiveCustomItems implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args == null) {
            System.out.println("null");;
        }
        if (args.length == 1) {
            System.out.println("1");
        }

        if (args.length == 2) {
            System.out.println("2");
        }
        return true;
    }

}
