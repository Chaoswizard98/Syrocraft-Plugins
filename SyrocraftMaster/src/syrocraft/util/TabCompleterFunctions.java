package syrocraft.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin; 

public class TabCompleterFunctions extends JavaPlugin{
	//========================
	//Get List of File Names =
	//========================
	public static List<String> getFileNamesList(String pathname, String args, String defaultName){
		ArrayList<String> fileNames = new ArrayList<String>();
		if(new File(pathname).exists()){//if the folder exists,
			for (File file : new File(pathname).listFiles()){//loop through all files
				int pos = file.getName().lastIndexOf('.');//find position of the extension
				if(pos == -1){//if the file doesnt have an extension (very unlikely)
					pos = file.getName().length()-1;//set it to the last position
				}
				if(file.getName().substring(0,pos).startsWith(args)){//if the file starts with our current argument
					fileNames.add(file.getName().substring(0,pos));//add it to the list (and remove the extension)
				}
			}
		}
		if(fileNames.isEmpty()){//if the list is empty
			fileNames.add(defaultName);//add default name (so we know what the argument should be)
		}
		else{//otherwise, sort the list alphabetically
			Collections.sort(fileNames);
		}
		return fileNames;
	}
	
	//===========================
	//Get List of File Sections =
	//===========================
	public static List<String> getFileSectionsList(String pathname, String section, String args, String defaultName){
		
		File f = new File(pathname);//get the drop table file
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		ArrayList<String> fileNames = new ArrayList<String>();
		
		if(f.exists()){
			if(cfg.contains(section)){
			Set<String> dropList = cfg.getConfigurationSection(section).getKeys(false);
			for(String i : dropList){//loop through drop list
				if(i.startsWith(args)){
					fileNames.add(i);
				}
			}
			}
		}
		if(fileNames.isEmpty()){//if the list is empty
			fileNames.add(defaultName);//add the default name
		}
		return fileNames;
	}
	
	//============================
	//Get List of Online Players =
	//============================
	public static List<String> getPlayerNamesList(String args){
		ArrayList<String> players = new ArrayList<String>();
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.getName().startsWith(args)){
				players.add(p.getName());
			}
		}
		Collections.sort(players);
		return players;
	}
	
	//==============================
	//Get List of Custom Arguments =
	//==============================
	public static List<String> getCustomList(ArrayList<String> argumentList, String args, String name){
		ArrayList<String> arguments = new ArrayList<String>();
		for(String i : argumentList){
			if(i.startsWith(args)){
				arguments.add(i);
			}
		}
		Collections.sort(arguments);
		if(arguments.isEmpty()){
			arguments.add(name);
		}
		return arguments;
	}
	
	//=======================
	//Get True / False List =
	//=======================
	public static List<String> getBooleanList(String args){
		ArrayList<String> booleanList = new ArrayList<String>();
		if("true".startsWith(args)){
			booleanList.add("true");
		}
		if("false".startsWith(args)){
			booleanList.add("false");
		}
		return booleanList;
	}
	
	//==========================
	//Get Single or Empty List =
	//==========================
	public static List<String> getSingleList(String name){
		ArrayList<String> singleList = new ArrayList<String>();
		if(name != "null"){
			singleList.add(name);
		}
		return singleList;
	}
}
