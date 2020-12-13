package syrocraft.expeditions.commands;

import java.io.File;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.expeditions.util.ExpeditionsUtil;

public class ExpeditionsCommand implements CommandExecutor{
	Main plugin;
	public ExpeditionsCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(commandLabel.equalsIgnoreCase("Expeditions")){//display all expedition lists
			Player thePlayer = (Player) theSender;
			Set<String> expeditions;

			File fGlobal = new File("plugins/SyrocraftMaster/Expeditions/Global","Expeditions.yml");//get the file
			FileConfiguration cfgGlobal = YamlConfiguration.loadConfiguration(fGlobal);
			if(fGlobal.exists()){//check if the global order file exists or not
				if(cfgGlobal.get("Expeditions")!= null){
					expeditions = cfgGlobal.getConfigurationSection("Expeditions").getKeys(false);
					thePlayer.sendMessage(ChatColor.GOLD + "-----Expeditions-----");
					for(String i : expeditions){
						File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks/",i+".yml");//get the file
						if(f.exists()){//test if the file exists
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
							if(cfg.getString("Active").equalsIgnoreCase("true")){//if the expedition is active,
								int playerTaskCount = ExpeditionsUtil.GetExpeditionTaskCount(thePlayer,i);
								int availableSlots = Integer.parseInt(cfg.getString("Slots"));
								if(playerTaskCount < availableSlots){
									thePlayer.sendMessage(ChatColor.GRAY + "["+ChatColor.AQUA+cfg.getString("Name")+ChatColor.GRAY + " (" +ChatColor.GREEN+playerTaskCount+"/"+availableSlots+ChatColor.GRAY+ ")]");
								}
								else{
									thePlayer.sendMessage(ChatColor.GRAY + "["+ChatColor.AQUA+cfg.getString("Name")+ChatColor.GRAY + " (" +ChatColor.RED+playerTaskCount+"/"+availableSlots+ChatColor.GRAY+ ")]");
								}
							}
						}
					}
					thePlayer.sendMessage(ChatColor.GOLD + "--------------------");
				}
				else{
					thePlayer.sendMessage(ChatColor.RED + "Global expeditions file does not contain data.");
				}
			}
			else{
				thePlayer.sendMessage(ChatColor.RED + "Global expeditions file does not exist.");
			}
		}
		return true;
	}
}