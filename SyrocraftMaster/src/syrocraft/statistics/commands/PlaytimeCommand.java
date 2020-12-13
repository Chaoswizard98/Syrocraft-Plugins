package syrocraft.statistics.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.statistics.util.StatisticsUtil;
import syrocraft.util.ParseTargetSelector;

public class PlaytimeCommand implements CommandExecutor{
	Main plugin;
	public PlaytimeCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		Player p = null;
		if (commandLabel.equalsIgnoreCase("Playtime")){//Playtime [Player]
			if(args.length == 0){
				p = (Player) theSender;
			}
			else{
				if(theSender.isOp()){
					p = ParseTargetSelector.GetPlayerList(theSender,args[0]).get(0);
				}
				else{
					theSender.sendMessage(ChatColor.RED + "You do not have permission for this.");
					return true;
				}
			}
			if(p != null){
				StatisticsUtil.UpdatePlaytime(p);//Update players playtime statistic
				File f = new File("plugins/SyrocraftMaster/Statistics/Users",p.getUniqueId().toString()+".yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
				theSender.sendMessage(ChatColor.GRAY + "Your playtime is: " +cfg.getString("Playtime.Days")+ " Days, " +cfg.getString("Playtime.Hours")+ " Hours, " +cfg.getString("Playtime.Minutes")+ " Minutes, " +cfg.getString("Playtime.Seconds")+ " Seconds.");
			}
			else{//player argument is null
				theSender.sendMessage(ChatColor.RED + "Invalid Player.");
			}
		}
		return true;
	}
}