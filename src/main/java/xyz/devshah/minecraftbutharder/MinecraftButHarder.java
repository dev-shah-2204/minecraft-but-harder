package xyz.devshah.minecraftbutharder;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.devshah.minecraftbutharder.commands.Basic;
import xyz.devshah.minecraftbutharder.commands.Boss;
import xyz.devshah.minecraftbutharder.commands.GiveCustomItems;
import xyz.devshah.minecraftbutharder.commands.TabCompletion;
import xyz.devshah.minecraftbutharder.events.*;
import xyz.devshah.minecraftbutharder.items.Armor;
import xyz.devshah.minecraftbutharder.items.Items;
import xyz.devshah.minecraftbutharder.items.Weapons;

public final class MinecraftButHarder extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        System.out.println("MinecraftButHarder plugin loaded");

        // Commands
        Basic basic = new Basic();
        Boss boss = new Boss(this);
        GiveCustomItems customItems = new GiveCustomItems();

        getCommand("heal").setExecutor(basic);
        getCommand("feed").setExecutor(basic);
        getCommand("coordinates").setExecutor(basic);
        getCommand("spawnboss").setExecutor(boss);

        getCommand("givecustom").setExecutor(customItems);
        getCommand("givecustom").setTabCompleter(new TabCompletion());

        System.out.println("§a[MinecraftButHarder] commands loaded");

        // Events
        PluginManager manager = getServer().getPluginManager();

        if (getConfig().getBoolean("features.custom-armor-enchants")) {
            manager.registerEvents(new CustomArmorEnchants(this), this);
        } else {
            System.out.println("§c[MinecraftButHarder] custom armor enchants disabled in config.yml");
        }

        if (getConfig().getBoolean("features.custom-item-enchants")) {
            manager.registerEvents(new CustomItemEnchants(this), this);
        } else {
            System.out.println("§c[MinecraftButHarder] custom item enchants disabled in config.yml");
        }

        manager.registerEvents(new CustomMobEvents(), this);
        
        if (getConfig().getBoolean("features.exp-rewards")) {
            manager.registerEvents(new ExpRewards(), this);
        } else {
            System.out.println("§c[MinecraftButHarder] exp rewards disabled in config.yml");
        }

        if (getConfig().getBoolean("features.graves")) {
            manager.registerEvents(new Graves(this), this);
        } else {
            System.out.println("§c[MinecraftButHarder] graves disabled in config.yml");
        }
        
        if (getConfig().getBoolean("features.cooked-food")) {
            manager.registerEvents(new FreeCookedFood(this), this);

        } else {
            System.out.println("§c[MinecraftButHarder] cooked food disabled in config.yml");
        }
        
        manager.registerEvents(new NewPlayerJoinEvent(), this);
        
        if (getConfig().getBoolean("features.strong-mobs")) {
            manager.registerEvents(new StrongMobs(this), this);
        } else {
            System.out.println("§c[MinecraftButHarder] strong mobs disabled in config.yml");
        }

        manager.registerEvents(new ZombieBossEvents(this), this);
        manager.registerEvents(new ExpConcession(this), this);

        System.out.println("§a[MinecraftButHarder] events loaded");

        // Items
        Armor.init();
        Items.init();
        Weapons.init();


        System.out.println("§a[MinecraftButHarder] items created");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
