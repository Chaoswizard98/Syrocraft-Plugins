package syrocraft.flags.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class TestFlagCommand implements CommandExecutor{
	Main plugin;
	public TestFlagCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("TestFlag")){//TestFlag <Flag> <command>
				if(args.length >= 2){
					File f = new File("plugins/SyrocraftMaster/Flags/Flags.yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					if(f.exists()){//does the file exist?
						if(cfg.contains("Flags."+args[0])){//does it contain this flag?
							if(cfg.getString("Flags."+args[0]).equalsIgnoreCase("True")){//is the flag true?
								//run the command
								StringBuilder sb = new StringBuilder(args[1]);//merge the remaining arguments into 1 string
								for(int i = 2; i < args.length; i++){
									sb.append(' ');
									sb.append(args[i]);
								}
								String command = sb.toString();//finalize the string
								Bukkit.dispatchCommand(theSender, command);//run command
							}
							else{
								theSender.sendMessage(ChatColor.GREEN + "Flag is not true.");
							}
						}
						else{
							theSender.sendMessage(ChatColor.GREEN + "Flag does not exist.");
						}
					}
					else{//invalid argument (Boolean Expected)
						theSender.sendMessage(ChatColor.RED + "File does not yet exist");
					}
				}
				else{//illegal argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}