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
        // Enchanting costs 0.3 levels per vanilla level (0.3, 0.6, 0.9 for the 3 buttons).
        // Keep this the only EnchantItemEvent handler, or the levels get changed twice.
        Player player = event.getEnchanter();
        int vanillaCost = event.whichButton() + 1;

        double after = Math.max(0, player.getLevel() + player.getExp() - 0.3 * vanillaCost);
        int afterLevel = (int) after;

        // Vanilla takes vanillaCost levels off after this event, so add it back in advance
        player.setLevel(afterLevel + vanillaCost);
        player.setExp((float) (after - afterLevel));
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
                // ExpRewards only rewards new highest levels, so restoring the original level is safe
                levels.put(player.getDisplayName(), player.getLevel());
            }

            if (event.getRawSlot() != 2) {
                player.setLevel(201);
            }

            if (event.getRawSlot() == 2) {
                int og_level = levels.get(player.getDisplayName());
                levels.remove(player.getDisplayName());

                Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {player.setLevel(og_level);}, 1);

            }
        }
    }

}
