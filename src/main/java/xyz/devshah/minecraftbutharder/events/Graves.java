package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class Graves implements Listener {
    private final HashMap<String, Integer> levels = new HashMap<>();

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            player.sendMessage("§aYour grave is at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));
            System.out.println("§a" + player.getDisplayName() + "'s grave is at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));

            Block deathBlock = player.getWorld().getBlockAt(player.getLocation());
            if (deathBlock.getType() != Material.AIR) {
                deathBlock.breakNaturally();
            }
            deathBlock.setType(Material.CHEST);
            Chest left = (Chest) deathBlock.getState();
            left.setCustomName(player.getDisplayName() + "'s grave 1");

            Block deathBlock2 = player.getWorld().getBlockAt(player.getLocation().add(1, 0, 0));
            if (deathBlock2.getType() != Material.AIR) {
                deathBlock2.breakNaturally();
            }
            deathBlock2.setType(Material.CHEST);
            Chest right = (Chest) deathBlock2.getState();
            right.setCustomName(player.getDisplayName() + "'s grave 2");


            List<ItemStack> items = new ArrayList<>();
            for (ItemStack thing : event.getDrops()) items.add(thing);
            event.getDrops().clear();

            int i = 0;

            for (ItemStack item : items) {
                if (i <= 26) {
                    left.getInventory().setItem(i, item);
                }
                else if (i > 26) {
                    right.getInventory().setItem(i-27, item);
                }
                if (i != items.size()) i++;
            }

            event.setDroppedExp(0);
            levels.put(player.getDisplayName(), player.getLevel());

        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (levels.containsKey(player.getDisplayName())) {
            player.setLevel(levels.get(player.getDisplayName()));
            levels.remove(player.getDisplayName());
        }
    }
}
