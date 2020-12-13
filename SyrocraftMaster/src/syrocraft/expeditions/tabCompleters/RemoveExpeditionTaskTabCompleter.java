package syrocraft.expeditions.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class RemoveExpeditionTaskTabCompleter implements TabCompleter{
	Main plugin;
	public RemoveExpeditionTaskTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveExpeditionTask")){//RemoveExpeditionTask <expedition> <Task>
				if(args.length == 1){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/Expeditions/Tasks"),args[0],"<Expedition>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/Expeditions/Tasks/"+args[0]+".yml"),"Tasks",args[1],"<Task>");
				}
				else if(args.length >= 3){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}

}