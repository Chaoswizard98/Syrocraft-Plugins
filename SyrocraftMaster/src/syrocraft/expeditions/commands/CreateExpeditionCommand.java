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
import syrocraft.util.NumberParser;

public class CreateExpeditionCommand implements CommandExecutor{
	Main plugin;
	public CreateExpeditionCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("CreateExpedition")){//createexpedition Name, min, max, slots, min stardust, max stardust, drop table
				if(args.length == 10){
					if(NumberParser.isInt(args[1])&&NumberParser.isInt(args[2])&&NumberParser.isInt(args[3])&&NumberParser.isInt(args[4])&&NumberParser.isInt(args[5])&&NumberParser.isInt(args[6])&&NumberParser.isInt(args[7])){
						File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",args[0]+".yml");//create new file to store their stats
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

						cfg.set("Name",args[0]);
						cfg.set("Min", args[1]);
						cfg.set("Max", args[2]);
						cfg.set("Slots", args[3]);
						cfg.set("Min_Stardust", args[4]);
						cfg.set("Max_Stardust", args[5]);
						cfg.set("Min_Materials", args[6]);
						cfg.set("Max_Materials", args[7]);
						cfg.set("Drop_Table", args[8]);
						cfg.set("Materials_Drop_Table", args[9]);
						
						cfg.set("Active", "true");
						try {
							cfg.save(f);//save the file
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theSender.sendMessage(ChatColor.GREEN + "Successfully created expedition.");
					}
					else{
						theSender.sendMessage(ChatColor.RED + "Invalid Argument. (Int expected)");
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