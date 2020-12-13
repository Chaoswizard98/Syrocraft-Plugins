package syrocraft.weaponEffects.listeners;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import syrocraft.Main;
import syrocraft.weaponEffects.util.WeaponEffectsUtil;

public class DamageTakeListener implements Listener{
	Main plugin;
	public DamageTakeListener(Main pl){
		this.plugin = pl;
	}

	@EventHandler
	public void onDamageTake(EntityDamageEvent e){
		if(e.getEntity() instanceof LivingEntity){
			ItemStack entityMainHand = ((LivingEntity) e.getEntity()).getEquipment().getItemInMainHand();
			ItemStack entityOffHand = ((LivingEntity) e.getEntity()).getEquipment().getItemInOffHand();

			if(e.getEntity() instanceof Snowman){//frostie has decreased damage cooldown
				if(e.getEntity().getCustomName().equalsIgnoreCase("§bFrostie")){
					((LivingEntity) e.getEntity()).setNoDamageTicks(15);
				}
			}
			if(WeaponEffectsUtil.specialWeaponHit(entityMainHand,"§2Viking Blade")){//if entity has viking blade
				if(entityOffHand.getType().equals(Material.SHIELD)){//if they also have a shield
					e.setDamage(e.getDamage()*.8);//take 20% less damage
				}
			}
			if(WeaponEffectsUtil.specialWeaponHit(entityMainHand,"§2Detox Rapier")||WeaponEffectsUtil.specialWeaponHit(entityOffHand,"§2Detox Rapier")){//if entity has detox rapier
				((LivingEntity) e.getEntity()).removePotionEffect(PotionEffectType.POISON);
			}
			if(WeaponEffectsUtil.specialWeaponHit(entityMainHand,"§cHellfire")||WeaponEffectsUtil.specialWeaponHit(entityOffHand,"§cHellfire")){//if entity has hellfire
				if(e.getCause().equals(DamageCause.FIRE)||e.getCause().equals(DamageCause.FIRE_TICK)){
					e.setCancelled(true);
				}
			}
		}
	}
}
