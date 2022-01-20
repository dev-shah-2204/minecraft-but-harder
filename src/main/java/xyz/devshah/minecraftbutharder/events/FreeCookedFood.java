package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FreeCookedFood implements Listener {

    private void dropItem(Entity entity, Material material, int amount) {
        entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(material, amount));
    }

    private boolean dropRare(double rarity) {
        Random random = new Random();
        double r = random.nextDouble();
        return r <= rarity;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (entity.getType() == EntityType.COW) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));

            dropItem(entity, Material.COOKED_BEEF, 2);
            if (dropRare(0.4)) { dropItem(entity, Material.LEATHER, 1); }
        }

        if (entity.getType() == EntityType.CHICKEN) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));

            dropItem(entity, Material.COOKED_CHICKEN, 1);
            if (dropRare(0.4)) { dropItem(entity, Material.FEATHER, 1); }
        }

        if (entity.getType() == EntityType.COD) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));

            dropItem(entity, Material.COOKED_COD, 1);
            if (dropRare(0.35)) {  dropItem(entity, Material.BONE_MEAL, 1); }
        }

        if (entity.getType() == EntityType.SALMON) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));

            dropItem(entity, Material.COOKED_SALMON, 1);
            if (dropRare(0.35)) { dropItem(entity, Material.BONE_MEAL, 1); }
        }

        if (entity.getType() == EntityType.MUSHROOM_COW) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));

            dropItem(entity, Material.COOKED_BEEF, 2);
            if (dropRare((float) 0.4)) { dropItem(entity, Material.LEATHER, 1); }
        }

        if (entity.getType() == EntityType.RABBIT) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));

            dropItem(entity, Material.COOKED_RABBIT, 1);
            if (dropRare((float) 0.45)) { dropItem(entity, Material.RABBIT_HIDE, 1); }
            if (dropRare((float) 0.15)) { dropItem(entity, Material.RABBIT_FOOT, 1); }
        }

        // Entity with regular drops
        if (entity.getType() == EntityType.PIG) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));
            dropItem(entity, Material.COOKED_PORKCHOP, 2);
        }

        if (entity.getType() == EntityType.FOX) {
            event.getDrops().clear();
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));
            dropItem(entity, Material.SWEET_BERRIES, 2);
        }

        if (entity.getType() == EntityType.SHEEP) {
            event.getDrops().remove(new ItemStack(Material.MUTTON, 1));
            event.getDrops().remove(new ItemStack(Material.MUTTON, 2));
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));
            dropItem(entity, Material.COOKED_MUTTON, 2);
        }

        if (entity.getType() == EntityType.TURTLE) {
            event.setDroppedExp((int) (event.getDroppedExp()/1.7));
            if (dropRare(0.05)) {  dropItem(entity, Material.TURTLE_HELMET,1); }
        }

        if (entity.getType() == EntityType.ENDERMAN) {
            if (!(entity.getWorld().getEnvironment().equals(World.Environment.THE_END))) {
                event.getDrops().clear();
            }
        }
    }

    // Events related to Exp
    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        if (event.getAmount() > 0) {
            // It should be a little easier to get exp points than it is.
            event.setAmount((int) (event.getAmount() * 1.7));
        }
    }

    @EventHandler
    public void onPlayerEnchantItem(EnchantItemEvent event) {
        // Don't ask me how, but this logic works flawlessly.
        event.getEnchanter().setLevel(event.getEnchanter().getLevel() + event.whichButton() + 1);
    }
}
