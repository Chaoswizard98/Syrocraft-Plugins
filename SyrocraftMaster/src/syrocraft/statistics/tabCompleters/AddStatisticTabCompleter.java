package syrocraft.statistics.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class AddStatisticTabCompleter implements TabCompleter{
	Main plugin;
	public AddStatisticTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("AddStatistic")){//AddStatistic <Player> <Statistic> <Value>
				if(args.length == 1){
					return TabCompleterFunctions.getPlayerNamesList(args[0]);
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getSingleList("<Statistic>");
				}
				else if(args.length == 3){
					return TabCompleterFunctions.getSingleList("<Value>");
				}
				else if(args.length >= 4){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}

}
