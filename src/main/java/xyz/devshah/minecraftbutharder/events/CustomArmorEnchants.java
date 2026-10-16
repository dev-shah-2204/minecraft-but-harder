package xyz.devshah.minecraftbutharder.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import xyz.devshah.minecraftbutharder.MinecraftButHarder;


public class CustomArmorEnchants implements Listener {
    MinecraftButHarder plugin;
    public CustomArmorEnchants(MinecraftButHarder plugin) { this.plugin = plugin; }

    private void addEffect(Player player, PotionEffectType type, Integer amplifer) {
        player.addPotionEffect(new PotionEffect(type, PotionEffect.INFINITE_DURATION, amplifer, false, false, true));
    }

    private void removeEffect(Player player, PotionEffectType type) {
        if (player.hasPotionEffect(type)) {
            PotionEffect effect = player.getPotionEffect(type);
            if (effect.isInfinite()) {
                player.removePotionEffect(type);
            }
        }
    }

    private List<String> getLore(ItemStack armor) {
        if (armor != null && armor.hasItemMeta() && armor.getItemMeta().hasLore()) {
            return armor.getItemMeta().getLore();
        }
        return new ArrayList<>();
    }

    public void applyArmorEffects() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            List<String> helmetLore = getLore(player.getInventory().getHelmet());
            List<String> chestplateLore = getLore(player.getInventory().getChestplate());
            List<String> leggingsLore = getLore(player.getInventory().getLeggings());
            List<String> bootsLore = getLore(player.getInventory().getBoots());

            // Helmet
            if (helmetLore.contains("§6Respiration")) { addEffect(player, PotionEffectType.WATER_BREATHING, 0); }
            else { removeEffect(player, PotionEffectType.WATER_BREATHING); }

            if (helmetLore.contains("§6Night Vision")) { addEffect(player, PotionEffectType.NIGHT_VISION, 0); }
            else { removeEffect(player, PotionEffectType.NIGHT_VISION); }

            // Chestplate
            if (chestplateLore.contains("§6Absorption")) {
                if (!(player.hasPotionEffect(PotionEffectType.ABSORPTION))) { addEffect(player, PotionEffectType.ABSORPTION, 1); }
            } else {
                removeEffect(player, PotionEffectType.ABSORPTION);
            }

            if (chestplateLore.contains("§6Health Boost")) {
                if (!(player.hasPotionEffect(PotionEffectType.HEALTH_BOOST))) { addEffect(player, PotionEffectType.HEALTH_BOOST, 2); }
            } else {
                removeEffect(player, PotionEffectType.HEALTH_BOOST);
            }

            // Leggings
            if (leggingsLore.contains("§6Saturation")) { addEffect(player, PotionEffectType.SATURATION, 1); }
            else { removeEffect(player, PotionEffectType.SATURATION); }

            if (leggingsLore.contains("§6Fire Resistance")) { addEffect(player, PotionEffectType.FIRE_RESISTANCE, 0); }
            else { removeEffect(player, PotionEffectType.FIRE_RESISTANCE); }

            // Boots
            if (bootsLore.contains("§6Speed")) { addEffect(player, PotionEffectType.SPEED, 1); }
            else { removeEffect(player, PotionEffectType.SPEED); }

            if (bootsLore.contains("§6Jump Boost")) { addEffect(player, PotionEffectType.JUMP_BOOST, 0); }
            else { removeEffect(player, PotionEffectType.JUMP_BOOST); }

            if (bootsLore.contains("§6Dolphin's Grace")) { addEffect(player, PotionEffectType.DOLPHINS_GRACE, 0); }
            else { removeEffect(player, PotionEffectType.DOLPHINS_GRACE); }
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

                if (helmet.getItemMeta().getLore().contains("§6Tracker")) {
                    if (damager instanceof Arrow && ((Arrow) damager).getShooter() instanceof LivingEntity) {
                        LivingEntity shooter = (LivingEntity) ((Arrow) damager).getShooter();
                        shooter.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 3*20, 0));
                    }
                    else if (damager instanceof LivingEntity) {
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 3 * 20, 0));
                    }
                }
            }


            // Chestplate Enchants
            if (chestplate != null && chestplate.getItemMeta() != null && chestplate.getItemMeta().getLore() != null) {

                // Aura of Pain
                if (chestplate.getItemMeta().getLore().contains("§6Aura of Pain")) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    if (r <= plugin.getConfig().getDouble("armor-enchants.aura-of-pain-chance")) {
                        event.setCancelled(true);
                        player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
                        player.sendMessage("§aYour armor blocked the attack!");
                    }
                }

                // Iron Army
                if (chestplate.getItemMeta().getLore().contains("§6Iron Army") && damager instanceof LivingEntity) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    if (r <= plugin.getConfig().getDouble("armor-enchants.iron-army-chance")) {
                        Entity golem = damager.getWorld().spawnEntity(damager.getLocation(), EntityType.IRON_GOLEM);
                        golem.setCustomName(player.getDisplayName()+"'s Iron Army");

                        ((IronGolem) golem).setTarget((LivingEntity) damager);
                        ((IronGolem) golem).addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, Integer.MAX_VALUE, 1, false, false));

                        Bukkit.getServer().getScheduler().runTaskLater(plugin, golem::remove, 30*20);
                    }
                }

                // Devil's Invitation
                if (chestplate.getItemMeta().getLore().contains("§6Devil's Invitation")) {
                    Random random = new Random();
                    double r = random.nextDouble();

                    if (r <= plugin.getConfig().getDouble("armor-enchants.devils-invitation-chance") && damager instanceof LivingEntity) {
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5*20, 1));
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3*20, 0));
                        ((LivingEntity) damager).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 5*20, 0));
                        damager.teleport(damaged.getLocation().add(2, 0, 2));
                    }

                    if (r <= plugin.getConfig().getDouble("armor-enchants.devils-invitation-chance") && damager instanceof Arrow) {
                        if (((Arrow) damager).getShooter() instanceof LivingEntity) {
                            LivingEntity shooter = (LivingEntity) ((Arrow) damager).getShooter();
                            shooter.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5*20, 1));
                            shooter.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 3*20, 0));
                            shooter.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 5*20, 0));
                            shooter.teleport(damaged.getLocation().add(2, 0, 2));
                        }
                    }
                }
            }


            // Leggings enchants
            if (leggings != null && leggings.getItemMeta() != null && leggings.getItemMeta().getLore() != null) {

                // Leap of Faith
                if (leggings.getItemMeta().getLore().contains("§6Leap of Faith") && ((Player) damaged).getHealth() <= (plugin.getConfig().getDouble("armor-enchants.leap-of-faith-hearts") * 2)) {
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

                    if (r <= plugin.getConfig().getDouble("armor-enchants.beastly-aura-chance")) {
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
                    if (r <= plugin.getConfig().getDouble("armor-enchants.undead-army-chance")) {
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