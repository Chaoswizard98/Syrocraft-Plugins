package syrocraft.statistics.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import syrocraft.Main;
import syrocraft.statistics.util.StatisticsUtil;

public class LoginEventListener implements Listener{
	Main plugin;
	public LoginEventListener(Main pl){
		this.plugin = pl;
	}
	
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();//The player logging in
		StatisticsUtil.SetLoginStatistic(p);//sets their login time
		
		//=======================
		//Unclaimed Vote Rewards=
		//=======================
		if(StatisticsUtil.HasUnclaimedVoteRewards(p)){//if the player has offline votes, let them know
			p.sendMessage(ChatColor.GOLD + "You have " + ChatColor.GREEN + "unclaimed vote rewards" + ChatColor.GOLD + ". Use " + ChatColor.GREEN +  "/ClaimVoteRewards" + ChatColor.GOLD + " to claim them.");
		}
		
		//============================
		//Seasonal Event Achievements=
		//============================
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		File f = new File("plugins/SyrocraftMaster/Flags/Flags.yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		if(f.exists()){
			if(cfg.contains("Flags.Halloween")){
				if(cfg.getString("Flags.Halloween").equalsIgnoreCase("True")){
					Bukkit.dispatchCommand(console, "advancement grant " + p.getName()+" only syrocraft:halloween/root");
					p.sendMessage(ChatColor.GOLD + "The " + ChatColor.GREEN + "Halloween Event" + ChatColor.GOLD + " is active! Visit the portal at spawn to begin!");
				}
			}
			if(cfg.contains("Flags.Easter")){
				if(cfg.getString("Flags.Easter").equalsIgnoreCase("True")){
					Bukkit.dispatchCommand(console, "advancement grant " + p.getName()+" only syrocraft:easter/root");
					p.sendMessage(ChatColor.GOLD + "The " + ChatColor.GREEN + "Easter Event" + ChatColor.GOLD + " is active! Visit the portal at spawn to begin!");
				}
			}
		}
    }
}