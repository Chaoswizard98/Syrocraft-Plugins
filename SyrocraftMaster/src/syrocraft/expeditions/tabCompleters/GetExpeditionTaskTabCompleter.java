package syrocraft.expeditions.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class GetExpeditionTaskTabCompleter implements TabCompleter{
	Main plugin;
	public GetExpeditionTaskTabCompleter(Main pl){
		this.plugin = pl;
	}

	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetExpeditionTask")){//GetExpeditionTask <TaskID> <Player>
				if(args.length == 1){
					return TabCompleterFunctions.getSingleList("<Task_ID>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getPlayerNamesList(args[1]);
				}
				else if(args.length >= 3){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}
}