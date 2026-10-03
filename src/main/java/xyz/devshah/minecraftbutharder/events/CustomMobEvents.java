package xyz.devshah.minecraftbutharder.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class CustomMobEvents implements Listener {

    @EventHandler
    public void onTargetChange(EntityTargetLivingEntityEvent event) {
        if (event.getEntity().getCustomName() != null) {
            if (event.getTarget() instanceof Player) {

                // Iron Army
                if (event.getEntity().getCustomName().contains("Iron Army")) {
                    if (event.getEntity().getCustomName().contains(((Player) event.getTarget()).getDisplayName())) {
                        event.setCancelled(true);
                    }
                }

                // Raised Undead
                if (event.getEntity().getCustomName().contains("Raised Undead")) {
                    if (event.getEntity().getCustomName().contains(((Player) event.getTarget()).getDisplayName())) {
                        event.setCancelled(true);
                    }
                }

                // Beastly Aura
                if (event.getEntity().getCustomName().contains("SPIRIT")) {
                    if (event.getEntity().getCustomName().contains(((Player) event.getTarget()).getDisplayName())) {
                        event.setCancelled(true);
                    }
                }

            }
        }
    }

}
