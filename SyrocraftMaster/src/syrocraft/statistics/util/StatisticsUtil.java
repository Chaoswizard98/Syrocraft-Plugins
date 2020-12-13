package syrocraft.statistics.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class StatisticsUtil {
	
	
	public static void UpdatePlaytime(Player p){
		File f = new File("plugins/SyrocraftMaster/Statistics/Users",p.getUniqueId().toString()+".yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		int seconds = 0;
		int minutes = 0; 
		int hours = 0; 
		int days = 0;

		if(cfg.getString("Playtime.Seconds")!= null){//load current playtime or initialize to 0 if non-existant
			seconds = Integer.parseInt(cfg.getString("Playtime.Seconds"));
		}
		if(cfg.getString("Playtime.Minutes") != null){
			minutes = Integer.parseInt(cfg.getString("Playtime.Minutes"));
		}
		if(cfg.getString("Playtime.Hours") != null){
			hours = Integer.parseInt(cfg.getString("Playtime.Hours"));
		}
		if(cfg.getString("Playtime.Days")!= null){
			days = Integer.parseInt(cfg.getString("Playtime.Days"));
		}

		long outTime = System.currentTimeMillis();//Logout time
		long inTime = Long.parseLong(cfg.getString("Playtime.TimeIn"));//login time

		//Figure out new playtime
		int newSeconds = (int) (((outTime - inTime) / 1000) % 60);
		int newMinutes = (int) (((outTime - inTime) / (1000 * 60)) % 60);
		int newHours = (int) (((outTime - inTime) / (1000 * 60 * 60)) % 24);
		int newDays = (int) (((outTime - inTime) / (1000 * 60 * 60 * 24)) % 356);

		//Update Time
		seconds += newSeconds;
		if(seconds >= 60){
			seconds -= 60;
			minutes += 1;
		}
		minutes += newMinutes;
		if(minutes >= 60){
			minutes -= 60;
			hours += 1;
		}
		hours += newHours;
		if(hours >= 24){
			hours -= 24;
			days += 1;
		}
		days += newDays;

		//Save Time
		cfg.set("Playtime.Seconds", seconds);
		cfg.set("Playtime.Minutes", minutes);
		cfg.set("Playtime.Hours", hours);
		cfg.set("Playtime.Days", days);
		cfg.set("Playtime.TimeIn", System.currentTimeMillis()); //remember login time (To later on save playtime)
		
		try {
			cfg.save(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static boolean HasUnclaimedVoteRewards(Player p){//checks if they have unclaimed vote rewards or not.
		File f = new File("plugins/SyrocraftMaster/Utility","Offline_Votes.yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		if(f.exists()){
			if(cfg.getString(p.getUniqueId().toString()) != null){//if the player has offline votes
				return true;
			}
		}
		return false;
	}
	
	public static void SetLoginStatistic(Player p){//sets their login time for their stats file
		File f = new File("plugins/SyrocraftMaster/Statistics/Users",p.getUniqueId().toString()+".yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		cfg.set("Utility.UUID", p.getUniqueId().toString());//store their UUID (Helps with online database stuff)
		cfg.set("Utility.Name", p.getName());//Store their current Name (Helps with online database stuff)
		cfg.set("Playtime.TimeIn", System.currentTimeMillis()); //remember login time (To later on save playtime)
		
		try {
			cfg.save(f);//save the file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static int GetStatistic(Player p, String type, String statistic, String data){//<Type> <Statistic> <Block/Entity>
		if(type.equalsIgnoreCase("Material")){
			return p.getStatistic(Statistic.valueOf(statistic),Material.valueOf(data));
		}
		else if(type.equalsIgnoreCase("Entity")){
			return p.getStatistic(Statistic.valueOf(statistic),EntityType.valueOf(data));
		}
		else if(type.equalsIgnoreCase("Other")){
			return p.getStatistic(Statistic.valueOf(statistic));
		}
		else if(type.equalsIgnoreCase("Custom")){
			File f = new File("plugins/SyrocraftMaster/Statistics/Users",p.getUniqueId().toString()+".yml");//create new file to store their stats
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			
			if(cfg.getString(statistic)!= null){
				return Integer.parseInt(cfg.getString(statistic));
			}
		}
		return 0;
	}

	public static void UpdateSeenList(Player player) {
		File f = new File("plugins/SyrocraftMaster/Utility","Seen_List.yml");//create new file to store their stats
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		cfg.set("Players."+player.getUniqueId().toString(),System.currentTimeMillis());//update the new logout time
		try {
			cfg.save(f);//save the file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}