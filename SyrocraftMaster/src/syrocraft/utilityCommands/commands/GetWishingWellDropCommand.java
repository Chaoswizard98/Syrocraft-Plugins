package syrocraft.utilityCommands.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.util.ParseTargetSelector;

public class GetWishingWellDropCommand implements CommandExecutor{
	Main plugin;
	public GetWishingWellDropCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("GetWishingWellDrop")){
				if(args.length == 1){
					ArrayList<Player> playerList = ParseTargetSelector.GetPlayerList(theSender, args[0]);
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					if(!playerList.isEmpty()){
						for(int i = 0; i < playerList.size(); i+=1){
							if(playerList.get(i) != null){
								if(playerList.get(i).isOnline()){//player is online
									Bukkit.dispatchCommand(console, "getdrop Wishing_Well " + playerList.get(i).getName());//give drop
				        			Bukkit.dispatchCommand(console, "getexpeditiontask WishingWell " + playerList.get(i).getName());//give drop
				        			Bukkit.dispatchCommand(console, "advancement grant " + playerList.get(i).getName()+" only syrocraft:syro_story/gambler");//give drop
								}
							}
						}
					}
					else{
						theSender.sendMessage(ChatColor.RED + "Player not found.");
					}
				}
				else{//invalid argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}