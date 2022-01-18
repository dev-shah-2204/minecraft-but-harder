package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/* https://github.com/Arnuh/ArmorEquipEvent
 *https://www.spigotmc.org/resources/lib-armorequipevent.5478/
 */
import com.codingforcookies.armorequip.ArmorEquipEvent;
import xyz.devshah.minecraftbutharder.MinecraftButHarder;

import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;


public class CustomArmorEnchants implements Listener {
    MinecraftButHarder plugin;
    public CustomArmorEnchants(MinecraftButHarder plugin) { this.plugin = plugin; }

    private void addEffect(Player player, PotionEffectType type, Integer amplifer) {
        player.addPotionEffect(new PotionEffect(type, Integer.MAX_VALUE, amplifer, false, false, true));
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        ItemStack newArmor = event.getNewArmorPiece();
        ItemStack oldArmor = event.getOldArmorPiece();
        Player player = event.getPlayer();

        if (newArmor != null && newArmor.getItemMeta() != null && newArmor.getItemMeta().getLore() != null) {
            List<String> lore = newArmor.getItemMeta().getLore();

            if (lore.contains("§6Respiration")) { addEffect(player, PotionEffectType.WATER_BREATHING, 0);}
            if (lore.contains("§6Night Vision")) { addEffect(player, PotionEffectType.NIGHT_VISION, 0); }
            if (lore.contains("§6Speed")) { addEffect(player, PotionEffectType.SPEED, 1); }
            if (lore.contains("§6Saturation")) { addEffect(player, PotionEffectType.SATURATION, 1); }
            if (lore.contains("§6Fire Resistance")) { addEffect(player, PotionEffectType.FIRE_RESISTANCE, 0); }
            if (lore.contains("§6Jump Boost")) { addEffect(player, PotionEffectType.JUMP, 0); }
            if (lore.contains("§6Dolphin's Grace")) { addEffect(player, PotionEffectType.DOLPHINS_GRACE, 0); }
            if (lore.contains("§6Absorbtion")) { addEffect(player, PotionEffectType.ABSORPTION, 1); }
            if (lore.contains("§6Health Boost")) { addEffect(player, PotionEffectType.HEALTH_BOOST, 2); }
        }

        if (oldArmor != null && oldArmor.getItemMeta() != null && oldArmor.getItemMeta().getLore() != null) {
            List<String> lore = oldArmor.getItemMeta().getLore();

            if (lore.contains("§6Night Vision")) { player.removePotionEffect(PotionEffectType.NIGHT_VISION); }
            if (lore.contains("§6Respiration")) { player.removePotionEffect(PotionEffectType.WATER_BREATHING); }
            if  (lore.contains("§6Speed")) { player.removePotionEffect(PotionEffectType.SPEED); }
            if (lore.contains("§6Saturation")) { player.removePotionEffect(PotionEffectType.SATURATION); }
            if (lore.contains("§6Fire Resistance")) { player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE); }
            if (lore.contains("§6Jump Boost")) { player.removePotionEffect(PotionEffectType.JUMP); }
            if (lore.contains("§6Dolphin's Grace")) { player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE); }
            if (lore.contains("§6Health Boost")) { player.removePotionEffect(PotionEffectType.HEALTH_BOOST); }
            if (lore.contains("§6Absorbtion")) { player.removePotionEffect(PotionEffectType.ABSORPTION); }
        }

    }


    @EventHandler
    public void onEntityDamagePlayer(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if (damaged instanceof Player) {
            Player player = (Player) damaged;
            ItemStack helmet = player.getInventory().getHelmet();
            ItemStack chestplate = player.getInventory().getChestplate();
            ItemStack leggings = player.getInventory().getLeggings();
            ItemStack boots = player.getInventory().getBoots();

            // Helmet Enchants
            if (helmet != null && helmet.getItemMeta() != null && helmet.getItemMeta().getLore() != null) {
                // Tracker
                if (helmet.getItemMeta().getLore().contains("§6Tracker") && damager instanceof LivingEntity) {
                    ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 3*20, 0));
                }
            }


            // Chestplate Enchants
            if (chestplate != null && chestplate.getItemMeta() != null && chestplate.getItemMeta().getLore() != null) {

                // Aura of Pain
                if (chestplate.getItemMeta().getLore().contains("§6Aura of Pain")) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    if (r <= 0.20) {
                        event.setCancelled(true);
                        player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
                        player.sendMessage("§aYour armor blocked the attack!");
                    }
                }

                // Iron Army
                if (chestplate.getItemMeta().getLore().contains("§6Iron Army") && damager instanceof LivingEntity) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    if (r <= 0.10) {
                        Entity golem = damager.getWorld().spawnEntity(damager.getLocation(), EntityType.IRON_GOLEM);
                        golem.setCustomName(player.getDisplayName()+"'s Iron Army");

                        ((IronGolem) golem).setTarget((LivingEntity) damager);
                        ((IronGolem) golem).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false));

                        Bukkit.getServer().getScheduler().runTaskLater(plugin, golem::remove, 30*20);

                    }
                }

                // Devil's Invitation
                if (chestplate.getItemMeta().getLore().contains("§6Devil's Invitation")) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    if (r <= 0.30 && damager instanceof LivingEntity) {
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5*20, 1));
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3*20, 0));
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5*20, 0));
                        damager.teleport(damaged.getLocation().add(2, 0, 2));
                    }

                    if (r <= 0.30 && damager instanceof Arrow) {
                        if (((Arrow) damager).getShooter() instanceof LivingEntity) {
                            LivingEntity shooter = (LivingEntity) ((Arrow) damager).getShooter();
                            shooter.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5*20, 1));
                            shooter.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3*20, 0));
                            shooter.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5*20, 0));
                            shooter.teleport(damaged.getLocation().add(2, 0, 2));
                        }
                    }

                }

            }


            // Leggings enchants
            if (leggings != null && leggings.getItemMeta() != null && leggings.getItemMeta().getLore() != null) {

                // Leap of Faith
                if (leggings.getItemMeta().getLore().contains("§6Leap of Faith") && ((Player) damaged).getHealth() <= 6) {
                    Vector velocity = new Vector(0, 2.5, 0);
                    damaged.setVelocity(velocity);
                    ((Player) damaged).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 4*20, 1, false, false, true));
                    damaged.sendMessage("§aYou took the leap of faith!");

                }
            }


            // Boots enchants
            if (boots != null && boots.getItemMeta() != null && boots.getItemMeta().getLore() != null) {

                // Beastly Aura
                if (boots.getItemMeta().getLore().contains("§6Beastly Aura") && damager instanceof LivingEntity) {
                    Random random = new Random();
                    double r = random.nextDouble();

                    if (r <= 0.15) {
                        Entity wolf = damager.getWorld().spawnEntity(damager.getLocation(), EntityType.WOLF);
                        wolf.setCustomName("§c"+player.getDisplayName()+"'s SPIRIT");
                        ((Wolf) wolf).setTarget((LivingEntity) damager);

                        Bukkit.getServer().getScheduler().runTaskLater(plugin, wolf::remove, 30*20);
                    }
                }

                // Undead Army
                if (boots.getItemMeta().getLore().contains("§6Undead Army") && damager instanceof LivingEntity) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    if (r <= 0.17) {
                        Entity zombie = damager.getWorld().spawnEntity(damager.getLocation(), EntityType.ZOMBIE);
                        ((Zombie) zombie).setTarget((LivingEntity) damager);
                        zombie.setCustomName("§4"+ player.getDisplayName() +"'s Raised Undead");
                        Bukkit.getServer().getScheduler().runTaskLater(plugin, zombie::remove, 20*20);
                    }
                }
            }

        }

    }
}