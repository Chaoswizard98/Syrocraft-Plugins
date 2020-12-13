package syrocraft.titles.commands;

import java.io.File;
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
import syrocraft.statistics.util.StatisticsUtil;
import syrocraft.titles.util.TitlesUtil;
import syrocraft.util.NumberParser;

public class LockedTitlesCommand implements CommandExecutor{
	Main plugin;
	public LockedTitlesCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if (commandLabel.equalsIgnoreCase("LockedTitles")){//LockedTitles [Page]
			int pageNumber = 1;
			int counter = 0;
			if(args.length == 1){
				if(NumberParser.isInt(args[0])){
					pageNumber = Integer.parseInt(args[0]);
				}
				else{
					theSender.sendMessage(ChatColor.RED + "Invalid Argument. (Int expected)");
					return true;
				}
			}

			File f = new File("plugins/SyrocraftMaster/Titles/","Titles.yml");//create new file to store their stats
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			if(cfg.contains("Titles")){
				Set<String> titleList = cfg.getConfigurationSection("Titles").getKeys(false);
				Player player = (Player) theSender;
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

				int currentScore = 0;
				int minScore = 0;
				String hoverText = "";
				String message = "";
				boolean firstPass = true;
				theSender.sendMessage(ChatColor.GOLD + "-----Locked Titles-----");
				for(String i : titleList){//loop through list
					firstPass = true;
					hoverText = "";
					if(!TitlesUtil.HasTitleRequirements(player,i)){
						if((counter < (pageNumber*8))&&(counter >= ((pageNumber-1)*8))){
							for(String j : cfg.getConfigurationSection("Titles."+i+".Requirements").getKeys(false)){
								currentScore = StatisticsUtil.GetStatistic(player,cfg.getString("Titles."+i+".Requirements."+j+".Type"),cfg.getString("Titles."+i+".Requirements."+j+".Statistic"),cfg.getString("Titles."+i+".Requirements."+j+".Data"));
								minScore = Integer.parseInt(cfg.getString("Titles."+i+".Requirements."+j+".Value"));
								message = cfg.getString("Titles."+i+".Requirements."+j+".Message");
								if(!firstPass){//if this isnt the first requirement
									hoverText += "\\n";//append newline
								}
								hoverText += currentScore + " / " + minScore + " " + message;
								firstPass = false;
							}

							Bukkit.dispatchCommand(console, "tellraw "+player.getName()+" [\"\",{\"text\":\"["+i+"]\",\"color\":\"gray\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\""+hoverText+"\",\"color\":\"gray\"}]}}}]");
						}
						counter += 1;
					}
				}
				theSender.sendMessage(ChatColor.GOLD + "Page "+pageNumber+" / "+ (int)Math.ceil(counter/8.0));
			}
		}
		return true;
	}
}