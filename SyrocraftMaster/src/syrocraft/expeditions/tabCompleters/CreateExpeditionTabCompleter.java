package syrocraft.expeditions.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class CreateExpeditionTabCompleter implements TabCompleter{
	Main plugin;
	public CreateExpeditionTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("CreateExpedition")){//createexpedition <Name>, <min>, <max>, <slots>, <min stardust>, <max stardust>, <min materials>, <max materials>, <drop table>, <materials drop table>
				if(args.length == 1){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/Expeditions/Tasks"),args[0],"<Expedition_Name>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getSingleList("<Min>");
				}
				else if(args.length == 3){
					return TabCompleterFunctions.getSingleList("<Max>");
				}
				else if(args.length == 4){
					return TabCompleterFunctions.getSingleList("<Slots>");
				}
				else if(args.length == 5){
					return TabCompleterFunctions.getSingleList("<Min_Stardust>");
				}
				else if(args.length == 6){
					return TabCompleterFunctions.getSingleList("<Max_Stardust>");
				}
				else if(args.length == 7){
					return TabCompleterFunctions.getSingleList("<Min_Materials>");
				}
				else if(args.length == 8){
					return TabCompleterFunctions.getSingleList("<Max_Materials>");
				}
				else if(args.length == 9){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/DropTables"),args[6],"<Drop_Table>");
				}
				else if(args.length == 10){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/DropTables"),args[6],"<Materials_Drop_Table>");
				}
				else if(args.length >= 11){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}

}
