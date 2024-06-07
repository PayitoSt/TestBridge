package net.rpgmyth.testbridge;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadItems {
    public static Map<String, List<String>> loadMMOItems() {

        Map<String, List<String>> mmocoreItems = new HashMap<>();

        FileConfiguration mmoCore = ItemsClassYML.getItems();
        List<String> itemsToHave;
        for (String classToCheck : mmoCore.getKeys(false)) {

            if (classToCheck != null) {
                itemsToHave = mmoCore.getStringList(classToCheck);
                mmocoreItems.put(classToCheck.toUpperCase(), itemsToHave);
            }
        }

        return mmocoreItems;
    }
}
