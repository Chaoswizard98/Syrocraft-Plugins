package syrocraft.titles.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.util.ParseTargetSelector;

public class SetTitleCommand implements CommandExecutor{
	Main plugin;
	public SetTitleCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if (commandLabel.equalsIgnoreCase("SetTitle")){//SetTitle <Player> <Title>
			if(args.length == 2){
				if(!ParseTargetSelector.GetPlayerList(theSender,args[0]).isEmpty()){
					Player p = ParseTargetSelector.GetPlayerList(theSender,args[0]).get(0);
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					File f = new File("plugins/SyrocraftMaster/Titles/","Titles.yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					if(cfg.contains("Titles."+args[1]+".Command")){
						String command = cfg.getString("Titles."+args[1]+".Command").replace("@player@", p.getName());
						Bukkit.dispatchCommand(console, command);
						theSender.sendMessage(ChatColor.GREEN + "Successfully set title.");
					}
					else{
						theSender.sendMessage(ChatColor.RED + "Title does not exist.");
					}
				}
				else{
					theSender.sendMessage(ChatColor.RED + "Invalid Player.");
				}
			}
			else{
				theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
			}
		}
		return true;
	}
}