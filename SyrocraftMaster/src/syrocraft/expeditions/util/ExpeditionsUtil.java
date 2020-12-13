package syrocraft.expeditions.util;

import java.io.File;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ExpeditionsUtil {
	public static String GenerateExpeditionTask(String expedition){
		File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",expedition+".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		ArrayList<String> taskList = new ArrayList<String>();
		for(String i : cfg.getConfigurationSection("Tasks").getKeys(false)){//loop through all tasks
			taskList.add(i);//add them to the array list
		}
		Random rn = new Random();//get a new RNG
		int rand = rn.nextInt(taskList.size());//get random number between 0 and list size
		return taskList.get(rand);
	}
	
	public static int GetExpeditionTaskCount(Player player, String expedition){
		File f = new File("plugins/SyrocraftMaster/Expeditions/Users",player.getUniqueId()+".yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int counter = 0;
		
		if(f.exists()){
			if(cfg.getConfigurationSection("Expeditions."+expedition)!= null){
			counter = cfg.getConfigurationSection("Expeditions."+expedition).getKeys(false).size();
			}
		}
		return counter;
	}
	
	public static String GetExpeditionDescription(String expedition, String task){
		File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",expedition+".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		return cfg.getString("Tasks."+task);
	}
}