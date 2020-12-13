package syrocraft.wishingWell.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class CreateDropTabCompleter implements TabCompleter{
	Main plugin;
	public CreateDropTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("CreateDrop")){//createdrop <droptable> <name> <rarity> <broadcast> <command>
				if(args.length == 1){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/DropTables"),args[0],"<Drop_Table>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/DropTables/"+args[0]+".yml"),"Drops",args[1],"<Drop_Name>");
				}
				else if(args.length == 3){
					return TabCompleterFunctions.getSingleList("<Rarity>");
				}
				else if(args.length == 4){
					return TabCompleterFunctions.getBooleanList(args[3]);
				}
				else if(args.length == 5){
					return TabCompleterFunctions.getSingleList("<Command>");
				}
				else if(args.length >= 6){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}
}