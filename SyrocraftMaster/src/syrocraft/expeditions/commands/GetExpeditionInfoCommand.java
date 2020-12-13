package syrocraft.expeditions.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.expeditions.util.ExpeditionsUtil;

public class GetExpeditionInfoCommand implements CommandExecutor{
	Main plugin;
	public GetExpeditionInfoCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if (commandLabel.equalsIgnoreCase("GetExpeditionInfo")){//GetExpeditionInfo <Page>
			int pageNumber = 1;
			int counter = 0;
			if(args.length == 1){
				pageNumber = Integer.parseInt(args[0]);
			}
			Player thePlayer = (Player) theSender;
			File f = new File("plugins/SyrocraftMaster/Expeditions/Users",thePlayer.getUniqueId()+".yml");//create new file to store their stats
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

			File globalf = new File("plugins/SyrocraftMaster/Expeditions/Global","Expeditions.yml");//create new file to store their stats
			FileConfiguration globalcfg = YamlConfiguration.loadConfiguration(globalf);

			thePlayer.sendMessage(ChatColor.GOLD + "-----Expedition Tasks-----");
			for(String i : globalcfg.getConfigurationSection("Expeditions").getKeys(false)){//loop through all expeditions
				if(cfg.getConfigurationSection("Expeditions."+i)!=null){
					for(String j : cfg.getConfigurationSection("Expeditions."+i).getKeys(false)){//loop through all tasks
						if((counter < (pageNumber*8))&&(counter >= ((pageNumber-1)*8))){
							thePlayer.sendMessage(ChatColor.AQUA + i + ": " + ChatColor.GRAY+ExpeditionsUtil.GetExpeditionDescription(i, cfg.getString("Expeditions."+i+"."+j+".Task_ID")));//Print Task
						}
						counter += 1;
					}
				}
			}
			thePlayer.sendMessage(ChatColor.GOLD + "Page "+pageNumber+" / "+ (int)Math.ceil(counter/8.0));
		}
		return true;
	}
}