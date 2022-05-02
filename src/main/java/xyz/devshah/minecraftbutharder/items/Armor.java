package xyz.devshah.minecraftbutharder.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Armor {
    public static ItemStack oldSafetyHelmet;

    public static ItemStack forbiddenImperviousHelmet;
    public static ItemStack forbiddenImperviousChestplate;
    public static ItemStack forbiddenImperviousLeggings;
    public static ItemStack forbiddenImperviousBoots;

    public static ItemStack asmodiusChestplate;
    public static ItemStack beelzebubHelmet;
    public static ItemStack mammonLeggings;
    public static ItemStack belphegorBoots;

    public static void init() {
        // Forbidden Impervious Set
        createNightCap();
        createForbiddenImperviousHelmet();
        createForbiddenImperviousChestplate();
        createForbiddenImperviousLeggings();
        createForbiddenImperviousBoots();

        // Devil Set
        createDevilHelmet();
        createDevilChestplate();
        createDevilPants();
        createDevilBoots();
    }

    private static void createNightCap() {
        ItemStack nightVisionHelmet = new ItemStack(Material.LEATHER_HELMET);

        nightVisionHelmet.addEnchantment(Enchantment.VANISHING_CURSE, 1);
        nightVisionHelmet.addEnchantment(Enchantment.DURABILITY, 2);

        ItemMeta meta = nightVisionHelmet.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        meta.setDisplayName("§8Old Safety Helmet");
        lore.add("§6Night Vision");
        meta.setLore(lore);
        nightVisionHelmet.setItemMeta(meta);

        oldSafetyHelmet = nightVisionHelmet;
    }

    // Forbidden Imeprvious Set
    private static void createForbiddenImperviousHelmet() {
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        helmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);

        ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName("§9Forbidden Impervious Helmet");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Night Vision");
        lore.add("§6Respiration");
        lore.add("§o§5Part of the Forbidden Impervious set§r");
        lore.add("§6The previous owner of this");
        lore.add("§6helmet was a scuba-diver.");
        lore.add("§6Naturally, you can breathe under");
        lore.add("§6water with it.");

        meta.setLore(lore);
        helmet.setItemMeta(meta);

        forbiddenImperviousHelmet = helmet;
    }

    private static void createForbiddenImperviousChestplate() {
        ItemStack imperviousChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        imperviousChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        imperviousChestplate.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 2);

        ItemMeta meta = imperviousChestplate.getItemMeta();
        meta.setDisplayName("§9Forbidden Impervious Chestplate");
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Aura of Pain");
        lore.add("§6Iron Army");
        lore.add("§o§5Part of the Forbidden Impervious set§r");
        lore.add("§6A chestplate of ancient origin,");
        lore.add("§6made by the best of the blacksmiths");
        lore.add("§6of that time.");

        meta.setLore(lore);
        imperviousChestplate.setItemMeta(meta);

        forbiddenImperviousChestplate = imperviousChestplate;
    }

    private static void createForbiddenImperviousLeggings() {
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Fire Resistance");
        lore.add("§6Saturation");
        lore.add("§o§5Part of the Forbidden Impervious set§r");
        lore.add("§6These leggings belonged to");
        lore.add("§6the king of Nekor, who I once");
        lore.add("§6fought, all I can say is that he");
        lore.add("§6won't be needing these anymore");

        ItemMeta meta = leggings.getItemMeta();
        meta.setDisplayName("§9Forbidden Impervious Leggings");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        meta.setLore(lore);

        leggings.setItemMeta(meta);
        forbiddenImperviousLeggings = leggings;
    }

    private static void createForbiddenImperviousBoots() {
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 3);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Beastly Aura");
        lore.add("§6Speed");
        lore.add("§0§5Part of the Forbidden Impervious set§r");
        lore.add("§6These boots are quite common");
        lore.add("§6among the wolf tamers of");
        lore.add("§6Almohad Caliphate, you'll");
        lore.add("§6soon see the cause of this.");

        ItemMeta meta = boots.getItemMeta();
        meta.setDisplayName("§9Forbidden Impervious Boots");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);

        boots.setItemMeta(meta);
        forbiddenImperviousBoots = boots;
    }

    // Devil's Armor set
    private static void createDevilHelmet() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Night Vision");
        lore.add("§6Respiration");
        lore.add("§6Tracker");
        lore.add("§oPart of the Devil's Armor set");
        lore.add("§4A helmet forged with dark matter");
        lore.add("§4found in Sheol, a place of darkness");
        lore.add("§4where the dead go, that has the");
        lore.add("§4eternal hunter's tracking abilities");

        ItemMeta meta = helmet.getItemMeta();
        meta.setDisplayName("§kMine§r §4Beelzebub's Helmet§r §kMine§r");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        helmet.setItemMeta(meta);
        beelzebubHelmet = helmet;
    }

    private static void createDevilChestplate() {
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Aura of Pain");
        lore.add("§6Devil's Invitation");
        lore.add("§6Absorbtion");
        lore.add("§6Health Boost");
        lore.add("§oPart of the Devil's Armor set");
        lore.add("§4The idea originally was to equip");
        lore.add("§4the devil with a grappling hook,");
        lore.add("§4but the wizards from Eden had other");
        lore.add("§4plans.");

        ItemMeta meta = chestplate.getItemMeta();
        meta.setDisplayName("§kMine§r §4Asmodius' Breastplate§r §kMine§r");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        chestplate.setItemMeta(meta);
        asmodiusChestplate = chestplate;
    }

    private static void createDevilPants() {
        ItemStack pants = new ItemStack(Material.DIAMOND_LEGGINGS);
        pants.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        pants.addUnsafeEnchantment(Enchantment.THORNS, 3);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Fire Resistance");
        lore.add("§6Saturation");
        lore.add("§6Leap of Faith");
        lore.add("§oPart of the Devil's Armor set");
        lore.add("§4It's quite difficult to wear");
        lore.add("§4heavy pants in the heat of the");
        lore.add("§4inferno, but you should be fine");
        lore.add("§4here in the overworld.");
        lore.add("§4P.S. The semen has been cleaned this time.");

        ItemMeta meta = pants.getItemMeta();
        meta.setDisplayName("§kMine§r §4Mammon's Pants§r §kMine§r");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        pants.setItemMeta(meta);
        mammonLeggings = pants;
    }

    private static void createDevilBoots() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Unbreakable");
        lore.add("§6Dolphin's Grace");
        lore.add("§6Undead Army");
        lore.add("§6Speed");
        lore.add("§6Jump Boost");
        lore.add("§oPart of the Devil's Armor set");
        lore.add("§4Boots so light that they take");
        lore.add("§4make you lighter than you are.");
        lore.add("§4Made from ether, they give you");
        lore.add("§4special effects when you wear them");

        ItemMeta meta = boots.getItemMeta();
        meta.setDisplayName("§kMine§r §4Belphegor's Boots§r §kMine§r");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        boots.setItemMeta(meta);
        belphegorBoots = boots;
    }
}
