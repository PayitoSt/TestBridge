package net.rpgmyth.testbridge;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class ItemsClassYML {
    private static FileConfiguration items = null;
    private static File itemsFile = null;
    public static FileConfiguration getItems(){

        if(items == null){
            reloadItems();
        }
        return items;
    }

    public static void reloadItems(){
        if(items == null){
            itemsFile = new File(TestBridge.plugin.getDataFolder(),"mmocore.yml");
        }
        items = YamlConfiguration.loadConfiguration(itemsFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(TestBridge.plugin.getResource("mmocore.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                items.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public static void saveItems(){
        try{
            items.save(itemsFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void registerItems(){
        itemsFile = new File(TestBridge.plugin.getDataFolder(),"mmocore.yml");
        if(!itemsFile.exists()){
            getItems().options().copyDefaults(true);
            saveItems();
        }
    }
}
