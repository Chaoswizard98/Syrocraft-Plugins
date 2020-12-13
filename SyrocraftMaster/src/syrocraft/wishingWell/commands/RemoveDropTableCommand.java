package syrocraft.wishingWell.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import syrocraft.Main;

public class RemoveDropTableCommand implements CommandExecutor{
	Main plugin;
	public RemoveDropTableCommand(Main pl){
		this.plugin = pl;
	}
	
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveDropTable")){//removedroptable <droptable>
				if(args.length == 1){
					File f = new File("plugins/SyrocraftMaster/DropTables",args[0]+".yml");//create new file to store their stats
					if(f.exists()){//if the drop table exists
						f.delete();
						theSender.sendMessage(ChatColor.GREEN + "Drop table removed successfully.");
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