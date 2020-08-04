package stickkick;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class StickKickMain extends JavaPlugin {
    CommandHandler cmdHandler = new CommandHandler();
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new Handler(cmdHandler), this);
        this.getCommand("stickkick").setExecutor(cmdHandler);
        this.getCommand("stkick").setExecutor(cmdHandler);
        this.getCommand("stk").setExecutor(cmdHandler);
    }
}