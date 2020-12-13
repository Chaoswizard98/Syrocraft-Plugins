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
import syrocraft.titles.util.TitlesUtil;
import syrocraft.util.NumberParser;

public class UnlockedTitlesCommand implements CommandExecutor{
	Main plugin;
	public UnlockedTitlesCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if (commandLabel.equalsIgnoreCase("UnlockedTitles")){//UnlockedTitles [Page]
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
				theSender.sendMessage(ChatColor.GOLD + "-----Unlocked Titles-----");
				Bukkit.dispatchCommand(console, "tellraw " +player.getName()+" [\"\",{\"text\":\"[Reset Prefix]\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\""+ "/lp user "+player.getName()+" meta removeprefix 50" +"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to activate this title.\",\"color\":\"gray\"}]}}}]");
				Bukkit.dispatchCommand(console, "tellraw " +player.getName()+" [\"\",{\"text\":\"[Reset Suffix]\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\""+ "/lp user "+player.getName()+" meta removesuffix 50" +"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to activate this title.\",\"color\":\"gray\"}]}}}]");
				Bukkit.dispatchCommand(console, "tellraw " +player.getName()+" [\"\",{\"text\":\"[Reset Name Color]\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\""+ "/team leave "+player.getName() +"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to activate this title.\",\"color\":\"gray\"}]}}}]");
				for(String i : titleList){//loop through list
					if(TitlesUtil.HasTitleRequirements(player,i)){
						if((counter < (pageNumber*8))&&(counter >= ((pageNumber-1)*8))){
							Bukkit.dispatchCommand(console, "tellraw " +player.getName()+" [\"\",{\"text\":\"["+i+"]\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\""+ "/SetTitle "+player.getName()+" "+i +"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to activate this title.\",\"color\":\"gray\"}]}}}]");
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