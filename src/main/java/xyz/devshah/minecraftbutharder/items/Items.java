package xyz.devshah.minecraftbutharder.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Items {
    public static ItemStack etherealPickaxe;
    public static ItemStack servantShovel;
    public static ItemStack minerFriend;

    public static void init() {
        createEtherealPickaxe();
        createServantShovel();
        createMinerFriend();
    }

    private static void createEtherealPickaxe() {
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 4);
        pickaxe.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 2);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Ether Surge");
        lore.add("§9Enchanted pickaxe with");
        lore.add("§9added potions and made");
        lore.add("§9other-worldly materials");

        ItemMeta meta = pickaxe.getItemMeta();
        meta.setDisplayName("§eEthereal Pickaxe");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);

        pickaxe.setItemMeta(meta);
        etherealPickaxe = pickaxe;
    }

    private static void createServantShovel() {
        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL, 1);
        shovel.addEnchantment(Enchantment.DIG_SPEED, 4);
        shovel.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Venom");
        lore.add("§9Enchanted pickaxe with");
        lore.add("§9added potions and made");
        lore.add("§9other-worldly materials");

        ItemMeta meta = shovel.getItemMeta();
        meta.setDisplayName("§eServant's Shovel");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);

        shovel.setItemMeta(meta);
        servantShovel = shovel;

    }

    private static void createMinerFriend() {
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
        pickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Ether Surge");
        lore.add("§6Blast Mining");
        lore.add("§9It truely is a miner's");
        lore.add("§9best friend");

        ItemMeta meta = pickaxe.getItemMeta();
        meta.setDisplayName("§eMiner's best friend");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);

        pickaxe.setItemMeta(meta);
        minerFriend = pickaxe;
    }

}
