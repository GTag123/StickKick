package ga.garifullin.stickkick;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;


public class StickKickMain extends JavaPlugin {
    CommandHandler cmdHandler = new CommandHandler();
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new Handler(), this);
        this.getCommand("stickkick").setExecutor(cmdHandler);
        this.getCommand("stkick").setExecutor(cmdHandler);
        this.getCommand("stk").setExecutor(cmdHandler);
        registerGlow();
    }
    public void registerGlow() { // don't touch pls (*_*)
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Glow glow = new Glow(70);
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e){
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}