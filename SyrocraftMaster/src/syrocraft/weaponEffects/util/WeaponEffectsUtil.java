package syrocraft.weaponEffects.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WeaponEffectsUtil {
	public static boolean specialWeaponHit(ItemStack item, String itemName){
		if(hasValidItem(item)){
			if(item.getItemMeta().getDisplayName().equals(itemName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasValidItem(ItemStack i){
		if(i != null){
			if(i.getType() != (Material.AIR)){
				return true;
			}
			return false;
		}
		return false;
	}
}