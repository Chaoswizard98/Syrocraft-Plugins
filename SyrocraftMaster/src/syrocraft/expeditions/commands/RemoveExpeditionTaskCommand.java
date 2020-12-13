package syrocraft.expeditions.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class RemoveExpeditionTaskCommand implements CommandExecutor{
	Main plugin;
	public RemoveExpeditionTaskCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveExpeditionTask")){//removeexpedition <expedition>
				if(args.length == 2){
					File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",args[0]+".yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					if(f.exists()){
						cfg.set("Tasks."+args[1], null);
						try {
							cfg.save(f);//save the file
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theSender.sendMessage(ChatColor.GREEN + "Successfully removed expedition task.");
					}
					else{//file does not exist
						theSender.sendMessage(ChatColor.RED + "Expedition does not exist.");
					}
				}
				else{//invalid argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}