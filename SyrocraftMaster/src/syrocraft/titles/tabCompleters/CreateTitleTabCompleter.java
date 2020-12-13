package syrocraft.titles.tabCompleters;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class CreateTitleTabCompleter implements TabCompleter{
	Main plugin;
	public CreateTitleTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("CreateTitle")){//CreateTitle <Title> <Command>
				if(args.length == 1){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/Titles/Titles.yml"),"Titles",args[0],"<Title>");
				}
				else if(args.length >= 2){
					return TabCompleterFunctions.getSingleList("<Command>");
				}
			}
		}
		return null;
	}

}