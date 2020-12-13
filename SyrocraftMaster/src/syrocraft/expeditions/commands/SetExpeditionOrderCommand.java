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

public class SetExpeditionOrderCommand implements CommandExecutor{
	Main plugin;
	public SetExpeditionOrderCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("SetExpeditionOrder")){//set the order of the expeditions in the list
				if(args.length >= 1){
					File f = new File("plugins/SyrocraftMaster/Expeditions/Global","Expeditions.yml");//get the file
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

					cfg.set("Expeditions", null);
					for(int i = 0; i<args.length; i += 1){
						cfg.set("Expeditions."+args[i], "");
					}

					try {
						cfg.save(f);//save the file
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theSender.sendMessage(ChatColor.GREEN + "Successfully set expedition order.");
				}
				else{
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}
