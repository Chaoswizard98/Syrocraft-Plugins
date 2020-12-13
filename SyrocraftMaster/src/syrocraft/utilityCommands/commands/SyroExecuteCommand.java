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

public class SyroExecuteCommand implements CommandExecutor{
	Main plugin;
	public SyroExecuteCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("SyroExecute")){//SyroExecute <target> <Command>
				if(args.length >= 2){
					if(args[0].equalsIgnoreCase("@Console@")){//if running through console
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String command = args[1];
						for(int j = 2; j < args.length; j+=1){
							command += ' ';
							command += args[j];
						}
						Bukkit.dispatchCommand(console, command);//give drop
					}
					else{//otherwise target is a player
						ArrayList<Player> playerList = ParseTargetSelector.GetPlayerList(theSender, args[0]);
						if (playerList.isEmpty()){//No players found, exit script
							return false;
						}
						else{
							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

							for(int i = 0; i < playerList.size(); i+=1){
								String command = args[1];
								for(int j = 2; j < args.length; j+=1){
									command += ' ';
									command += args[j];
								}
								command = command.replaceAll("@player@", playerList.get(i).getName());
								command = command.replaceAll("@Player@", playerList.get(i).getName());
								Bukkit.dispatchCommand(console, command);//give drop
							}

						}

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