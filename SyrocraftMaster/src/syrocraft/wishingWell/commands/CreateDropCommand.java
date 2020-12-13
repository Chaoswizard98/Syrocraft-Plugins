package syrocraft.wishingWell.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import syrocraft.Main;
import syrocraft.util.NumberParser;

public class CreateDropCommand implements CommandExecutor{
	Main plugin;
	public CreateDropCommand(Main pl){
		this.plugin = pl;
	}
	
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("CreateDrop")){//createdrop <droptable> <name> <rarity> <broadcast> <command>
				if(args.length >= 5){//make sure we have the correct number of arguments
					if(NumberParser.isDouble(args[2])){//test for args 2 being a double
						if(args[3].equalsIgnoreCase("true")||args[3].equalsIgnoreCase("false")){//test for args 3 being true or false

							File f = new File("plugins/SyrocraftMaster/DropTables",args[0]+".yml");//create new file to store their stats
							FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
							if(f.exists()){
								cfg.set("Drops."+args[1]+".Rarity",args[2]);//set the rarity of the item
								cfg.set("Drops."+args[1]+".Broadcast",args[3]);//set the broadcast boolean
								StringBuilder sb = new StringBuilder(args[4]);//merge the remaining arguments into 1 string
								for(int i = 5; i < args.length; i++){
									sb.append(' ');
									sb.append(args[i]);
								}
								String Command = sb.toString();//finalize the string
								cfg.set("Drops."+args[1]+".Command",Command);//set the command
								try {
									cfg.save(f);//save the file
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								theSender.sendMessage(ChatColor.GREEN + "Drop created successfully.");
							}
							else{
								theSender.sendMessage(ChatColor.RED + "Drop table does not exist.");
							}
						}
						else{//broadcast is not true or false
							theSender.sendMessage(ChatColor.RED + "Invalid Argument. (True or False expected)");
						}
					}
					else{//rarity isnt a double
						theSender.sendMessage(ChatColor.RED + "Invalid Argument. (Double expected)");
					}
				}
				else{//wrong number of arguments
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}
