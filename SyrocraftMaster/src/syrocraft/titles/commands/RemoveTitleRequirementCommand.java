package syrocraft.titles.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class RemoveTitleRequirementCommand implements CommandExecutor{
	Main plugin;
	public RemoveTitleRequirementCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveTitleRequirement")){//RemoveTitleRequirement <Title> <Requirement>
				if(args.length == 2){
					File f = new File("plugins/SyrocraftMaster/Titles/","Titles.yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					cfg.set("Titles."+args[0]+".Requirements."+args[1], null);
					try {
						cfg.save(f);//save the file
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theSender.sendMessage(ChatColor.GREEN + "Successfully removed title requirement.");
				}
				else{//invalid argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}