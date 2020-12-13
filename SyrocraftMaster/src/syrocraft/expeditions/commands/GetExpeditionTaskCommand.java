package syrocraft.expeditions.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
import syrocraft.expeditions.util.ExpeditionsUtil;
import syrocraft.util.ParseTargetSelector;

public class GetExpeditionTaskCommand implements CommandExecutor{
	Main plugin;
	public GetExpeditionTaskCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("GetExpeditionTask")){//GetExpeditionTask <TaskID> <Player>
				ArrayList<Player> playerList;
				playerList = ParseTargetSelector.GetPlayerList(theSender,args[1]);
				if(!playerList.isEmpty()){
					for(int p = 0; p < playerList.size(); p+=1){//loop through all players in the list
						if(playerList.get(p).isOnline()){//if the player is online,
							File f = new File("plugins/SyrocraftMaster/Expeditions/Users",playerList.get(p).getUniqueId()+".yml");//create new file to store their stats
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

							for(String i : cfg.getConfigurationSection("Expeditions").getKeys(false)){//loop through all expeditions
								for(String j : cfg.getConfigurationSection("Expeditions."+i).getKeys(false)){//loop through all tasks
									if(cfg.getString("Expeditions."+i+"."+j+".Task_ID").equalsIgnoreCase(args[0])){//if task matches ID
										File f2 = new File("plugins/SyrocraftMaster/Expeditions/Tasks",i+".yml");
										FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(f2);
										if(f2.exists()){
											if(cfg2.getString("Active").equalsIgnoreCase("true")){//if completed at least min tasks and successfully rolls to end expedition early (caps at max tasks)
												if((Integer.parseInt(cfg.getString("Expeditions."+i+"."+j+".Progress"))>=(Integer.parseInt(cfg2.getString("Min"))-1))&&(Math.random()>=(1-(Double.parseDouble(cfg.getString("Expeditions."+i+"."+j+".Progress"))/Double.parseDouble(cfg2.getString("Max")))))){
													//generate reward
													
													//min stardust, max stardust.
													int max = Integer.parseInt(cfg2.getString("Max_Stardust"));
													int min = Integer.parseInt(cfg2.getString("Min_Stardust"));
													
													Random rand = new Random();
													int ammount = rand.nextInt((max - min) + 1) + min;
													
													ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
													Bukkit.dispatchCommand(console, "minecraft:give " + playerList.get(p).getName() + " minecraft:sugar{CustomModelData:1,display:{Lore:['{\"text\":\"§7Used as a fuel source\"}','{\"text\":\"§7for some items.\"}'],Name:'{\"text\":\"§3Stardust\"}'}} " + ammount);
													Bukkit.dispatchCommand(console, "GetDrop " + cfg2.getString("Drop_Table") + " " + playerList.get(p).getName());//getdrop <droptable> <player> [Update_Stats]
													Bukkit.dispatchCommand(console, "AddStatistic " + playerList.get(p).getName() + " Expeditions."+ i +".Completed 1");
													Bukkit.dispatchCommand(console, "AddStatistic " + playerList.get(p).getName() + " Expeditions.Totals.Total_Expeditions 1");
													
													max = Integer.parseInt(cfg2.getString("Max_Materials"));
													min = Integer.parseInt(cfg2.getString("Min_Materials"));
													ammount = rand.nextInt((max - min) + 1) + min;
													
													for(int k = 0; k < ammount; k+=1){
														Bukkit.dispatchCommand(console, "GetDrop " + cfg2.getString("Materials_Drop_Table") + " " + playerList.get(p).getName());
													}
													
													cfg.set("Expeditions."+i+"."+j,null);//set task to null (completed)
													playerList.get(p).sendMessage(ChatColor.GREEN + "Completed Expedition.");//Print Task
													if(cfg.getConfigurationSection("Expeditions."+i).getKeys(false).isEmpty()){
														cfg.set("Expeditions."+i,null);//set task to null (completed)
													}
												}
												else{
													String expeditionTask = ExpeditionsUtil.GenerateExpeditionTask(i);//generate random task
													cfg.set("Expeditions."+i+"."+j+".Task_ID",expeditionTask);//set task
													cfg.set("Expeditions."+i+"."+j+".Progress",(Integer.parseInt(cfg.getString("Expeditions."+i+"."+j+".Progress"))+1));//set task
													playerList.get(p).sendMessage(ChatColor.GRAY + ExpeditionsUtil.GetExpeditionDescription(i, expeditionTask));
												}
											}
										}
									}
								}
							}
							try {
								cfg.save(f);//save the file
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						else{//player is not online
							theSender.sendMessage(ChatColor.RED + "Player is not online or does not exist.");
						}
					}
				}
				else{//player list is empty
					theSender.sendMessage(ChatColor.RED + "No Players Found.");
				}
			}
		}

		return true;
	}

}
