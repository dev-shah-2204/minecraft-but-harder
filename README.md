# Minecraft But Harder

## What does the plugin do?

The game is quite easy now. This plugin makes the game difficult. Two hits from a zombie and you're dead. Invisible spiders, super-fast creepers and a few more surprises. Also endermen don't drop ender pearls anymore. 

How do you get to the end? You summon the Withered Sovereign using ***/spawnboss.***
Winning the boss fight grants you 25 Eyes of Ender, 64 Ender Pearls and a really cool armor set and a sword. 13 more eyes than you need to complete an empty portal. If you need more, you can go for another boss fight.

To balance this out, players are granted cool rewards every 5 levels, which include armor, weapons and tools with unordinary enchantments. 

Secondly, upon dying, rather than trying to find the same spot and dying again and again and ultimately losing your hard earned items, they get stored in a grave and the location is sent to you in chat.

Moreover, you get the cooked variant of the food that an animal would usually drop. Cooked Porkchop instead of Porkchop, Steak instead of Beef, Cooked Mutton instead of Raw Mutton etc.

Lastly, there is an xp gain multiplier to progress the story faster. Most of these plugin settings can be customised in [`config.yml`](src/main/resources/config.yml).

## Setup

After running the server, you will see a [`config.yml`](src/main/resources/config.yml) file in the plugins folder, which you can edit to turn on/off certain features and edit settings like creeper speed, air guardian spawn chance, boss fight settings, exp gain multiplier, custom item enchantment settings etc..

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
Description: Spawn The Withered Sovereign.<br>
Usage: /spawnboss<br>

### givecustom
Description: Give custom items that the plugin offers<br>
Usage: /givecustom \<item\> [player]

<br>