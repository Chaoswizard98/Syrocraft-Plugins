package syrocraft.weaponEffects.listeners;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import syrocraft.Main;

public class ProjectileHitListener implements Listener{
	Main plugin;
	public ProjectileHitListener(Main pl){
		this.plugin = pl;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e){
		if(e.getEntity() instanceof Snowball){
			if(e.getHitEntity() instanceof Player){
				if(((LivingEntity) e.getHitEntity()).getNoDamageTicks() == 0){
					e.getHitEntity().playEffect(EntityEffect.HURT);
					((LivingEntity) e.getHitEntity()).setNoDamageTicks(7);//10 is default MC, sped up for lols
					Vector v = e.getEntity().getVelocity().multiply(0.6);
					v.setY(.4);
					e.getHitEntity().setVelocity(v);
				}
			}
			if(e.getHitEntity() instanceof Snowman){
				if(e.getHitEntity().getCustomName().equalsIgnoreCase("§bFrostie")){
					((Damageable) e.getHitEntity()).damage(5);
				}
			}
		}
	}
}
