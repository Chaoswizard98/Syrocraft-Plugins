package syrocraft.expeditions.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class EnableExpeditionTabCompleter implements TabCompleter{
	Main plugin;
	public EnableExpeditionTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("EnableExpedition")){//EnableExpedition <expedition>
				if(args.length == 1){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/Expeditions/Tasks"),args[0],"<Expedition>");
				}
				else if(args.length >= 2){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}

}