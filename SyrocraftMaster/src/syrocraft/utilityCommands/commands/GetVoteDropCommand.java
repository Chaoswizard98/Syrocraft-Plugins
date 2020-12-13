package syrocraft.utilityCommands.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class GetVoteDropCommand implements CommandExecutor{
	Main plugin;
	public GetVoteDropCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("GetVoteDrop")){
				if(args.length == 1){
					OfflinePlayer[] players = Bukkit.getOfflinePlayers();
					OfflinePlayer p = null;
					for(int i = 0; i< players.length; i+= 1){
						if(players[i].getName().equals(args[0])){//if this is the player that voted,
							p = players[i];
							break;
						}
					}

					if(p != null){
						if(p.isOnline()){//player is online
							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							Bukkit.dispatchCommand(console, "minecraft:give " + p.getName() + " minecraft:gold_nugget{CustomModelData:1,display:{Lore:['{\"text\":\"§7Redeem at the rare\"}','{\"text\":\"§7items vendor at spawn.\"}','{\"text\":\"§7\"}','{\"text\":\"§3(Currency)\"}'],Name:'{\"text\":\"§3Voter\\\'s Coin\"}'}} 1");//give drop
							Bukkit.dispatchCommand(console, "AddStatistic " + p.getName() + " Server_Stats.Votes 1");//increment vote score
							Bukkit.dispatchCommand(console, "GetDrop Vote_Rewards " + p.getName());//getdrop <droptable> <player> [Update_Stats]
							Bukkit.dispatchCommand(console, "eco give " + p.getName() + " 50");
							Bukkit.dispatchCommand(console, "advancement grant " + p.getName()+" only syrocraft:syro_story/vote");
						}
						else{//player is offline, store stats
							File f = new File("plugins/SyrocraftMaster/Utility","Offline_Votes.yml");//create new file to store their stats
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
							
							int missedVotes = 0;//set missed votes to 0
							if(cfg.getString(p.getUniqueId().toString()) != null){//if the player has not recieved existing votes,
								missedVotes = Integer.parseInt(cfg.getString(p.getUniqueId().toString()));//get the current number of missed votes
							}
							missedVotes += 1;//increment missed votes by 1
							cfg.set(p.getUniqueId().toString(),missedVotes);//update the new missed votes score
							
							try {
								cfg.save(f);//save the file
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else{//player does not exist
						theSender.sendMessage(ChatColor.RED + "Player does not exist.");
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