package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.AnvilInventory;
import xyz.devshah.minecraftbutharder.MinecraftButHarder;

import java.util.HashMap;

public class ExpConcession implements Listener {
    MinecraftButHarder plugin;
    public ExpConcession(MinecraftButHarder plugin) { this.plugin = plugin; }

    private final HashMap<String, Integer> levels = new HashMap<>();

    @EventHandler
    public void onPlayerEnchantItem(EnchantItemEvent event) {
        // Don't ask me how, but this logic works flawlessly.
        event.getEnchanter().setLevel(event.getEnchanter().getLevel() + event.whichButton() + 1);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() instanceof AnvilInventory) {
            Player player = (Player) event.getPlayer();

            if (levels.containsKey(player.getDisplayName())) {
                player.setLevel(levels.get(player.getDisplayName()));
                levels.remove(player.getDisplayName());
            }
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() instanceof AnvilInventory) {
            Player player = (Player) event.getWhoClicked();
            if (!(levels.containsKey(player.getDisplayName()))) {
                int level = player.getLevel();

                if (level % 5 == 0) {
                    // Else players might keep getting free rewards everytime they use anvil
                    level += 1;
                }
                levels.put(player.getDisplayName(), level);
            }

            if (event.getRawSlot() != 2) {
                player.setLevel(201);
            }

            if (event.getRawSlot() == 2) {
                int og_level = levels.get(player.getDisplayName());
                levels.remove(player);

                Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {player.setLevel(og_level);}, 1);

            }
        }
    }

}
