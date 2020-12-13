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

public class CreateExpeditionTaskCommand implements CommandExecutor{
	Main plugin;
	public CreateExpeditionTaskCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("CreateExpeditionTask")){//CreateExpeditionTask <expedition> <Task> <Description>
				if(args.length >= 3){
					File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",args[0]+".yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					if(f.exists()){
						StringBuilder sb = new StringBuilder(args[2]);//combine arguments into one string
						for(int i = 3; i < args.length; i++){
							sb.append(' ');
							sb.append(args[i]);
						}
						String message = sb.toString();//our combined string

						cfg.set("Tasks."+args[1], message);
						try {
							cfg.save(f);//save the file
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						theSender.sendMessage(ChatColor.GREEN + "Successfully created expedition task.");
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