package syrocraft.weaponEffects.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import syrocraft.Main;

public class PlayerInteractListener implements Listener{
	Main plugin;
	public PlayerInteractListener(Main pl){
		this.plugin = pl;
	}
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
	    Player p = event.getPlayer();
	    if(p.getInventory().getItemInMainHand() != null){
	    	if(p.getInventory().getItemInMainHand().getType()==Material.SNOWBALL){
	    		if((p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§bEnchanted Snowball"))){
	    			if((event.getAction().equals(Action.RIGHT_CLICK_AIR))||(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
	    				event.setCancelled(true); //cancel throwing
	    				p.launchProjectile(Snowball.class);
	    				p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 0.5f, 0.5f);
	    			}
	    		}
	    		if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§bMultishot Snowball")){
	    			if((event.getAction().equals(Action.RIGHT_CLICK_AIR))||(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
	    				p.launchProjectile(Snowball.class);
	    				p.launchProjectile(Snowball.class);
	    				p.launchProjectile(Snowball.class);
	    				p.launchProjectile(Snowball.class);
	    				p.launchProjectile(Snowball.class);
	    			}
	    		}
	    	}
	    }
	}
}
