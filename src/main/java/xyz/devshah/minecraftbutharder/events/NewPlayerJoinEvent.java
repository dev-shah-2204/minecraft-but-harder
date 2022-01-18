package xyz.devshah.minecraftbutharder.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class NewPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();

            meta.setTitle("Empic Tutorial");
            meta.setAuthor("dev-shah-2204");

            List<String> pages = new ArrayList<String>();

            pages.add("Hello, welcome to the server! This server has a custom plugin called \"Minecraft But Harder\" and I'll tell you what it does!");
            pages.add("This plugin makes the game more difficult to play, the zombies and skeletons are really strong, the creepers are a lot faster and the spiders are invisible!");
            pages.add("Every 5 levels, you'll get an exciting reward. Custom Enchants starting from level 10 like Night Vision, Iron Army, Beastly Aura and many more!");
            pages.add("Endermen don't drop Ender Pearls, you have to defeat the boss to get 25 Eyes of Ender and 64 Ender Pearls and a really cool combat set");
            pages.add("The boss is really fucking strong but weak in water. please dont exploit");

            meta.setPages(pages);
            book.setItemMeta(meta);

            player.getInventory().addItem(book);

        }
    }
}