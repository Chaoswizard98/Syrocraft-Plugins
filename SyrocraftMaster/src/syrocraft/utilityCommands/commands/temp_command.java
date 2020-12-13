package syrocraft.utilityCommands.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;

public class temp_command implements CommandExecutor{
	Main plugin;
	public temp_command(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(commandLabel.equalsIgnoreCase("temp")){
			
			for (File file : new File("plugins/Old_Stats").listFiles()){//loop through all files
				File f = new File("plugins/SyrocraftMaster/Statistics/Users",file.getName());//create new file to store their stats
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
				
				FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file);
				
				
				cfg.set("Utility.UUID", cfg2.getString("Utility.UUID"));
				cfg.set("Utility.Name",cfg2.getString("Utility.Name"));

				cfg.set("Playtime.TimeIn",cfg2.getLong("Playtime.TimeIn"));
				cfg.set("Playtime.Seconds",cfg2.getInt("Playtime.Seconds"));
				cfg.set("Playtime.Minutes",cfg2.getInt("Playtime.Minutes"));
				cfg.set("Playtime.Hours",cfg2.getInt("Playtime.Hours"));
				cfg.set("Playtime.Days",cfg2.getInt("Playtime.Days"));
				
				cfg.set("Server_Stats.Votes",cfg2.getInt("Custom.Votes"));
				cfg.set("Server_Stats.Donations",cfg2.getInt("Custom.Donor"));
				
				cfg.set("Expeditions.Totals.Total_Expeditions",cfg2.getInt("Custom.TotalExpeditions"));
				cfg.set("Expeditions.Easy.Completed",cfg2.getInt("Custom.EasyExpeditions"));
				cfg.set("Expeditions.Medium.Completed",cfg2.getInt("Custom.MediumExpeditions"));
				cfg.set("Expeditions.Hard.Completed",cfg2.getInt("Custom.HardExpeditions"));
				cfg.set("Expeditions.Elite.Completed",cfg2.getInt("Custom.EliteExpeditions"));
				
				cfg.set("Drop_Tables.Totals.Total_Drops",cfg2.getInt("Custom.WishingWell"));
				cfg.set("Drop_Tables.Totals.Total_Rare_Drops",cfg2.getInt("Custom.RareDrops"));
				cfg.set("Drop_Tables.Wishing_Well.Drops",cfg2.getInt("Custom.WishingWell"));
				cfg.set("Drop_Tables.Wishing_Well.Rare_Drops",0);
				
				
				cfg.set("Boss_Kills.Totals.Total_Kills", cfg2.getInt("Custom.SinarthraKills")+cfg2.getInt("Custom.VandredKills")+cfg2.getInt("Custom.OlgrithKills")+cfg2.getInt("Custom.IllfangKills")+cfg2.getInt("Custom.AcidiousKills")+cfg2.getInt("Custom.PlaguebringerKills")+cfg2.getInt("Custom.LevitatiousKills")+cfg2.getInt("Custom.FlameWielderKills")+cfg2.getInt("Custom.FleshRenderKills")+cfg2.getInt("Custom.ValconeKills")+cfg2.getInt("Custom.MotherSpiderKills")+cfg2.getInt("Custom.EWraithKills")+cfg2.getInt("Custom.PraxiusKills")+cfg2.getInt("Custom.DoomskullKills")+cfg2.getInt("Custom.HerobrineKills")+cfg2.getInt("Custom.HerobrineKills")+cfg2.getInt("Custom.HelixKills")+cfg2.getInt("Custom.VorpalBunnyKills"));
				
				cfg.set("Boss_Kills.Bosses.Sinarthra",cfg2.getInt("Custom.SinarthraKills"));
				cfg.set("Boss_Kills.Bosses.Vandred",cfg2.getInt("Custom.VandredKills"));
				cfg.set("Boss_Kills.Bosses.Olgrith",cfg2.getInt("Custom.OlgrithKills"));
				cfg.set("Boss_Kills.Bosses.Illfang_Necromancer",cfg2.getInt("Custom.IllfangKills"));
				cfg.set("Boss_Kills.Bosses.Acidious",cfg2.getInt("Custom.AcidiousKills"));
				cfg.set("Boss_Kills.Bosses.Cursed_Plaguebringer",cfg2.getInt("Custom.PlaguebringerKills"));
				cfg.set("Boss_Kills.Bosses.Levitatious",cfg2.getInt("Custom.LevitatiousKills"));
				cfg.set("Boss_Kills.Bosses.Demonic_Flamewielder",cfg2.getInt("Custom.FlameWielderKills"));
				cfg.set("Boss_Kills.Bosses.Demonic_Fleshrender",cfg2.getInt("Custom.FleshRenderKills"));
				cfg.set("Boss_Kills.Bosses.Valcone",cfg2.getInt("Custom.ValconeKills"));
				cfg.set("Boss_Kills.Bosses.Mother_Spider",cfg2.getInt("Custom.MotherSpiderKills"));
				cfg.set("Boss_Kills.Bosses.Enchanted_Wraith",cfg2.getInt("Custom.EWraithKills"));
				cfg.set("Boss_Kills.Bosses.Praxius",cfg2.getInt("Custom.PraxiusKills"));
				cfg.set("Boss_Kills.Bosses.General_Doomskull",cfg2.getInt("Custom.DoomskullKills"));
				cfg.set("Boss_Kills.Bosses.Herobrine",cfg2.getInt("Custom.HerobrineKills"));
				cfg.set("Boss_Kills.Bosses.Helix",cfg2.getInt("Custom.HelixKills"));
				cfg.set("Boss_Kills.Bosses.Vorpal_Bunny",cfg2.getInt("Custom.VorpalBunnyKills"));
				
				
				
				//cfg.set(args[1],value);
				try {
					cfg.save(f);//save the file
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			theSender.sendMessage(ChatColor.GREEN + "Statistic Updated.");
		}
		return true;
	}

}
