package xyz.devshah.minecraftbutharder.events;


import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import xyz.devshah.minecraftbutharder.MinecraftButHarder;
import xyz.devshah.minecraftbutharder.items.Armor;
import xyz.devshah.minecraftbutharder.items.Weapons;

import java.util.List;
import java.util.Random;

public class ZombieBossEvents implements Listener {
    MinecraftButHarder plugin;
    public ZombieBossEvents(MinecraftButHarder plugin) {this.plugin = plugin;}

    private void spawnAirGuardian(Entity entity, Player player) {
        Entity chicken = (Chicken) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CHICKEN);
        Zombie rider = (Zombie) chicken.getWorld().spawnEntity(chicken.getLocation(), EntityType.ZOMBIE);
        rider.setBaby();

        ItemStack weapon = Weapons.devilStick;

        rider.getEquipment().setItemInMainHand(weapon);
        rider.getEquipment().setItemInMainHandDropChance(0.035F); // 3.5% Chance of dropping the Knockback 15 stick
        rider.setCustomName("Elite Air Guardian");
        rider.setHealth(1); // half heart
        chicken.addPassenger(rider);

        ((Chicken) chicken).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
        ((Chicken) chicken).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
        ((Chicken) chicken).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 0));
        ((Chicken) chicken).setTarget(player);
    }

    private void spawnVexMinion(Entity entity, Player player) {
        Entity minion = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.VEX);
        minion.setCustomName("Suicide Bomber");

        ((Vex) minion).setHealth(6); // 3 hearts
        ((Vex) minion).setTarget(player);
    }

    @EventHandler
    public void onZombieTurnFishman(EntityTransformEvent event) {
        if (event.getEntity().getCustomName() != null) {
            if (event.getEntity().getCustomName() == "Undead King" && event.getEntity() instanceof Zombie) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity.getCustomName() == "Undead King" && entity instanceof Zombie) {
            if (event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                event.setDamage(0);
            }
        }

        if (entity instanceof Creeper) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
                event.setDamage(0);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (entity.getCustomName() != null) {
            if (entity.getCustomName().equalsIgnoreCase("Undead King") && damager instanceof IronGolem && entity instanceof Zombie) {
                event.setDamage(2); // Almost immune to Iron Golems
            }

            if (entity.getCustomName().equalsIgnoreCase("Undead King") && damager instanceof Player && entity instanceof Zombie) {
                ((Zombie) entity).setTarget((LivingEntity) event.getDamager());
                Random random = new Random();
                double r = random.nextDouble();

                if (r <= 0.20) {
                    spawnAirGuardian(entity, (Player) damager);
                    spawnAirGuardian(entity, (Player) damager);
                }

                if (r <= 0.30) {
                    spawnVexMinion(entity, (Player) damager);
                    spawnVexMinion(entity, (Player) damager);
                }
            }

        }

        if (damager.getCustomName() != null && entity instanceof Player) {
            if (damager.getCustomName().equalsIgnoreCase("Undead King") && damager instanceof Zombie) {
                Player player = (Player) entity;
                Random random = new Random();
                double r = random.nextDouble();

                if (r < 0.30) {
                    Vector velocity = new Vector(0, 4, 0);
                    player.setVelocity(velocity);
                }
            }


            if (damager.getCustomName().equalsIgnoreCase("Suicide Bomber")) {
                event.setDamage(0);
                damager.remove();
                entity.getWorld().createExplosion(entity.getLocation(), 1);
            }

            if (damager.getCustomName().equalsIgnoreCase("Elite Air Guardian") && entity instanceof Player) {
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 10 * 20, 3, false, false));
            }

        }
    }


    @EventHandler
    public void onTargetChange(EntityTargetEvent event) {
        Entity entity = event.getEntity();
        Entity target = event.getTarget();
        if (entity.getCustomName() != null) {
            if (entity.getCustomName() == "Undead King" && entity instanceof Zombie) {
                if (!(target instanceof Player)) {
                    List<Entity> entityList = event.getEntity().getNearbyEntities(50, 50, 50);

                    for (Entity mob : entityList) {
                        if (mob instanceof Player) {
                            ((Zombie) entity).setTarget((LivingEntity) mob);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void onBossDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.getCustomName() != null && entity.getCustomName().equalsIgnoreCase("Undead King") && entity instanceof Zombie) {
            World world = entity.getWorld();
            Sound sound = Sound.UI_TOAST_CHALLENGE_COMPLETE;
            Location location = entity.getLocation();

            world.playSound(entity.getLocation(), Sound.BLOCK_ANVIL_PLACE, 10, 0);
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {world.playSound(location, sound, 10, 0);}, 6);
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {world.playSound(location, sound, 10, 0);}, 6);

            entity.getServer().broadcastMessage("§aThe undead king has been quelled. Congratulations, brave warriors!");

            for (Entity mob : entity.getWorld().getEntities()) {
                if (mob.getCustomName() != null && mob.getCustomName().equalsIgnoreCase("Elite Air Guardian")) {
                    mob.remove();
                }
                if (mob.getCustomName() != null && mob.getCustomName().equalsIgnoreCase("Suicide Bomber")) {
                    mob.remove();
                }
                if (mob.getCustomName() != null && mob.getCustomName().equalsIgnoreCase("Air Guardian")) {
                    mob.remove();
                }
            }

            Block deathBlock = entity.getWorld().getBlockAt(entity.getLocation());
            deathBlock.setType(Material.OBSIDIAN);

            deathBlock.getLocation().add(1, 0, 0).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(-1, 0, 0).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(0, 0, 1).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(0, 0, -1).getBlock().setType(Material.OBSIDIAN);

            deathBlock.getLocation().add(1, 1, 0).getBlock().setType(Material.TORCH);
            deathBlock.getLocation().add(-1, 1, 0).getBlock().setType(Material.TORCH);
            deathBlock.getLocation().add(0, 1, 1).getBlock().setType(Material.TORCH);
            deathBlock.getLocation().add(0, 1, -1).getBlock().setType(Material.TORCH);

            deathBlock.getLocation().add(1, 0, 1).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(-1, 0, 1).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(1, 0, -1).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(-1, 0, -1).getBlock().setType(Material.OBSIDIAN);
            deathBlock.getLocation().add(0, 1, 0).getBlock().setType(Material.OBSIDIAN);

            Block _chest = deathBlock.getLocation().add(0, 2, 0).getBlock();
            _chest.setType(Material.CHEST);

            Chest chest = (Chest) _chest.getState();
            chest.getInventory().setItem(0, Weapons.devilSword);
            chest.getInventory().setItem(1, Armor.beelzebubHelmet);
            chest.getInventory().setItem(2, Armor.asmodiusChestplate);
            chest.getInventory().setItem(3, Armor.mammonLeggings);
            chest.getInventory().setItem(4, Armor.belphegorBoots);
            chest.getInventory().setItem(5, new ItemStack(Material.ENDER_EYE, 25));
            chest.getInventory().setItem(6, new ItemStack(Material.ENDER_PEARL, 16));
            chest.getInventory().setItem(7, new ItemStack(Material.ENDER_PEARL, 16));
            chest.getInventory().setItem(8, new ItemStack(Material.ENDER_PEARL, 16));
            chest.getInventory().setItem(9, new ItemStack(Material.ENDER_PEARL, 16));


        }
    }
}
