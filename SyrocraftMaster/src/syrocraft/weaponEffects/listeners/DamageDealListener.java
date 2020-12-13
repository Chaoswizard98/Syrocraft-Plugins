package syrocraft.weaponEffects.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import syrocraft.Main;
import syrocraft.util.MobFunctions;
import syrocraft.weaponEffects.util.WeaponEffectsUtil;

public class DamageDealListener implements Listener{
	Main plugin;
	public DamageDealListener(Main pl){
		this.plugin = pl;
	}
	
	//===============
	//Custom Weapons=
	//===============
	@EventHandler
	public void onDamageDeal(EntityDamageByEntityEvent e){
		if(e.getCause().equals(DamageCause.ENTITY_ATTACK)){
			if(e.getDamager() instanceof LivingEntity){
				ItemStack damagerMainHand = ((LivingEntity) e.getDamager()).getEquipment().getItemInMainHand();
				ItemStack damagerOffHand = ((LivingEntity) e.getDamager()).getEquipment().getItemInOffHand();
				if(WeaponEffectsUtil.specialWeaponHit(damagerOffHand,"§2Amulet of the Spider")){//if entity attacks with amulet in off hand,
					if(Math.random() <= .1){//10% proc chance
						((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 6));
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§2Amulet of the Spider")){//if entity attacks with amulet in main hand,
					if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
						((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
						((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 6));
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerOffHand,"§8Spider's Web")){//(Boss only item)
					if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
						if(e.getFinalDamage() == 0){//if we block with a shield, cancel the effect
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.GLOWING);
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.SPEED);
						}
						else{//otherwise, slow the target
							((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 6));
						}
						
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§8Olgrith's Club")){//(Boss only item)
					if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
						if(e.getFinalDamage() == 0){//if we block with a shield, cancel the effect
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.GLOWING);
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.SPEED);
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
						}
						else{//otherwise, slow the target
							((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 6));
						}
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerOffHand,"§cAmulet of the Strength")){//if entity attacks with amulet in off hand,
					if(Math.random() <= .1){//10% proc chance
						e.setDamage(e.getDamage()*1.25);
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§6Sword of Light")){//if entity attacks with sword of light,
					if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
						((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
						double healAmmount = e.getDamage()/2;
						double maxHeal = (((LivingEntity) e.getDamager()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() - ((LivingEntity) e.getDamager()).getHealth());
						if(healAmmount > maxHeal){
							healAmmount = maxHeal;
						}
						((LivingEntity) e.getDamager()).setHealth(((LivingEntity) e.getDamager()).getHealth() + healAmmount);
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§2Toxic Blade")){//if entity attacks with toxic blade,
					if(e.getFinalDamage() > 0){//if we block with a shield, dont perform the effect
						((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2));
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§8Crippling Blade")){//if entity attacks with crippling blade,
					if(e.getFinalDamage() > 0){//if we block with a shield, dont perform the effect
						((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2));
						if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
							((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 6));
						}
					}
					else{
						((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§5Night Terror Blade §7(e)")){//if entity attacks with crippling blade,
					if(e.getFinalDamage() > 0){//if we block with a shield, dont perform the effect
						if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
							((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 2));
						}
					}
					else{
						((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§8Withering Blade")){//if entity attacks with withering blade,
					if(e.getFinalDamage() > 0){//if we block with a shield, dont perform the effect
						((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 2));
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§6Holy Blade")){//if entity attacks with holy blade,
					if(MobFunctions.isUndead(((LivingEntity) e.getEntity()))){
						if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
							e.setDamage(e.getDamage()*2);
						}
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§cDoomskull's Blade")){//if entity attacks with doomskull's blade,
					if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
						((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
						((LivingEntity) e.getEntity()).damage(e.getDamage());//deal base damage as extra armor bypassing damage (does not bypass resistance potion)
						e.setDamage(1);
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§3Thief's Dagger")){//if entity attacks with thief's dagger,
					if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
						((LivingEntity) e.getEntity()).damage(e.getDamage());//deal base damage as extra armor bypassing damage (does not bypass resistance potion)
						e.setDamage(1);
					}
				}
				if(WeaponEffectsUtil.specialWeaponHit(damagerMainHand,"§1Latrodectus")){//if entity attacks with latrodectus,
					if(MobFunctions.isArthropod(((LivingEntity) e.getEntity()))){
						if(((LivingEntity) e.getDamager()).hasPotionEffect(PotionEffectType.LUCK)){
							((LivingEntity) e.getDamager()).removePotionEffect(PotionEffectType.LUCK);
							for(Entity entity : e.getEntity().getNearbyEntities(10, 10, 10)){
								if(entity instanceof LivingEntity){
									if(MobFunctions.isArthropod((LivingEntity) entity)){
										((Damageable) entity).damage(e.getDamage());
									}
								}
							}
							e.setDamage(e.getDamage()*2);
						}
					}
				}
			}
		}
	}
}