package syrocraft.flags.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class SetFlagCommand implements CommandExecutor{
	Main plugin;
	public SetFlagCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("SetFlag")){//SetFlag <Flag> <Value>
				if(args.length == 2){
					if(args[1].equalsIgnoreCase("True")||args[1].equalsIgnoreCase("False")){
						File f = new File("plugins/SyrocraftMaster/Flags/Flags.yml");//create new file to store flags
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
						cfg.set("Flags."+args[0],args[1]);
						try {
							cfg.save(f);//save the file
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theSender.sendMessage(ChatColor.GREEN + "Flag Set.");
					}
					else{//invalid argument (Boolean Expected)
						theSender.sendMessage(ChatColor.RED + "Invalid argument. (True/False Expected)");
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
