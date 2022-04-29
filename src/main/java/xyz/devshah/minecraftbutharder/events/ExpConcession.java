package xyz.devshah.minecraftbutharder.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class ExpConcession implements Listener {

    @EventHandler
    public void onPlayerEnchantItem(EnchantItemEvent event) {
        // Don't ask me how, but this logic works flawlessly.
        event.getEnchanter().setLevel(event.getEnchanter().getLevel() + event.whichButton() + 1);
    }

}
