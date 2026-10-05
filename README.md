# Minecraft But Harder

## What does the plugin do?

The game is quite easy now. This plugin makes the game difficult. Two hits from a zombie and you're dead. Invisible spiders and super-fast creepers. It cannot get worse, right? Well, Endermen won't be dropping Ender Pearls. Yeah.

How do you beat the game? You summon the Zombie Boss using ***/spawnboss.***
The Zombie boss drops 25 Eyes of Ender, 64 Ender Pearls and a really cool combat set when you beat him. 13 more than you need to complete and empty portal.

But don't worry, there's a good part too. You get a cool reward every 5 xp levels. These include armor, tools and weapons with custom enchantments!<br>
Secondly, when you die, your precious items get stored in a chest at the spot that you died and you're given that spot's coordinates. You don't wanna lose that stuff, right?

Moreover, you get the cooked variant of the food that an animal would usually drop.
Cooked Porkchop instead of Porkchop, Steak instead of Beef, Cooked Mutton instead of Raw Mutton etc.

## Setup

Save the MinecraftButHarder jar file in the plugins folder of your server, along with `ArmorEquipEvent-1.7.2.jar`. The plugin relies on this other plugin for the custom armor enchantments to work. 

After running the server, you will see a `config.yml` file in the plugins folder, which you can edit to turn on/off certain features and edit settings like creeper speed, air guardian spawn chance, boss fight settings, exp gain multiplier, custom item enchantment settings etc..

## Commands

There are a total of 5 commands:
- heal (op only)
- feed (op only)
- coordinates / cords
- spawnboss (op only)
- givecustom (op only)

### heal
Description: Heals a player<br>
Usage: /heal [player]<br>

### feed
Description: Feeds a player<br>
Usage: /feed [player]<br>

### coordinates
Description: Get your cords in chat<br>
Usage: /coordinates [player]<br>
Aliases: cords<br>
Note: Only ops can get other players' cords.

### spawnboss
Description: Spawn the Undead King.<br>
Usage: /spawnboss<br>

### givecustom
Description: Give custom items that the plugin offers<br>
Usage: /givecustom \<item\> [player]

<br>

***IMPORTANT:***

- If you wish to edit this code, you'll need to change the systemPath to where you save the ArmorEquipEvent-1.7.2.jar file in the pom.xml file. 
