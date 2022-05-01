package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import xyz.devshah.minecraftbutharder.MinecraftButHarder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graves implements Listener {
    MinecraftButHarder plugin;
    public Graves(MinecraftButHarder plugin) { this.plugin = plugin; }

    private final HashMap<String, Integer> levels = new HashMap<>();


    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            event.setDroppedExp(0);
            int level = player.getLevel();

            if (level % 5 == 0) {
                // Else players might keep getting free rewards everytime they respawn
                level += 1;
            }

            levels.put(player.getDisplayName(), level);

            if (event.getDrops().size() > 0) {
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();

                player.sendMessage("§aYour grave is at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));
                plugin.getLogger().info("§a" + player.getDisplayName() + "'s grave is at " + String.format("%.0f", x) + "/" + String.format("%.0f", y) + "/" + String.format("%.0f", z));

                Block deathBlock = player.getWorld().getBlockAt(player.getLocation());
                if (deathBlock.getType() != Material.AIR) deathBlock.breakNaturally();
                deathBlock.setType(Material.CHEST);
                Chest left = (Chest) deathBlock.getState();

                Block deathBlock2 = player.getWorld().getBlockAt(player.getLocation().add(1, 0, 0));
                if (deathBlock2.getType() != Material.AIR) deathBlock2.breakNaturally();

                deathBlock2.setType(Material.CHEST);
                Chest right = (Chest) deathBlock2.getState();

                List<ItemStack> items = new ArrayList<>(event.getDrops());
                event.getDrops().clear();

                int i = 0;

                for (ItemStack item : items) {
                    if (i <= 26) left.getInventory().setItem(i, item);
                    else right.getInventory().setItem(i - 27, item);
                    if (i != items.size()) i++;
                }
            }

        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        player.setLevel(2);
        if (levels.containsKey(player.getDisplayName())) {
            int level = levels.get(player.getDisplayName());
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {player.setLevel(level);}, 2);
            levels.remove(player.getDisplayName());
        }
    }
}
