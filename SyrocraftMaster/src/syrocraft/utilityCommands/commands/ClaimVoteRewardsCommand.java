package syrocraft.utilityCommands.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.statistics.util.StatisticsUtil;

public class ClaimVoteRewardsCommand implements CommandExecutor{
	Main plugin;
	public ClaimVoteRewardsCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) theSender;
			if(commandLabel.equalsIgnoreCase("ClaimVoteRewards")){
				if(StatisticsUtil.HasUnclaimedVoteRewards(p)){//if the player has offline votes
					File f = new File("plugins/SyrocraftMaster/Utility","Offline_Votes.yml");//create new file to store their stats
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					
					int missedVotes = Integer.parseInt(cfg.getString(p.getUniqueId().toString()));//get the current number of missed votes
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(int i = 0; i < missedVotes; i+= 1){//for all votes, generate a reward
						Bukkit.dispatchCommand(console, "getvotedrop " + p.getName());
					}
				
					cfg.set(p.getUniqueId().toString(),null);//remove player from file
					
					try {
						cfg.save(f);//save the file
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					theSender.sendMessage(ChatColor.GREEN + "Rewards claimed successfully.");
				}
				else{
					theSender.sendMessage(ChatColor.RED + "You do not have rewards to claim.");
				}
			}
			return true;
	}
}
