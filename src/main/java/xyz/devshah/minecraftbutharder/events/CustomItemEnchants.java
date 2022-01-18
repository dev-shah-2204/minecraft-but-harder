package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import xyz.devshah.minecraftbutharder.MinecraftButHarder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomItemEnchants implements Listener {
    MinecraftButHarder plugin;
    public CustomItemEnchants(MinecraftButHarder plugin) { this.plugin = plugin; }
    private final ArrayList<String> cooldown = new ArrayList<>();

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if (damager instanceof LivingEntity) {
            ItemStack item = ((LivingEntity) damager).getEquipment().getItemInMainHand();

            if (item.getItemMeta() != null && item.getItemMeta().getLore() != null && damaged instanceof LivingEntity) {
                List<String> lore = item.getItemMeta().getLore();
                Random random = new Random();
                double r = random.nextDouble();

                // Nausea
                if (lore.contains("§6Nausea")) {
                    ((LivingEntity) damaged).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10*20, 4, false, false));
                }

                // Venom
                if (lore.contains("§6Venom")) {
                    ((LivingEntity) damaged).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 7*20, 2, false, false));
                }

                // Bubonic Plague
                if (lore.contains("§Bubonic Plague")) {
                    if (r <= 0.7) {
                        ((LivingEntity) damaged).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 7*20, 0, false, false));
                    }
                }

                // Whirlwind
                if (lore.contains("§6Whirlwind")) {
                    if (r <= 0.35) {
                        Vector velocity = new Vector(0, 3, 0);
                        damaged.setVelocity(velocity);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getItem() != null && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().getLore() != null) {
            Player player = event.getPlayer();
            if (event.getItem().getItemMeta().getLore().contains("§6Wrath of God")) {
                if (cooldown.contains(player.getDisplayName())) {
                    player.sendMessage("§cYou're still under the 10 second cooldown");
                    return;
                }

                Block block = player.getTargetBlockExact(512);
                if (block != null) {
                    player.getWorld().strikeLightning(block.getLocation());
                    cooldown.add(player.getDisplayName());
                    Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> cooldown.remove(player.getDisplayName()), 10 * 20);
                }
            }
        }
    }

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getItemMeta() != null && item.getItemMeta().getLore() != null) {
            if (item.getItemMeta().getLore().contains("§6Ether Surge")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 3*20, 2, false, false));
            }

            if (item.getItemMeta().getLore().contains("§6Blast Mining")) {
                Location loc = event.getBlock().getLocation();
                player.getWorld().createExplosion(loc.add(1, 0, 1), 1);

            }
        }

    }

}
