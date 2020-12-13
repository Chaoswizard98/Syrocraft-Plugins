package syrocraft.utilityCommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import syrocraft.Main;

public class ParticleSphereCommand implements CommandExecutor{
	Main plugin;
	public ParticleSphereCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("ParticleSphere")){//ParticleSphere <Particle> <main argument>
				if(args.length == 2){
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					Bukkit.dispatchCommand(console, "execute at " + args[1] + " run summon minecraft:area_effect_cloud ~ ~ ~ {Particle:\""+args[0]+"\",ReapplicationDelay:10,Radius:1.0f,Duration:5}");
					Bukkit.dispatchCommand(console, "execute at " + args[1] + " run summon minecraft:area_effect_cloud ~ ~.5 ~ {Particle:\""+args[0]+"\",ReapplicationDelay:10,Radius:1.5f,Duration:5}");
					Bukkit.dispatchCommand(console, "execute at " + args[1] + " run summon minecraft:area_effect_cloud ~ ~1 ~ {Particle:\""+args[0]+"\",ReapplicationDelay:10,Radius:2.0f,Duration:5}");
					Bukkit.dispatchCommand(console, "execute at " + args[1] + " run summon minecraft:area_effect_cloud ~ ~1.5 ~ {Particle:\""+args[0]+"\",ReapplicationDelay:10,Radius:1.5f,Duration:5}");
					Bukkit.dispatchCommand(console, "execute at " + args[1] + " run summon minecraft:area_effect_cloud ~ ~2 ~ {Particle:\""+args[0]+"\",ReapplicationDelay:10,Radius:1.0f,Duration:5}");
				}
				else{//invalid argument count
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}
