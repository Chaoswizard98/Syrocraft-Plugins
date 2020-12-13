package syrocraft.titles.tabCompleters;

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

public class AddTitleRequirementTabCompleter implements TabCompleter{
	Main plugin;
	public AddTitleRequirementTabCompleter(Main pl){
		this.plugin = pl;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("AddTitleRequirement")){//AddTitleRequirement <Title> <Requirement Name> <Type> <Statistic> <Data> <Value> <Message> 
				if(args.length == 1){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/Titles/Titles.yml"),"Titles",args[0],"<Title>");
				}
				else if(args.length == 2){
					return TabCompleterFunctions.getFileSectionsList(("plugins/SyrocraftMaster/Titles/Titles.yml"),"Titles."+args[0]+".Requirements",args[1],"<Requirement_Name>");
				}
				else if(args.length == 3){
					ArrayList<String> argumentList = new ArrayList<String>();
					argumentList.add("Material");
					argumentList.add("Entity");
					argumentList.add("Other");
					argumentList.add("Custom");
					return TabCompleterFunctions.getCustomList(argumentList, args[2], "<Type>");
				}
				else if(args.length == 4){
					ArrayList<String> argumentList = new ArrayList<String>();
					for (Statistic c : Statistic.values()){
						argumentList.add(c.toString());
					}
					
					return TabCompleterFunctions.getCustomList(argumentList, args[3], "<Statistic>");
				}
				else if(args.length == 5){
					ArrayList<String> argumentList = new ArrayList<String>();
					if(args[2].equalsIgnoreCase("Material")){
						for (Material m : Material.values()){
							argumentList.add(m.toString());
						}
					}
					else if(args[2].equalsIgnoreCase("Entity")){
						for (EntityType e : EntityType.values()){
							
							argumentList.add(e.toString());
						}
					}
					else{
						argumentList.add("none");
					}
					return TabCompleterFunctions.getCustomList(argumentList, args[4], "<Data>");
				}
				else if(args.length == 6){
					return TabCompleterFunctions.getSingleList("<Value>");
				}
				else if(args.length >= 7){
					return TabCompleterFunctions.getSingleList("<Message>");
				}
			}
		}
		return null;
	}

}