package syrocraft.statistics.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import syrocraft.Main;
import syrocraft.statistics.util.StatisticsUtil;

public class LogoutEventListener implements Listener{
	Main plugin;
	public LogoutEventListener(Main pl){
		this.plugin = pl;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		StatisticsUtil.UpdatePlaytime(e.getPlayer());//Update players playtime statistic
		StatisticsUtil.UpdateSeenList(e.getPlayer());//Update seen list
	}
}