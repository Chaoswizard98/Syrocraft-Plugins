package syrocraft.expeditions.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class GetExpeditionInfoTabCompleter implements TabCompleter{
	Main plugin;
	public GetExpeditionInfoTabCompleter(Main pl){
		this.plugin = pl;
	}

	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if (commandLabel.equalsIgnoreCase("GetExpeditionInfo")){//GetExpeditionInfo [Page Number]
			if(args.length == 1){
				return TabCompleterFunctions.getSingleList("[Page Number]");
			}
			else if(args.length >= 2){
				return TabCompleterFunctions.getSingleList("null");
			}
		}
		return null;
	}
}