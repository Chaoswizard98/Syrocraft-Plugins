package syrocraft.wishingWell.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class RemoveDropTabCompleter implements TabCompleter{
	Main plugin;
	public RemoveDropTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveDrop")){//Removedrop <Drop_Table> <Drop_Name>
				if(args.length == 1){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/DropTables"),args[0],"<Drop_Table>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/DropTables/"+args[0]+".yml"),"Drops",args[1],"<Drop_Name>");
				}
				else if(args.length >= 3){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}

}