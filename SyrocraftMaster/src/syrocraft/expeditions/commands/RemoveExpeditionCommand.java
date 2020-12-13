package syrocraft.expeditions.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import syrocraft.Main;

public class RemoveExpeditionCommand implements CommandExecutor{
	Main plugin;
	public RemoveExpeditionCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveExpedition")){//removeexpedition <expedition>
				if(args.length == 1){
					File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",args[0]+".yml");
					if(f.exists()){//if the file exists
						f.delete();
						theSender.sendMessage(ChatColor.GREEN + "Expedition removed successfully.");
					}
					else{
						theSender.sendMessage(ChatColor.RED + "Expedition does not exist.");
					}
				}
				else{
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}