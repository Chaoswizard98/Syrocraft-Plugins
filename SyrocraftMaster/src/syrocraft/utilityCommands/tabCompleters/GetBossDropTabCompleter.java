package syrocraft.utilityCommands.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class GetBossDropTabCompleter implements TabCompleter{
	Main plugin;
	public GetBossDropTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetBossDrop")){//GetBossDrop <player> <Drop Table> <Statistic> <Radius> <Advancement Name> <Trigger>
				if(args.length == 1){
					return TabCompleterFunctions.getPlayerNamesList(args[0]);
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/DropTables"),args[1],"<Drop_Table>");
				}
				else if(args.length == 3){
					return TabCompleterFunctions.getSingleList("<Statistic>");
				}
				else if(args.length == 4){
					return TabCompleterFunctions.getSingleList("<Radius>");
				}
				else if(args.length == 5){
					return TabCompleterFunctions.getSingleList("<Advancement Name>");
				}
				else if(args.length == 6){
					return TabCompleterFunctions.getSingleList("<Trigger>");
				}
				else if(args.length >= 7){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}
}