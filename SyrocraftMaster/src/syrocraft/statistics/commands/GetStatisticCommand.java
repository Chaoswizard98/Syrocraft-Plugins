package syrocraft.statistics.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.statistics.util.StatisticsUtil;

public class GetStatisticCommand implements CommandExecutor{
	Main plugin;
	public GetStatisticCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetStatistic")){//GetStatistic <Type> <Statistic> <Data>
				if(args.length == 3){
					Player p = (Player) theSender;
					if(p.isOnline()){
						theSender.sendMessage(ChatColor.GRAY + Integer.toString(StatisticsUtil.GetStatistic(p, args[0], args[1], args[2])));//<Player> <Type> <Statistic> <Data>
					}
					else{//player not found
						theSender.sendMessage(ChatColor.RED + "Player not found.");
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