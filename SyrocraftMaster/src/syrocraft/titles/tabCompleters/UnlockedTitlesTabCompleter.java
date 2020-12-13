package syrocraft.titles.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class UnlockedTitlesTabCompleter implements TabCompleter{
	Main plugin;
	public UnlockedTitlesTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
			if (commandLabel.equalsIgnoreCase("UnlockedTitles")){//UnlockedTitles [Page]
				if(args.length == 1){
					return TabCompleterFunctions.getSingleList("[Page]");
				}
				else if(args.length >= 2){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		return null;
	}
}