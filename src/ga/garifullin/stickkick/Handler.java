package ga.garifullin.stickkick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Handler implements Listener {
    @EventHandler
    public void stickHit(EntityDamageByEntityEvent e){
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();

        if ((damager instanceof Player) || ( (damager instanceof Projectile) && (((Projectile) damager).getShooter() instanceof Player) )) {
            Player punisher = getPunisher(damager);

            if (entity instanceof Player){
                Player daun = (Player) entity;
                String itemlore = ItemHandler.checkItemAndReturnLore(punisher.getInventory().getItemInMainHand());
                if( !(itemlore.equals("none")) && punisher.hasPermission("ga.garifullin.stickkick.use")){

                    if (daun.getName().equals(punisher.getName())) {
                        punisher.sendMessage(ChatColor.GOLD + "Не используй на себе!");
                        return;
                    }
                    
                    e.setCancelled(true);
                    switch (itemlore) {
                        case Actions.KICK:
                            daun.kickPlayer(ChatColor.RED + "You kicked!");
                            punisher.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был кикнут");
                            break;
                        case Actions.KILL:
                            daun.setHealth(0);
                            punisher.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был убит");
                            break;
                        case Actions.SPECTATOR:
                            daun.setGameMode(GameMode.SPECTATOR);
                            punisher.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" стал наблюдателем");
                            break;
                        case Actions.BAN:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + daun.getName() + " Ban((");
                            punisher.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был забанен");
                            break;
                        case Actions.BANIP:
                            String ip = daun.getAddress().getHostName();
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + daun.getName() + " You got banned by IP((");
                            Bukkit.banIP(ip);
                            punisher.sendMessage(ChatColor.GOLD + daun.getName() + ChatColor.RED +" был забанен");
                            punisher.sendMessage(ChatColor.GOLD + ip + ChatColor.RED +" IP адрес был забанен");
                            break;
                    }
                }
            } else if (entity instanceof LivingEntity){
                if(ItemHandler.checkItemAndReturnLore(punisher.getInventory().getItemInMainHand()).equals("kill")) {
                    e.setCancelled(true);
                    ((LivingEntity) entity).setHealth(0);
                }
            }
        }
    }

    private static Player getPunisher(Entity damager){ // do not use!!! because code in else block may cause an error if it is not used correctly!!!! Error may be during casting to Player type
        if (damager instanceof Player) {
             return (Player) damager;
        } else {
            return (Player) ((Projectile) damager).getShooter();
        }
    }
}
