package xyz.devshah.minecraftbutharder.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Weapons {
    public static ItemStack devilStick;
    public static ItemStack venomBlade;
    public static ItemStack asgardAxe;
    public static ItemStack excalibur;
    public static ItemStack claudiusAegis;
    public static ItemStack devilSword;

    public static void init() {
        createDevilStick();
        createPoisonStoneSword();
        createAsgardAxe();
        createExcalibur();
        createClaudiusAegis();
        createDevilSword();
    }

    public static void createDevilStick() {
        ItemStack stick = new ItemStack(Material.STICK, 1);
        stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 14);

        ItemMeta meta = stick.getItemMeta();
        meta.setDisplayName("§8Devil's Stick");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§6Nausea");
        meta.setLore(lore);
        stick.setItemMeta(meta);

        devilStick = stick;
    }

    public static void createPoisonStoneSword() {
        ItemStack poisonStoneSword = new ItemStack(Material.STONE_SWORD, 1);
        poisonStoneSword.addEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta meta = poisonStoneSword.getItemMeta();

        meta.setDisplayName("§8Ancient Venomous Sword");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§6Venom");
        meta.setLore(lore);
        poisonStoneSword.setItemMeta(meta);

        venomBlade = poisonStoneSword;
    }

    public static void createAsgardAxe() {
        ItemStack godDiamondAxe = new ItemStack(Material.DIAMOND_AXE, 1);
        godDiamondAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        godDiamondAxe.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);

        ItemMeta meta = godDiamondAxe.getItemMeta();
        meta.setDisplayName("§4Axe of the Asgardian");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Wrath of God");
        lore.add("§9Enchanted axe made by");
        lore.add("§9Dwarvain blacksmiths on");
        lore.add("§9Nidavellir. Forged from Uru, this");
        lore.add("§9axe has the ability to summon lightning!");
        meta.setLore(lore);
        godDiamondAxe.setItemMeta(meta);

        asgardAxe = godDiamondAxe;
    }

    public static void createExcalibur() {
        ItemStack excaliburSword = new ItemStack(Material.DIAMOND_SWORD, 1);
        excaliburSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
        excaliburSword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 2);
        excaliburSword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 2);
        excaliburSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
        excaliburSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 2);


        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Wrath of God");
        lore.add("§6Venom");
        lore.add("§9The famed sword of the");
        lore.add("§9Makurian king. The excalibur");
        lore.add("§9seems to be endowed with a");
        lore.add("§9potion of the true God's power");

        ItemMeta meta = excaliburSword.getItemMeta();
        meta.setDisplayName("§4Excalibur");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        excaliburSword.setItemMeta(meta);

        excalibur = excaliburSword;
    }

    public static void createClaudiusAegis() {
        ItemStack shield = new ItemStack(Material.SHIELD, 1);
        shield.addUnsafeEnchantment(Enchantment.LUCK, 1);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§9Tiberius Claudius Caesar Augustus,");
        lore.add("§9the previous owner of this shield,");
        lore.add("§9fought and won many battles. Some");
        lore.add("§9say that his shield was the reason he");
        lore.add("§9won, others believe that it was his");
        lore.add("§9godly aura");

        ItemMeta meta = shield.getItemMeta();
        meta.setDisplayName("§cClaudius' Aegis");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        shield.setItemMeta(meta);

        claudiusAegis = shield;
    }

    public static void createDevilSword() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);

        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 7);
        sword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 7);
        sword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 4);


        ItemMeta meta = sword.getItemMeta();
        meta.setDisplayName("§kMine §4Mephistopheles' Blade§r §kMine");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§6Whirlwind");
        lore.add("§6Bubonic Plague");
        lore.add("Unbreakable");
        lore.add("§3This sword is believed to");
        lore.add("§3be the strongest weapon of");
        lore.add("§3all time.");
        lore.add("§r§oRecieved on defeating the Undead King");
        meta.setLore(lore);
        sword.setItemMeta(meta);

        devilSword = sword;
    }

}
