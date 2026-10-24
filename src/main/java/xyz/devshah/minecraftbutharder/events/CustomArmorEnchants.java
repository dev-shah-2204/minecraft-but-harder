package xyz.devshah.minecraftbutharder.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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

            if (helmetLore.contains("§6Night Vision")) {
                if (player.getInventory().getItemInOffHand().getType().equals(Material.TORCH)) {
                    addEffect(player, PotionEffectType.NIGHT_VISION, 0);
                } else {
                    removeEffect(player, PotionEffectType.NIGHT_VISION);
                }
            }
            else { removeEffect(player, PotionEffectType.NIGHT_VISION); }

            if (helmetLore.contains("§6Stormborn")) {
                if (player.getWorld().hasStorm()) {
                    if (player.getEyeLocation().getY() >= player.getWorld().getHighestBlockYAt(player.getLocation())) {
                        addEffect(player, PotionEffectType.STRENGTH, 0);
                        addEffect(player, PotionEffectType.REGENERATION, 0);
                    }
                    else {
                        removeEffect(player, PotionEffectType.STRENGTH);
                        removeEffect(player, PotionEffectType.REGENERATION);
                    }
                }
                else {
                    removeEffect(player, PotionEffectType.STRENGTH);
                    removeEffect(player, PotionEffectType.REGENERATION);
                }
            } 
            else {
                removeEffect(player, PotionEffectType.STRENGTH);
                removeEffect(player, PotionEffectType.REGENERATION);
            } 

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
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack chestplate = player.getInventory().getChestplate();
            ItemStack boots = player.getInventory().getBoots();

            if (chestplate != null && chestplate.getItemMeta() != null && chestplate.getItemMeta().getLore() != null) {

                // Divine Intervention
                if (chestplate.getItemMeta().getLore().contains("§6Divine Intervention")) {
                    double health = player.getHealth();
                    double damage = event.getFinalDamage();

                    if ((health - damage) <= 0) {
                        Random random = new Random();
                        double r = random.nextDouble();

                        if (r <= 0.40) {
                            event.setCancelled(true);
                            player.setHealth(12);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*5, 1, true, true, true));
                            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 2, 1);
                            player.getWorld().spawnParticle(Particle.FLASH, player.getLocation().add(0, 1, 0), 1);
                            player.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 45, 0.5, 1, 0.5, 0.1);
                        }
                    }
                }
            }
            
            if (boots != null && boots.getItemMeta() != null && boots.getItemMeta().getLore() != null) {

                // Earthshaker
                if (boots.getItemMeta().getLore().contains("§6Earthshaker")) {
                    if (event.getCause().equals(DamageCause.FALL)) {
                        event.setCancelled(true);
                        float fallHeight = player.getFallDistance();
                        double knockBackMultiplier = (fallHeight / 10) * (1 - (fallHeight / 5));


                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_BIG_FALL, 2, 1);
                        // player.getWorld().spawnParticle(Particle.EXPLOSION, player.getLocation(), 5, 2, 0, 2);
                        player.getWorld().spawnParticle(Particle.BLOCK, player.getLocation(), 20, 2, 0, 2);
                        
                        for (Entity entity : player.getNearbyEntities(5, 3, 5)) {
                            if (entity instanceof Monster || entity instanceof Player) {
                                Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector());
            
                                if (direction.length() == 0) {
                                    direction = player.getLocation().getDirection();
                                }
                                direction.normalize();
                                if (direction.getY() <= 1) { 
                                    direction.setY(1);  // Send the mobs flying
                                }
                                ((LivingEntity) entity).damage(3);
                                entity.setVelocity(direction.multiply(knockBackMultiplier));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler 
    public void onHungerIncrease(EntityExhaustionEvent event) {
        Player player = (Player) event.getEntity();
        ItemStack leggings = player.getInventory().getLeggings();

        if (leggings != null && leggings.getItemMeta() != null && leggings.getItemMeta().getLore() != null && leggings.getItemMeta().getLore().contains("§6Saturation")) {
            event.setExhaustion(event.getExhaustion() / 2);
            
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

                if (helmet.getItemMeta().getLore().contains("§6Thunderstruck")) {
                    Random random = new Random();
                    double r = random.nextDouble();
                    
                    // Players become immune to lightning strike damage
                    if (damager instanceof LightningStrike) {
                        event.setCancelled(true);
                    }
                    
                    // Strike lightning on damager
                    if (damager instanceof LivingEntity) {
                        Location location = damager.getLocation();
                        if (r <= 0.40) { damager.getWorld().strikeLightning(location); }
                    } 

                    else if (damager instanceof Arrow) {
                        if (((Arrow)damager).getShooter() instanceof LivingEntity) {
                            LivingEntity shooter = (LivingEntity) ((Arrow) damager).getShooter();
                            Location location = shooter.getLocation();
                            shooter.getWorld().strikeLightning(location);
                        }
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
                        player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
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
                        ((IronGolem) golem).addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, PotionEffect.INFINITE_DURATION, 1, false, false));

                        Bukkit.getServer().getScheduler().runTaskLater(plugin, golem::remove, 30*20);
                    }
                }

                // Adrenaline
                if (chestplate.getItemMeta().getLore().contains("§6Adrenaline")) {
                    double health = player.getHealth();
                    double damage = event.getFinalDamage();
                    double newHealth = health - damage;

                    if (newHealth <= 9) {
                        if (!(player.hasPotionEffect(PotionEffectType.STRENGTH))) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 20*30, 1, false, false, true));
                        }
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

                // Undead Army
                if (leggings.getItemMeta().getLore().contains("§6Undead Army") && damager instanceof LivingEntity) {
                    
                    Random random = new Random();
                    double r = random.nextDouble();
                    
                    if (r <= plugin.getConfig().getDouble("armor-enchants.undead-army-chance")) {
                        Entity zombie = damager.getWorld().spawnEntity(damager.getLocation(), EntityType.ZOMBIE);
                        ((Zombie) zombie).setTarget((LivingEntity) damager);
                        zombie.setCustomName("§4"+ player.getDisplayName() +"'s Raised Undead");
                        Bukkit.getServer().getScheduler().runTaskLater(plugin, zombie::remove, 20*20);
                    }
                }
                
                // Mirror Shield
                if (leggings.getItemMeta().getLore().contains("§6Mirror Shield") && damager instanceof Projectile) {
                    if (((Projectile) damager).getShooter() instanceof LivingEntity) {
                        LivingEntity shooter = (LivingEntity) ((Projectile) damager).getShooter();
                        Projectile projectile = (Projectile) damager;
                        Random random = new Random();
                        double r = random.nextDouble();

                        if (r <= 0.50) {
                            player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
                            event.setCancelled(true);
                            projectile.remove();

                            if (projectile.getType() == EntityType.ARROW || projectile.getType() == EntityType.SPECTRAL_ARROW) {
                                Location targetLocation = shooter.getEyeLocation();
                                Vector direction = targetLocation.subtract(player.getEyeLocation()).toVector().normalize().multiply(2);
                                Arrow arrow = player.launchProjectile(Arrow.class, direction);
                                arrow.setGravity(false);
                                arrow.setCritical(true);
                                Bukkit.getServer().getScheduler().runTaskLater(plugin, arrow::remove, 20*3);
                            }
                        }
                    }
                }

                // Shadowstep
                if (leggings.getItemMeta().getLore().contains("§6Shadowstep")) {
                    double damage = event.getFinalDamage();
                    double health = player.getHealth();
                    if ((health - damage) <= 7) {
                        Random random = new Random();
                        double r = random.nextDouble();

                        if (r <= 0.40) {
                            List<Entity> nearbyMobs = player.getNearbyEntities(25, 25, 15);
                            for (Entity entity : nearbyMobs) {
                                if (entity instanceof Mob) {
                                    if (player.equals(((Mob) entity).getTarget())) {
                                        ((Mob) entity).setTarget(null);
                                    }
                                }
                            }

                            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20*7, 0, false, false, true));
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cYou're invisible, quick, run!"));
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                            player.playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1, 1);
                            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aYou're visible again")), 20*7);
                        }
                    }
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
            }
        }
    }
}