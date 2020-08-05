package ga.garifullin.stickkick;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemHandler {
    public static final String localizedName = "ga.garifullin.stickkick";

    public static void create(Player p, String action) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) item.setType(Material.STICK);
        ItemMeta meta = item.getItemMeta();

        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "StickKick");
        meta.setLocalizedName(localizedName);
        meta.setUnbreakable(true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(action);
        meta.setLore(lore);

        item.setItemMeta(meta);
        p.getInventory().setItemInMainHand(item);
    }
    public static String checkItemAndReturnLore(ItemStack item){
        if ( !(item.hasItemMeta()) ) return "none";
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLocalizedName() && meta.getLocalizedName().equals(localizedName)) {
            return meta.getLore().get(0);
        }
        return "none";
    }


}
