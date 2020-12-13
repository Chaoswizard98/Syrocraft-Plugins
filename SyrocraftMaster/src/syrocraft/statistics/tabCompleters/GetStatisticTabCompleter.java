package syrocraft.statistics.tabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import syrocraft.Main;
import syrocraft.util.TabCompleterFunctions;

public class GetStatisticTabCompleter implements TabCompleter{
	Main plugin;
	public GetStatisticTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetStatistic")){//GetStatistic <Type> <Statistic> <Data> [Player]
				if(args.length == 1){
					ArrayList<String> argumentList = new ArrayList<String>();
					argumentList.add("Material");
					argumentList.add("Entity");
					argumentList.add("Other");
					argumentList.add("Custom");
					return TabCompleterFunctions.getCustomList(argumentList, args[0], "<Type>");
				}
				else if(args.length == 2){
					ArrayList<String> argumentList = new ArrayList<String>();
					for (Statistic c : Statistic.values()){
						argumentList.add(c.toString());
					}
					
					return TabCompleterFunctions.getCustomList(argumentList, args[1], "<Statistic>");
				}
				else if(args.length == 3){
					ArrayList<String> argumentList = new ArrayList<String>();
					if(args[0].equalsIgnoreCase("Material")){
						for (Material m : Material.values()){
							argumentList.add(m.toString());
						}
					}
					else if(args[0].equalsIgnoreCase("Entity")){
						for (EntityType e : EntityType.values()){
							
							argumentList.add(e.toString());
						}
					}
					else{
						argumentList.add("none");
					}
					return TabCompleterFunctions.getCustomList(argumentList, args[2], "<Data>");
				}
				else if(args.length >= 4){
					return TabCompleterFunctions.getSingleList("null");
				}
			}
		}
		return null;
	}
}