package syrocraft.flags.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class TestFlagTabCompleter  implements TabCompleter{
	Main plugin;
	public TestFlagTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("TestFlag")){//testflag <flag> <command>
				if(args.length == 1){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/Flags/Flags.yml"),"Flags",args[0],"<Flag>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getSingleList("<Command>");
				}
				else if(args.length >= 3){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}
}