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
import syrocraft.util.NumberParser;
import syrocraft.util.ParseTargetSelector;

public class GetBossDropCommand implements CommandExecutor{
	Main plugin;
	public GetBossDropCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("GetBossDrop")){//GetBossDrop <player> <Drop Table> <Statistic> <Radius> <Advancement Name> <Trigger>
				if(args.length == 6){
					if(NumberParser.isInt(args[3])){
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						System.out.println(args[0]);
						Bukkit.dispatchCommand(console, "getdrop " + args[1] + " " + args[0]);//give drop to initial player
						ArrayList<Player> playerList = ParseTargetSelector.GetPlayerList((CommandSender) ParseTargetSelector.GetPlayerList(theSender, args[0]).get(0), "@a[r="+args[3]+"]");
						if(!playerList.isEmpty()){
							for(int i = 0; i < playerList.size(); i+=1){
								if(playerList.get(i) != null){
									if(playerList.get(i).isOnline()){//player is online
										Bukkit.dispatchCommand(console, "addstatistic " + playerList.get(i).getName() + " "+ args[2] + " 1");//update boss kills statistic
										Bukkit.dispatchCommand(console, "advancement grant " + playerList.get(i).getName()+" only syrocraft:dungeons/"+args[4]);//grant achievement
										Bukkit.dispatchCommand(console, "advancement grant " + playerList.get(i).getName()+" only syrocraft:dungeons/"+args[5]+" "+args[4]);//grant achievement criteria
									}
								}
							}
						}
						else{
							theSender.sendMessage(ChatColor.RED + "Player not found.");
						}
					}
					else{
						theSender.sendMessage(ChatColor.RED + "Invalid Argument. (Int expected)");
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