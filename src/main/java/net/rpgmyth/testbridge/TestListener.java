package net.rpgmyth.testbridge;

import dev.lone.itemsadder.api.CustomStack;
import net.Indyuce.mmocore.api.event.PlayerEnterCastingModeEvent;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.api.player.profess.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.List;

public class TestListener implements Listener {

    @EventHandler
    public void hasCustomWeapon(PlayerEnterCastingModeEvent event){
        Player player = event.getPlayer();
        PlayerClass playerClass = PlayerData.get(player.getUniqueId()).getProfess();
        if(playerClass.getName().equalsIgnoreCase("Human"))return;
        if(!checkItem(player, playerClass.getId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void switchSlot(PlayerItemHeldEvent event){
        if(PlayerData.get(event.getPlayer()).isCasting()){
            PlayerData.get(event.getPlayer()).leaveSkillCasting();
        }
    }

    @EventHandler
    public void playerDrop(PlayerDropItemEvent event){
        if(PlayerData.get(event.getPlayer()).isCasting()){
            PlayerData.get(event.getPlayer()).leaveSkillCasting();
        }
    }

    private boolean checkItem(Player player, String playerClass) {
        List<String> customWeapons = TestBridge.getMMOItems.get(playerClass);
        if (customWeapons == null) {
            return false;
        }
        if(!isValidItem(player)){
            return false;
        }
        String itemId = CustomStack.byItemStack(player.getInventory().getItemInMainHand()).getId();

        if (!customWeapons.contains(itemId)) {
            return false;
        }

        return !itemId.contains("_shiny") || player.hasPermission("rpgclass.use.shiny");
    }

    private boolean isValidItem(Player player){
        return CustomStack.byItemStack(player.getInventory().getItemInMainHand()) != null;
    }
}
