package ga.garifullin.stickkick;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandHandler implements  CommandExecutor{
    private static final String mainCmd = "/stickkick";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdName, String[] args) {
        if(!sender.hasPermission("ga.garifullin.stickkick.create")){
            sender.sendMessage("You dont have permission to use this command!");
            return true;
        }
        if (args.length == 0){
            return false;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage("Only for players!");
            return true;
        }
        switch (args[0]){
            case "help":
                sender.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "StickKick help:\n" +
                        ChatColor.RESET + "" + ChatColor.GRAY + mainCmd + " remove" + ChatColor.RESET + " — Удалить 'палку' (держите 'палку' в руке)\n" +
                        ChatColor.RESET + "" + ChatColor.GRAY + mainCmd + " <action type>" + ChatColor.RESET + " — Выбрать действие\n" +
                        ChatColor.RESET + "" + ChatColor.RED + "" + ChatColor.BOLD + "Типы действий:\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.KICK + ChatColor.RESET + " — кикает при ударе игрока 'палкой'\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.SPECTATOR + ChatColor.RESET + " — включает режим наблюдателя при ударе игрока 'палкой'\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.KILL + ChatColor.RESET + " — убивает при ударе игрока 'палкой'\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.BAN + ChatColor.RESET + " — банит при ударе игрока 'палкой'\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.BANIP + ChatColor.RESET + " — банит по " +
                        ChatColor.BOLD + "" + ChatColor.RED + "IP" + ChatColor.RESET + " при ударе игрока 'палкой'\n");
                break;
            case "remove":
                ItemMeta meta = ((Player) sender).getInventory().getItemInMainHand().getItemMeta();
                if ( (meta == null) || (meta.getLocalizedName() == null) || !(meta.getLocalizedName().equals("ga.garifullin.stickkick")) ){
                    sender.sendMessage(ChatColor.GOLD + "Вы должны держать в руке " + ChatColor.GREEN + "" + ChatColor.BOLD +
                            "StickKick" + ChatColor.RESET + "" + ChatColor.GOLD + " 'палку'");
                    return true;
                }
                ((Player) sender).getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                sender.sendMessage(ChatColor.GOLD + "'Палка' была удалена");
                break;
            case Actions.KICK:
                ItemHandler.create((Player) sender, Actions.KICK);
                sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                        ChatColor.RED + "<" + Actions.KICK + ">");
                break;
            case Actions.KILL:
                ItemHandler.create((Player) sender, Actions.KILL);
                sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                        ChatColor.RED + "<" + Actions.KILL + ">");
                break;
            case Actions.SPECTATOR:
                ItemHandler.create((Player) sender, Actions.SPECTATOR);
                sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                        ChatColor.RED + "<" + Actions.SPECTATOR + ">");
                break;
            case Actions.BAN:
                ItemHandler.create((Player) sender, Actions.BAN);
                sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                        ChatColor.RED + "<" + Actions.BAN + ">");
                break;
            case Actions.BANIP:
                ItemHandler.create((Player) sender, Actions.BANIP);
                sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                        ChatColor.RED + "<" + Actions.BANIP + ">");
                break;
            default:
                return false;
        }
        return true;
    }
}
