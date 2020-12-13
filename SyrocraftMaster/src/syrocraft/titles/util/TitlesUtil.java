package syrocraft.titles.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.statistics.util.StatisticsUtil;

public class TitlesUtil {
	public static boolean HasTitleRequirements(Player player, String title){
		File f = new File("plugins/SyrocraftMaster/Titles/","Titles.yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		if(cfg.contains("Titles."+title+".Requirements")){
			for(String i : cfg.getConfigurationSection("Titles."+title+".Requirements").getKeys(false)){//loop through list
				if(StatisticsUtil.GetStatistic(player,cfg.getString("Titles."+title+".Requirements."+i+".Type"),cfg.getString("Titles."+title+".Requirements."+i+".Statistic"),cfg.getString("Titles."+title+".Requirements."+i+".Data"))<Integer.parseInt(cfg.getString("Titles."+title+".Requirements."+i+".Value"))){
					return false;
				}
			}
		}
		return true;
	}
}