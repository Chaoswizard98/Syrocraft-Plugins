package syrocraft.expeditions.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.expeditions.util.ExpeditionsUtil;
import syrocraft.util.ParseTargetSelector;

public class StartExpeditionCommand implements CommandExecutor{
	Main plugin;
	public StartExpeditionCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if (commandLabel.equalsIgnoreCase("StartExpedition")){//StartExpedition <expedition> <Player>
				if(args.length == 2){
					File f = new File("plugins/SyrocraftMaster/Expeditions/Tasks",args[0]+".yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					if(f.exists()){//does the expedition exist?
						if(cfg.getString("Active").equalsIgnoreCase("true")){
							ArrayList<Player> playerList;
							playerList = ParseTargetSelector.GetPlayerList(theSender,args[1]);
							if(!playerList.isEmpty()){
								for(int j = 0; j < playerList.size(); j+=1){//loop through all players in the list
									if(playerList.get(j).isOnline()){//if the player is online,
										String expeditionTask = ExpeditionsUtil.GenerateExpeditionTask(args[0]);//generate random task
										File f2 = new File("plugins/SyrocraftMaster/Expeditions/Users",playerList.get(j).getUniqueId()+".yml");//create new file to store their stats
										FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(f2);
										cfg2.set("Name", playerList.get(j).getName());
										if(ExpeditionsUtil.GetExpeditionTaskCount(playerList.get(j),args[0])<Integer.parseInt(cfg.getString("Slots"))){
											for(int i = 0; i< Integer.parseInt(cfg.getString("Slots")); i += 1){//loop through available slots
												if(cfg2.getString("Expeditions."+args[0]+".Slot"+i) == null){
													cfg2.set("Expeditions."+args[0]+".Slot"+i+".Task_ID",expeditionTask);//add new task
													cfg2.set("Expeditions."+args[0]+".Slot"+i+".Progress","0");//set progress to 0
													playerList.get(j).sendMessage(ChatColor.GRAY + ExpeditionsUtil.GetExpeditionDescription(args[0],expeditionTask));//Print Task
													try {
														cfg2.save(f2);//save the file
													} catch (IOException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													break;
												}
											}
										}
										else{//cant generate task, at limit
											playerList.get(j).sendMessage(ChatColor.RED + "You're already running the maximum number of expeditions for this type.");//Print Task
										}
									}
								}
							}
							else{//no valid players
								theSender.sendMessage(ChatColor.RED + "No valid players");
							}
						}
						else{
							theSender.sendMessage(ChatColor.RED + "Expedition is currently disabled.");
						}
					}
					else{//Expedition does not exist
						theSender.sendMessage(ChatColor.RED + "Expedition type does not exist.");
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