package stickkick;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements  CommandExecutor{
    boolean isEnabled = false;
    public String action = Actions.KICK;
    private static final String mainCmd = "/stickkick";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdName, String[] args) {
        if(!sender.hasPermission("skickkick.use")){
            sender.sendMessage("You dont have permission!");
            return true;
        }
        if (args.length == 0){
            return false;
        }
        switch (args[0]){
            case "help":
                sender.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "StickKick help:\n" +
                        ChatColor.RESET + "" + ChatColor.GRAY + mainCmd + " on" + ChatColor.RESET + " — активировать палку\n" +
                        ChatColor.RESET + "" + ChatColor.GRAY + mainCmd + " off" + ChatColor.RESET + " — деактивировать палку\n" +
                        ChatColor.RESET + "" + ChatColor.GRAY + mainCmd + " action <type>" + ChatColor.RESET + " — Выбрать дейтсвие\n" +
                        ChatColor.RESET + "" + ChatColor.RED + "" + ChatColor.BOLD + "Типы действий:\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.KICK + ChatColor.RESET + " — кикает при ударе игрока палкой\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.SPECTATOR + ChatColor.RESET + " — включает режим наблюдателя при ударе игрока палкой\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.KILL + ChatColor.RESET + " — убивает при ударе игрока палкой\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.BAN + ChatColor.RESET + " — банит при ударе игрока палкой\n" +
                        ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + Actions.BANIP + ChatColor.RESET + " — банит по " +
                        ChatColor.BOLD + "" + ChatColor.RED + "IP" + ChatColor.RESET + " при ударе игрока палкой\n");
                break;
            case "action":
                if (args.length < 2) {
                    this.action = Actions.KICK;
                    sender.sendMessage(ChatColor.GOLD + "Выбран тип действия по умолчанию: " +
                            ChatColor.RED + "<" + Actions.KICK + ">");
                } else {
                    switch (args[1]){
                        case Actions.KICK:
                            this.action = Actions.KICK;
                            this.isEnabled = true;
                            sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                                    ChatColor.RED + "<" + Actions.KICK + ">");
                            break;
                        case Actions.KILL:
                            this.action = Actions.KILL;
                            this.isEnabled = true;
                            sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                                    ChatColor.RED + "<" + Actions.KILL + ">");
                            break;
                        case Actions.SPECTATOR:
                            this.action = Actions.SPECTATOR;
                            this.isEnabled = true;
                            sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                                    ChatColor.RED + "<" + Actions.SPECTATOR + ">");
                            break;
                        case Actions.BAN:
                            this.action = Actions.BAN;
                            this.isEnabled = true;
                            sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                                    ChatColor.RED + "<" + Actions.BAN + ">");
                            break;
                        case Actions.BANIP:
                            this.action = Actions.BANIP;
                            this.isEnabled = true;
                            sender.sendMessage(ChatColor.GOLD + "Выбран тип действия: " +
                                    ChatColor.RED + "<" + Actions.BANIP + ">");
                            break;
                        default:
                            sender.sendMessage(ChatColor.DARK_RED + "Действие " + args[1] + " не существует.\n" +
                                    mainCmd + " help для подробностей.");
                            break;

                    }
                }
                break;
            case "on":
                this.isEnabled = true;
                sender.sendMessage(ChatColor.GOLD + "Палка активирована");
                break;
            case "off":
                this.isEnabled = false;
                sender.sendMessage(ChatColor.GOLD + "Палка деактивирована");
                break;
            default:
                return false;
        }
        return true;
    }
}
