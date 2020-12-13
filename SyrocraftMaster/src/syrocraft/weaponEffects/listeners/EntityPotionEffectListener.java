package syrocraft.weaponEffects.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import syrocraft.Main;
import syrocraft.weaponEffects.util.WeaponEffectsUtil;

public class EntityPotionEffectListener implements Listener{
	Main plugin;
	public EntityPotionEffectListener(Main pl){
		this.plugin = pl;
	}

	@EventHandler
	public void onPotionEffect(EntityPotionEffectEvent e){
		if(e.getEntity() instanceof LivingEntity){
			ItemStack entityMainHand = ((LivingEntity) e.getEntity()).getEquipment().getItemInMainHand();
			ItemStack entityOffHand = ((LivingEntity) e.getEntity()).getEquipment().getItemInOffHand();
			if(e.getNewEffect()!=null){
				if(e.getNewEffect().getType().equals(PotionEffectType.POISON)){//if damage cause is poison
					if(WeaponEffectsUtil.specialWeaponHit(entityMainHand,"§2Detox Rapier")||WeaponEffectsUtil.specialWeaponHit(entityOffHand,"§2Detox Rapier")){//if entity has detox rapier
						e.setCancelled(true);
					}
				}
			}
		}
	}
}