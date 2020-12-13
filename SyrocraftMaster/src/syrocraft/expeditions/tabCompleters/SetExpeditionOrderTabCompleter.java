package syrocraft.expeditions.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class SetExpeditionOrderTabCompleter implements TabCompleter{
	Main plugin;
	public SetExpeditionOrderTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("SetExpeditionOrder")){//SetExpeditionOrder <Name1>, <Name2> ...<Name X>
				if(args.length > 0){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/Expeditions/Tasks"),args[args.length-1],"<Expedition_Name>");
				}
			}
		}
		return null;
	}
}