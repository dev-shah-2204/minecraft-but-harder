package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.devshah.minecraftbutharder.items.Armor;
import xyz.devshah.minecraftbutharder.items.Items;
import xyz.devshah.minecraftbutharder.items.Weapons;

public class ExpRewards implements Listener {

    private void giveItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        } else {
            player.getInventory().addItem(item);
        }
    }

    @EventHandler
    public void onPlayerLevelUp(PlayerLevelChangeEvent event) {
        int oldLevel = event.getOldLevel();
        int newLevel = event.getNewLevel();
        Player player = event.getPlayer();
        Server server = player.getServer();

        // Give player rewards on level 5 or multiple of 5
        if (newLevel % 5 == 0) {
            player.setHealth(20);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
            player.setFoodLevel(20);
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 0);
        }

        if (newLevel == 5) {
            server.broadcastMessage(player.getDisplayName() + " got an enchanted Golden Pickaxe!");
            ItemStack pick = new ItemStack(Material.GOLDEN_PICKAXE, 1);
            pick.addEnchantment(Enchantment.DURABILITY, 2);
            pick.addEnchantment(Enchantment.DIG_SPEED, 3);
            giveItem(player, pick);
        }

        if (newLevel == 10) {
            server.broadcastMessage(player.getDisplayName() + " got the §8Old Safety Helmet§r!");
            giveItem(player, Armor.oldSafetyHelmet);
        }

        if (newLevel == 15) {
            server.broadcastMessage(player.getDisplayName() + " got the §8Ancient Venomous Sword§r!");
            giveItem(player, Weapons.venomBlade);
        }

        if (newLevel == 20) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Chestplate§r!");
            giveItem(player, Armor.forbiddenImperviousChestplate);
        }

        if (newLevel == 30) {
            server.broadcastMessage(player.getDisplayName() + " got the §4Axe of the Asgardian§r!");
            giveItem(player, Weapons.asgardAxe);
        }

        if (newLevel == 35) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Leggings§r!");
            giveItem(player, Armor.forbiddenImperviousLeggings);
        }

        if (newLevel == 45) {
            server.broadcastMessage(player.getDisplayName() + " got the §eEthereal Pickaxe§r!");
            giveItem(player, Items.etherealPickaxe);
        }

        if (newLevel == 50) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Helmet§r!");
            giveItem(player, Armor.forbiddenImperviousHelmet);
        }

        if (newLevel == 60) {
            server.broadcastMessage(player.getDisplayName() + " got the §cExcalibur§r!");
            giveItem(player, Weapons.excalibur);
        }

        if (newLevel == 65) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Boots§r!");
            giveItem(player, Armor.forbiddenImperviousBoots);
        }

        if (newLevel == 70) {
            server.broadcastMessage(player.getDisplayName() + " got the §cClaudius' Aegis§r!");
            giveItem(player, Weapons.claudiusAegis);
        }

        if (newLevel == 80) {
            server.broadcastMessage(player.getDisplayName() + " got the §eServant's Shovel§r!");
            giveItem(player, Items.servantShovel);
        }

        if (newLevel == 90) {
            server.broadcastMessage(player.getDisplayName() + " got the §eMiner's Best Friend§r!");
            giveItem(player, Items.minerFriend);
        }
    }

}
