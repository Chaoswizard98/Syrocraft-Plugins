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

public class CreateDropTableCommand implements CommandExecutor{
	Main plugin;
	public CreateDropTableCommand(Main pl){
		this.plugin = pl;
	}
	
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("CreateDropTable")){//CreateDropTable <droptable> <message>
				if(args.length >= 2){
					StringBuilder sb = new StringBuilder(args[1]);//combine arguments into one string
					for(int i = 2; i < args.length; i++){
						sb.append(' ');
						sb.append(args[i]);
					}
					String Message = sb.toString();//our combined string

					File f = new File("plugins/SyrocraftMaster/DropTables",args[0]+".yml");//get the drop table file
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					
					cfg.set("Drop_Table",args[0]);//append the name to the top of the file (for readability)
					cfg.set("Rare_Message",Message);//set the new rare drop message

					try {
						cfg.save(f);//save the file
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theSender.sendMessage(ChatColor.GREEN + "Drop table created successfully.");
				}
				else{//wrong number of arguments
					theSender.sendMessage(ChatColor.RED + "Invalid argument count.");
				}
			}
		}
		return true;
	}
}