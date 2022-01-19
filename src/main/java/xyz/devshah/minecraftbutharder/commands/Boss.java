package xyz.devshah.minecraftbutharder.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Boss implements CommandExecutor {
    private void addEffect(Zombie entity, PotionEffectType type, int level) {
        entity.addPotionEffect(new PotionEffect(type, Integer.MAX_VALUE, level, false, false));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawnboss")) {
            Player player;
            Location location;

            if (!(sender instanceof Player)) {
                player = sender.getServer().getPlayer(args[0]);
                if (player == null) {
                    System.out.println("§cPlayer not found");
                    return true;
                }
                location = player.getLocation().add(2, 0, 2);
            }

            else {
                if (!(sender.isOp())) {
                    sender.sendMessage("§cOnly server operators can spawn the boss");
                    return true;
                }
                if (((Player) sender).getLevel() < 50) {
                    sender.sendMessage("§cYou need to be alteast level 50 to spawn the boss");
                    return true;
                }
                else {
                    location = ((Player) sender).getLocation().add(2, 0, 2);
                    player = (Player) sender;
                }
            }

            if (!(player.getWorld().getEnvironment().equals(World.Environment.NETHER))) {
                sender.sendMessage("§cThe undead king can only be fought in the nether");
                return true;
            }

            Entity boss = player.getWorld().spawnEntity(location, EntityType.ZOMBIE);
            Zombie zombie = (Zombie) boss;
            zombie.setCustomName("Undead King");
            zombie.setAdult(); // A baby boss would look weird

            List<Entity> entityList = player.getNearbyEntities(10,10,10);

            for (Entity entity : entityList) {
                if (entity instanceof Chicken) {
                    List<Entity> entities = entity.getPassengers();
                    for (Entity otherEntity : entities ) {
                        if (otherEntity == (Entity) zombie) {
                            entity.removePassenger(otherEntity);
                        }
                    }
                }
            }

            ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET, 1);
            ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
            ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
            ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS, 1);
            ItemStack weapon = new ItemStack(Material.NETHERITE_SWORD, 1);

            helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
            helmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 5);

            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
            chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);

            leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
            leggings.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);

            boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 12);
            boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);

            zombie.getEquipment().setHelmet(helmet);
            zombie.getEquipment().setChestplate(chestplate);
            zombie.getEquipment().setLeggings(leggings);
            zombie.getEquipment().setBoots(boots);
            zombie.getEquipment().setItemInMainHand(weapon);

            zombie.getEquipment().setHelmetDropChance(0);
            zombie.getEquipment().setChestplateDropChance(0);
            zombie.getEquipment().setLeggingsDropChance(0);
            zombie.getEquipment().setBootsDropChance(0);
            zombie.getEquipment().setItemInMainHandDropChance(0); // We'll drop a custom axe later

            zombie.setTarget((Player) sender);

            zombie.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 3*20, 255, false, false));
            addEffect(zombie, PotionEffectType.FIRE_RESISTANCE, 0);
            addEffect(zombie, PotionEffectType.INCREASE_DAMAGE, 0);
            addEffect(zombie, PotionEffectType.ABSORPTION, 3);
            addEffect(zombie, PotionEffectType.HEALTH_BOOST, 10);
            addEffect(zombie, PotionEffectType.SPEED, 1);
            addEffect(zombie, PotionEffectType.JUMP, 1);
            addEffect(zombie, PotionEffectType.GLOWING, 0);

        }

        return true;
    }
}
