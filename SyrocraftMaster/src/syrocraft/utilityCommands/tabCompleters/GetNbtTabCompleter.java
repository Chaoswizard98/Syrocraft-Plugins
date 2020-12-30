package syrocraft.utilityCommands.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class GetNbtTabCompleter implements TabCompleter{
	Main plugin;
	public GetNbtTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetNbt")){
				return TabCompleterFunctions.getSingleList("null");
			}
		}
		return null;
	}
}
