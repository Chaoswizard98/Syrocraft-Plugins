package syrocraft.utilityCommands.commands;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import syrocraft.Main;
import syrocraft.util.NumberParser;

public class SeenListCommand implements CommandExecutor{
	Main plugin;
	public SeenListCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(commandLabel.equalsIgnoreCase("SeenList")){
			int pageNumber = 1;
			int counter = 0;
			long OutTime = System.currentTimeMillis();//Logout time
			if(args.length == 1){//test for page number
				if(NumberParser.isInt(args[0])){
					pageNumber = Integer.parseInt(args[0]);
				}
				else{
					theSender.sendMessage(ChatColor.RED + "Invalid Argument. (Int expected)");
					return true;
				}
			}
			
			Map<Long, String> playerMap = new TreeMap<Long, String>();
			
			OfflinePlayer[] players = Bukkit.getOfflinePlayers();
			for(int i = 0; i< players.length; i+= 1){
				playerMap.put(OutTime-players[i].getLastPlayed(),players[i].getUniqueId().toString());
			}
			
			theSender.sendMessage(ChatColor.GOLD + "-----Last Seen-----");
		    for(Map.Entry<Long,String> entry : playerMap.entrySet()){
		        if((counter < (pageNumber*8))&&(counter >= ((pageNumber-1)*8))){
					long InTime = Bukkit.getOfflinePlayer(UUID.fromString(entry.getValue())).getLastPlayed();//login time
					int NewMinutes = (int) (((OutTime - InTime) / (1000 * 60)) % 60);
					int NewHours = (int) (((OutTime - InTime) / (1000 * 60 * 60)) % 24);
					int NewDays = (int) (((OutTime - InTime) / (1000 * 60 * 60 * 24)) % 356);
					theSender.sendMessage(ChatColor.RED + Bukkit.getOfflinePlayer(UUID.fromString(entry.getValue())).getName() + ChatColor.GOLD + " was last seen: " +NewDays+ " Days, " +NewHours+ " Hours, " +NewMinutes+ " Minutes ago.");
				}
				counter += 1;
		    }
		    theSender.sendMessage(ChatColor.GOLD + "Page "+pageNumber+" / "+ (int)Math.ceil(counter/8.0));
		}
		return true;
	}
}