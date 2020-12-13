package syrocraft.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParseTargetSelector {
	public static ArrayList<Player> GetPlayerList(CommandSender theSender, String targetSelector){
		ArrayList<Player> playerList;//create the player list
		playerList = new ArrayList<Player>();

		if(targetSelector.equalsIgnoreCase("@p")||targetSelector.startsWith("@p[r=", 0)){//getting nearest player
			double closest = Double.MAX_VALUE;//set closest distance to max value
			Player closestp = null; //set closest player to null

			if(targetSelector.length()>6){//if target selector contains a radius
				closest = Integer.parseInt(targetSelector.substring(5, targetSelector.length()-1));//read the radius
				closest *= closest;//convert to radius squared (distance)
			}

			if(theSender instanceof Player){//if a player sent the command,
				closestp=(Player) theSender;//set nearest player to the sender
			}
			else if(theSender instanceof BlockCommandSender){//if a command block sent the command
				BlockCommandSender Block=(BlockCommandSender) theSender;//sender = command block
				Location loc = Block.getBlock().getLocation();//location = senders location
				for(Player i : Bukkit.getOnlinePlayers()){//loop through all players
					if(i.getWorld() == loc.getWorld()){//if they are in the same world as the sender
						double dist = i.getLocation().distanceSquared(loc); //get distance from sender
						if (closest == Double.MAX_VALUE || dist < closest){//if we have a new closest player,
							closest = dist;//remember closest distance
							closestp = i;//remember closest player
						}
					} 
				}
			}
			if (closestp != null){//if we found a player
				playerList.add(closestp);//add them to the list
			}
		}
		else if(targetSelector.equalsIgnoreCase("@a")||targetSelector.startsWith("@a[r=", 0)){//getting all players
			double closest = Double.MAX_VALUE;//set closest distance to max value
			
			if(targetSelector.length()>6){//if target selector contains a radius
				closest = Integer.parseInt(targetSelector.substring(5, targetSelector.length()-1));//read the radius
				closest *= closest;//convert to radius squared (distance)
			}
			
			if(theSender instanceof Player){//if a player sent the command,
				Player player=(Player) theSender;//set sender to player
				Location loc = player.getLocation();//get player location
				for(Player i : Bukkit.getOnlinePlayers()){//loop through all players
					if(i.getWorld() == loc.getWorld()){//if they are in the same world
						double dist = i.getLocation().distanceSquared(loc);//get the distance from sender location
						if (closest == Double.MAX_VALUE || dist < closest){//if they are within range,
							playerList.add(i);//add them to the list
						}
					}
				} 
			}
			else if(theSender instanceof BlockCommandSender){//if the sender was a command block,
				BlockCommandSender Block=(BlockCommandSender) theSender;//set sender to command block
				Location loc = Block.getBlock().getLocation();//get sender location
					for(Player i : Bukkit.getOnlinePlayers()){//loop through all players
						if(i.getWorld() == loc.getWorld()){//if they are in the same world
							double dist = i.getLocation().distanceSquared(loc);//get the distance from sender location
							if (closest == Double.MAX_VALUE || dist < closest){//if they are within range,
								playerList.add(i);//add them to the list
							}
						}
					}
			}
		}
		else{//getting specific player
			for(Player i : Bukkit.getOnlinePlayers()){//loop through all players
				if(i.getName().equals(targetSelector)){
					playerList.add(i);
				}
			}
		}
		return playerList;
	}
}