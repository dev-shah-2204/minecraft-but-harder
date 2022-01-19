package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Graves implements Listener {
    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDrops().size() == 0) {
                return;
            }

            Player player = (Player) event.getEntity();

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            player.sendMessage("§aYour grave is at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));

            Block deathBlock = player.getWorld().getBlockAt(player.getLocation());
            deathBlock.setType(Material.CHEST);
            Chest left = (Chest) deathBlock.getState();
            left.setCustomName(player.getDisplayName() + "'s grave 1");

            Block deathBlock2 = player.getWorld().getBlockAt(player.getLocation().add(1, 0, 0));
            deathBlock2.setType(Material.CHEST);
            Chest right = (Chest) deathBlock2.getState();
            right.setCustomName(player.getDisplayName() + "'s grave 2");


            List<ItemStack> items = new ArrayList<>();
            for (ItemStack thing : event.getDrops()) items.add(thing);
            event.getDrops().clear();

            int i = 0;
            System.out.println(items.size());

            for (ItemStack item : items) {
                if (i <= 26) {
                    left.getInventory().setItem(i, item);
                }
                else if (i > 26) {
                    right.getInventory().setItem(i-27, item);
                }
                if (i != items.size()) i++;
            }

        }
    }
}