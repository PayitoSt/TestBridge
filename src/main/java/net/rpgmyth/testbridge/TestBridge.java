package net.rpgmyth.testbridge;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TestBridge extends JavaPlugin {

    public static Plugin plugin;

    public static Map<String, List<String>> getMMOItems = new HashMap<>();
    @Override
    public void onEnable() {
        plugin = JavaPlugin.getPlugin(TestBridge.class);

        ItemsClassYML.registerItems();
        getMMOItems = LoadItems.loadMMOItems();
        registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new TestListener(), this);
    }
}
