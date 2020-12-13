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

public class CreateTitleCommand implements CommandExecutor{
	Main plugin;
	public CreateTitleCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("CreateTitle")){//CreateTitle <Title> <Command>
				if(args.length >= 2){
					File f = new File("plugins/SyrocraftMaster/Titles/","Titles.yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

					StringBuilder sb = new StringBuilder(args[1]);//merge the remaining arguments into 1 string
					for(int i = 2; i < args.length; i++){
						sb.append(' ');
						sb.append(args[i]);
					}
					String command = sb.toString();//finalize the string
					cfg.set("Titles."+args[0]+".Command",command);

					try {
						cfg.save(f);//save the file
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theSender.sendMessage(ChatColor.GREEN + "Successfully created title.");
				}
				else{//invalid argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}