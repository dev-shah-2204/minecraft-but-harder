package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import xyz.devshah.minecraftbutharder.items.Weapons;

import java.util.Random;

public class StrongMobs implements Listener {
    private void addEquipment(Entity entity, int avgLevel, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack weapon) {
        int enchantLevel;
        if (avgLevel <= 7) { enchantLevel = 3; }  // Really difficult for levels lower than 7
        else {enchantLevel = (int) (avgLevel / 4.5);}  // Moderately difficult for levels higher than 7

        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enchantLevel);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enchantLevel);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enchantLevel);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enchantLevel);

        ((LivingEntity) entity).getEquipment().setHelmet(helmet);
        ((LivingEntity) entity).getEquipment().setChestplate(chestplate);
        ((LivingEntity) entity).getEquipment().setLeggings(leggings);
        ((LivingEntity) entity).getEquipment().setBoots(boots);
        ((LivingEntity) entity).getEquipment().setItemInMainHand(weapon);

        ((LivingEntity) entity).getEquipment().setHelmetDropChance(0.1F);
        ((LivingEntity) entity).getEquipment().setChestplateDropChance(0.1F);
        ((LivingEntity) entity).getEquipment().setLeggingsDropChance(0.1F);
        ((LivingEntity) entity).getEquipment().setBootsDropChance(0.1F);
        ((LivingEntity) entity).getEquipment().setItemInMainHandDropChance(0.15F);
    }

    @EventHandler
    public void MobSpawnEvent(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        Random random = new Random();

        // Leather Armor
        ItemStack lHelmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack lChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack lLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack lBoots = new ItemStack(Material.LEATHER_BOOTS);

        // Iron Armor
        ItemStack iHelmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack iChestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack iLeggings = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack iBoots = new ItemStack(Material.IRON_BOOTS, 1);

        // Gold Armor
        ItemStack gHelmet = new ItemStack(Material.GOLDEN_HELMET, 1);
        ItemStack gChestplate = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
        ItemStack gLeggings = new ItemStack(Material.GOLDEN_LEGGINGS, 1);
        ItemStack gBoots = new ItemStack(Material.GOLDEN_BOOTS, 1);

        // Diamond Armor
        ItemStack dHelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemStack dChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemStack dLeggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemStack dBoots = new ItemStack(Material.IRON_BOOTS, 1);

        // Swords
        ItemStack iSword = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack gSword = new ItemStack(Material.GOLDEN_SWORD, 1);

        // Bow
        ItemStack bow = new ItemStack(Material.BOW, 1);

        int totalLevel = 0;
        int playerCount = 0;

        for (Player player : entity.getServer().getOnlinePlayers()) {
            totalLevel += player.getLevel();
            playerCount += 1;
        }

        int avgLevel = 0;
        if (playerCount != 0) {
            avgLevel = (int) (totalLevel / playerCount);
        }


        if (entity instanceof Zombie || entity instanceof ZombieVillager) {
            // Giving gear
            if (entity.getCustomName() == null) {
                double r = random.nextDouble();
                if (r <= 0.45) {
                    addEquipment(entity, avgLevel, lHelmet, lChestplate, lLeggings, lBoots, gSword);
                } else if (r <= 0.75) {
                    addEquipment(entity, avgLevel, gHelmet, gChestplate, gLeggings, gBoots, gSword);
                } else if (r <= 0.90) {
                    addEquipment(entity, avgLevel, iHelmet, iChestplate, iLeggings, iBoots, iSword);
                } else {
                    addEquipment(entity, avgLevel, dHelmet, dChestplate, dLeggings, dBoots, iSword);
                }

                int effectAmplifier = ((int) (avgLevel / 6));
                if (effectAmplifier > 0) {
                    effectAmplifier -= 1;
                }
                ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, effectAmplifier, false, false));

                // Spawning Air Guardians
                double airGuardianSpawn = random.nextDouble();
                if (airGuardianSpawn <= 0.20) {
                    Chicken chicken = (Chicken) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CHICKEN);
                    Zombie rider = (Zombie) entity;
                    rider.setBaby();

                    rider.getEquipment().setHelmet(dHelmet);
                    rider.getEquipment().setItemInMainHand(Weapons.devilStick);

                    rider.getEquipment().setHelmetDropChance(0);
                    rider.getEquipment().setItemInMainHandDropChance(0.1F);
                    rider.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    rider.setHealth(1);

                    chicken.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));
                    chicken.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
                    chicken.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, 0));
                    chicken.addPassenger(rider);
                }
            }
        }

        if (entity instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) entity;
            double r = random.nextDouble();

            bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, ((int) (avgLevel / 4)));

            if (r <= 0.45) { addEquipment(entity, avgLevel, lHelmet, lChestplate, lLeggings, lBoots, bow); }
            else if (r <= 0.75) { addEquipment(entity, avgLevel, gHelmet, gChestplate, gLeggings, gBoots, bow); }
            else if (r <= 0.90) { addEquipment(entity, avgLevel, iHelmet, iChestplate, iLeggings, iBoots, bow); }
            else { addEquipment(entity, avgLevel, dHelmet, dChestplate, dLeggings, dBoots, bow); }
        }

        if (entity instanceof Creeper) {
            ((LivingEntity) entity).setHealth(1);
            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 7, false, false));
        }

        if (entity instanceof Spider) {
            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, true, true));
        }
    }

    // Remove speed from creeper on explode
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Creeper) {
            ((Creeper) event.getEntity()).removePotionEffect(PotionEffectType.SPEED);
        }
    }

}
