package syrocraft.statistics.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.util.NumberParser;
import syrocraft.util.ParseTargetSelector;

public class SetStatisticCommand implements CommandExecutor{
	Main plugin;
	public SetStatisticCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("SetStatistic")){//SetStatistic <Player> <Statistic> <Value>
				if(args.length == 3){
					if(NumberParser.isInt(args[2])){
						Player p = ParseTargetSelector.GetPlayerList(theSender,args[0]).get(0);
						if(p.isOnline()){
							File f = new File("plugins/SyrocraftMaster/Statistics/Users",p.getUniqueId().toString()+".yml");//create new file to store their stats
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
							cfg.set(args[1],Integer.parseInt(args[2]));
							try {
								cfg.save(f);//save the file
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							theSender.sendMessage(ChatColor.GREEN + "Statistic Updated.");
						}
						else{//player not found
							theSender.sendMessage(ChatColor.RED + "Player not found.");
						}
					}
					else{//invalid argument (Int Expected)
						theSender.sendMessage(ChatColor.RED + "Invalid argument. (Int Expected)");
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