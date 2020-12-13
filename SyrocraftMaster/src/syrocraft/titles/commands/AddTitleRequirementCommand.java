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
import syrocraft.util.NumberParser;

public class AddTitleRequirementCommand implements CommandExecutor{
	Main plugin;
	public AddTitleRequirementCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("AddTitleRequirement")){//AddTitleRequirement <Title> <Requirement Name> <Type> <Statistic> <Data> <Value> <Message> 
				if(args.length >= 7){
					if(NumberParser.isInt(args[5])){
						File f = new File("plugins/SyrocraftMaster/Titles/","Titles.yml");//create new file to store their stats
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

						cfg.set("Titles."+args[0]+".Requirements."+args[1]+".Type",args[2]);
						cfg.set("Titles."+args[0]+".Requirements."+args[1]+".Statistic",args[3]);
						cfg.set("Titles."+args[0]+".Requirements."+args[1]+".Data",args[4]);
						cfg.set("Titles."+args[0]+".Requirements."+args[1]+".Value",args[5]);

						StringBuilder sb = new StringBuilder(args[6]);//merge the remaining arguments into 1 string
						for(int i = 7; i < args.length; i++){
							sb.append(' ');
							sb.append(args[i]);
						}
						String message = sb.toString();//finalize the string
						cfg.set("Titles."+args[0]+".Requirements."+args[1]+".Message",message);

						try {
							cfg.save(f);//save the file
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theSender.sendMessage(ChatColor.GREEN + "Requirement Added.");
					}
					else{//illegal argument
						theSender.sendMessage(ChatColor.RED + "Invalid argument. (Int Expected)");
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