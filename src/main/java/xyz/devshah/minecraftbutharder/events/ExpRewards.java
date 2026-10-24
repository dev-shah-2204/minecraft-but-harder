package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import xyz.devshah.minecraftbutharder.MinecraftButHarder;
import xyz.devshah.minecraftbutharder.items.Armor;
import xyz.devshah.minecraftbutharder.items.Items;
import xyz.devshah.minecraftbutharder.items.Weapons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpRewards implements Listener {
    // Reward levels a player has already received, stored on the player so it survives restarts
    private final NamespacedKey receivedRewardsKey;
    public ExpRewards(MinecraftButHarder plugin) { this.receivedRewardsKey = new NamespacedKey(plugin, "received-level-rewards"); }

    private void giveItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), item);
        } else {
            player.getInventory().addItem(item);
        }
    }

    @EventHandler
    public void onPlayerLevelUp(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();

        Set<Integer> received = new HashSet<>();
        if (data.has(receivedRewardsKey, PersistentDataType.INTEGER_ARRAY)) {
            for (int level : data.get(receivedRewardsKey, PersistentDataType.INTEGER_ARRAY)) { received.add(level); }
        } else {
            // Players from before this was tracked already got the rewards up to their current level
            for (int level = 5; level <= event.getOldLevel(); level += 5) { received.add(level); }
            saveReceived(data, received);
        }

        // ExpConcession temporarily sets the level to 201 while an anvil is open, that isn't a real level up
        if (player.getOpenInventory().getTopInventory() instanceof AnvilInventory) { return; }

        // Every reward level passed on the way up, in case several were skipped at once
        List<Integer> newRewards = new ArrayList<>();
        for (int level = (event.getOldLevel() / 5 + 1) * 5; level <= event.getNewLevel(); level += 5) {
            // Skip rewards already received, so dying or spending levels can't farm them
            if (received.add(level)) { newRewards.add(level); }
        }
        if (newRewards.isEmpty()) { return; }
        saveReceived(data, received);

        // Give player rewards on level 5 or multiple of 5
        player.setHealth(20);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
        player.setFoodLevel(20);
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 5, 0);

        for (int level : newRewards) {
            giveLevelReward(player, level);
        }
    }

    private void saveReceived(PersistentDataContainer data, Set<Integer> received) {
        data.set(receivedRewardsKey, PersistentDataType.INTEGER_ARRAY, received.stream().mapToInt(Integer::intValue).toArray());
    }

    private void giveLevelReward(Player player, int newLevel) {
        Server server = player.getServer();

        if (newLevel == 5) {
            server.broadcastMessage(player.getDisplayName() + " got an enchanted Golden Pickaxe!");
            ItemStack pick = new ItemStack(Material.GOLDEN_PICKAXE, 1);
            pick.addEnchantment(Enchantment.UNBREAKING, 2);
            pick.addEnchantment(Enchantment.EFFICIENCY, 3);
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

        if (newLevel == 25) {
            server.broadcastMessage(player.getDisplayName() + " got the §4Axe of the Asgardian§r!");
            giveItem(player, Weapons.asgardAxe);
        }

        if (newLevel == 30) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Leggings§r!");
            giveItem(player, Armor.forbiddenImperviousLeggings);
        }

        if (newLevel == 35) {
            server.broadcastMessage(player.getDisplayName() + " got the §eEthereal Pickaxe§r!");
            giveItem(player, Items.etherealPickaxe);
        }

        if (newLevel == 40) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Helmet§r!");
            giveItem(player, Armor.forbiddenImperviousHelmet);
        }

        if (newLevel == 45) {
            server.broadcastMessage(player.getDisplayName() + " got the §cExcalibur§r!");
            giveItem(player, Weapons.excalibur);
        }

        if (newLevel == 50) {
            server.broadcastMessage(player.getDisplayName() + " got the §9Forbidden Impervious Boots§r!");
            giveItem(player, Armor.forbiddenImperviousBoots);
        }

        if (newLevel == 55) {
            server.broadcastMessage(player.getDisplayName() + " got the §cClaudius' Aegis§r!");
            giveItem(player, Weapons.claudiusAegis);
        }

        if (newLevel == 60) {
            server.broadcastMessage(player.getDisplayName() + " got the §eServant's Shovel§r!");
            giveItem(player, Items.servantShovel);
        }

        if (newLevel == 65) {
            server.broadcastMessage(player.getDisplayName() + " got the §eMiner's Best Friend§r!");
            giveItem(player, Items.minerFriend);
        }

        if (newLevel == 70) {
            server.broadcastMessage(player.getDisplayName() + " got the §b§kMine§r §eZeus' Helm §kMine§r!");
            giveItem(player, Armor.godHelmet);
        }

        if (newLevel == 75) {
            server.broadcastMessage(player.getDisplayName() + " got the §b§kMine§r §eAres' Breastplate §kMine§r!");
            giveItem(player, Armor.godChestplate);
        }

        if (newLevel == 80) {
            server.broadcastMessage(player.getDisplayName() + " got the §b§kMine§r §ePerseus' Greaves §kMine§r!");
            giveItem(player, Armor.godLeggings);
        }

        if (newLevel == 85) {
            server.broadcastMessage(player.getDisplayName() + " got the §b§kMine§r §ePoseidon's Tidewalkers §kMine§r!");
            giveItem(player, Armor.godBoots);
        }
    }

}
