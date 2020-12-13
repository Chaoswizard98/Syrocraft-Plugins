package syrocraft.wishingWell.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class RemoveDropCommand implements CommandExecutor{
	Main plugin;
	public RemoveDropCommand(Main pl){
		this.plugin = pl;
	}
	
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveDrop")){//removedrop <droptable> <drop>
				if(args.length >= 2){//make sure we have the correct number of arguments
					File f = new File("plugins/SyrocraftMaster/DropTables",args[0]+".yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					if(f.exists()){//if theh drop table exists
						cfg.set("Drops."+args[1],null);//remove the drop
						try {
							cfg.save(f);//save the file
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theSender.sendMessage(ChatColor.GREEN + "Drop removed successfully.");
					}
					else{
						theSender.sendMessage(ChatColor.RED + "Drop table does not exist.");
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
