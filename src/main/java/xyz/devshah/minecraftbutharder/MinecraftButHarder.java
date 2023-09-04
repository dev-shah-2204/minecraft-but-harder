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
        System.out.println("MinecraftButHarder plugin loaded");

        // Commands
        Basic basic = new Basic();
        Boss boss = new Boss();
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

        manager.registerEvents(new CustomArmorEnchants(this), this);
        manager.registerEvents(new CustomItemEnchants(this), this);
        manager.registerEvents(new CustomMobEvents(), this);
        manager.registerEvents(new ExpRewards(), this);
        manager.registerEvents(new Graves(this), this);
        manager.registerEvents(new FreeCookedFood(), this);
        manager.registerEvents(new NewPlayerJoinEvent(), this);
        manager.registerEvents(new StrongMobs(), this);
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
