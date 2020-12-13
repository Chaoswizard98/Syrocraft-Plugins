package syrocraft.weaponEffects.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.inventory.ItemStack;

import syrocraft.Main;
import syrocraft.weaponEffects.util.WeaponEffectsUtil;

public class EntityCombustListener implements Listener{
	Main plugin;
	public EntityCombustListener(Main pl){
		this.plugin = pl;
	}

	@EventHandler
	public void onEntityCombust(EntityCombustEvent e){
		if(e.getEntity() instanceof LivingEntity){
			ItemStack entityMainHand = ((LivingEntity) e.getEntity()).getEquipment().getItemInMainHand();
			ItemStack entityOffHand = ((LivingEntity) e.getEntity()).getEquipment().getItemInOffHand();
			if(WeaponEffectsUtil.specialWeaponHit(entityMainHand,"§cHellfire")||WeaponEffectsUtil.specialWeaponHit(entityOffHand,"§cHellfire")){//if entity has hellfire
				e.setCancelled(true);
			}
		}
	}
}