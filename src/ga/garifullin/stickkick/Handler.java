package ga.garifullin.stickkick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Handler implements Listener {
    @EventHandler
    public void stickHit(EntityDamageByEntityEvent e){
        if((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)){
            Player admin = (Player) e.getDamager();
            Player daun = (Player) e.getEntity();
            String itemlore = ItemHandler.checkItemAndReturnLore(admin.getInventory().getItemInMainHand());
            if( !(itemlore.equals("none")) && admin.hasPermission("ga.garifullin.stickkick.use")){
                e.setCancelled(true);
                switch (itemlore) {
                    case Actions.KICK:
                       daun.kickPlayer(ChatColor.RED + "You kicked!");
                       admin.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был кикнут");
                       break;
                    case Actions.KILL:
                        daun.setHealth(0);
                        admin.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был убит");
                        break;
                    case Actions.SPECTATOR:
                        daun.setGameMode(GameMode.SPECTATOR);
                        admin.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" стал наблюдателем");
                        break;
                    case Actions.BAN:
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + daun.getName() + " Ban((");
                        admin.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был забанен");
                        break;
                    case Actions.BANIP:
                        String ip = daun.getAddress().getHostName();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + daun.getName() + " You got banned by IP((");
                        Bukkit.banIP(ip);
                        admin.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был забанен");
                        admin.sendMessage(ChatColor.GOLD + ip + ChatColor.RED +" IP адрес был забанен");
                        break;
                }
            }
        }
    }

}
