package syrocraft.titles.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class RemoveTitleTabCompleter implements TabCompleter{
	Main plugin;
	public RemoveTitleTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("RemoveTitle")){//RemoveTitle <Title>
				if(args.length == 1){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/Titles/Titles.yml"),"Titles",args[0],"<Title>");
				}
				else if(args.length >= 2){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}

}