package syrocraft.wishingWell.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class CreateDropTableTabCompleter implements TabCompleter{
	Main plugin;
	public CreateDropTableTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("CreateDropTable")){//setraredropmessage <droptable> <message>
				if(args.length == 1){
					return TabCompleterFunctions.getFileNamesList(("plugins/SyrocraftMaster/DropTables"),args[0],"<Drop_Table>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getSingleList("<Message>");
				}
				else if(args.length >= 3){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}
}