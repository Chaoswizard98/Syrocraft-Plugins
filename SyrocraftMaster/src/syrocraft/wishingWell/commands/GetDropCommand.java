package syrocraft.wishingWell.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

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
import syrocraft.util.ParseTargetSelector;

public class GetDropCommand implements CommandExecutor{
	Main plugin;
	public GetDropCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetDrop")){//getdrop <droptable> <player> [Update_Stats]
				if((args.length == 2)||(args.length == 3)){
					boolean updateStats = true;
					if(args.length == 3){
						if(args[2].equalsIgnoreCase("false")){
							updateStats = false;
						}
					}
					String dropTable = args[0];
					ArrayList<Player> playerList;
					playerList = ParseTargetSelector.GetPlayerList(theSender,args[1]);
					if(!playerList.isEmpty()){
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

						for(int j = 0; j < playerList.size(); j+=1){//loop through all players in the list
							if(playerList.get(j).isOnline()){//if the player is online,
								ArrayList<String> potentialDrops;//create list of potential drops
								potentialDrops = new ArrayList<String>();
								File f = new File("plugins/SyrocraftMaster/DropTables",dropTable+".yml");//get the drop table file
								FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

								if(cfg.contains("Drops")){
									Set<String> dropList = cfg.getConfigurationSection("Drops").getKeys(false);
									if(!dropList.isEmpty()){
										while(potentialDrops.size() == 0){//while we dont yet have a drop,
											for(String i : dropList){//loop through drop list
												if(Math.random() <= Double.parseDouble(cfg.getString("Drops." + i + ".Rarity"))){//if the item drops
													potentialDrops.add(i);//add to list
												}
											}
										}

										ArrayList<String> finalDrops;//create list of potential drops
										finalDrops = new ArrayList<String>();
										for(String i : potentialDrops){//loop through potential drops
											finalDrops.add(i);//add item to the list
										}

										Random rn = new Random();//get a new RNG
										int rand = rn.nextInt(finalDrops.size());//get random number between 0 and list size
										String dropItem = finalDrops.get(rand);//set drop item to the randomly chosen item

										String Command;
										Command = cfg.getString("Drops." + dropItem + ".Command").replaceAll("@player@", playerList.get(j).getName());//make sure player spawns item
										if(updateStats){
											Bukkit.dispatchCommand(console, "addstatistic " + playerList.get(j).getName() + " Drop_Tables.Totals.Total_Drops 1");
											Bukkit.dispatchCommand(console, "addstatistic " + playerList.get(j).getName() + " Drop_Tables."+dropTable+".Drops 1");
										}

										if(cfg.getString("Drops." + dropItem + ".Broadcast").equalsIgnoreCase("true")){
											Bukkit.dispatchCommand(console, "broadcast &b" + playerList.get(j).getName() + " &ahas recieved &6" + dropItem + "&a " + cfg.getString("Rare_Message"));
											if(updateStats){
												Bukkit.dispatchCommand(console, "addstatistic " + playerList.get(j).getName() + " Drop_Tables.Totals.Total_Rare_Drops 1");
												Bukkit.dispatchCommand(console, "addstatistic " + playerList.get(j).getName() + " Drop_Tables."+dropTable+".Rare_Drops 1");
											}
										}

										Bukkit.dispatchCommand(console, Command);//give drop
									}
									else{
										theSender.sendMessage(ChatColor.RED + "Drop tables are empty.");
									}
								}
								else{
									theSender.sendMessage(ChatColor.RED + "Specified drop table does not exist.");
								}
							}
						}
						theSender.sendMessage(ChatColor.GREEN + "Successfully ran drop command.");
					}
					else{//player list is empty (invalid player)
						theSender.sendMessage(ChatColor.RED + "Player not found.");
					}
				}
				else{//invalid argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		else{//the sender is not OP
			theSender.sendMessage(ChatColor.RED + "Sorry, but you don't have permission to use this command.");
		}
		return true;
	}
}