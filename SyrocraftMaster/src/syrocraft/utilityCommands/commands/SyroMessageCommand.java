package syrocraft.utilityCommands.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import syrocraft.Main;
import syrocraft.util.ParseTargetSelector;

public class SyroMessageCommand implements CommandExecutor{
	Main plugin;
	public SyroMessageCommand(Main pl){
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args){
		if(theSender.isOp()){
			if(commandLabel.equalsIgnoreCase("SyroMessage")){
				if(args.length > 1){
					ArrayList<Player> playerList = ParseTargetSelector.GetPlayerList(theSender, args[0]);
					if (playerList.isEmpty()){//No players found, exit script
    					return false;
    				}
					else{
						String command = args[1];
						for(int j = 2; j < args.length; j+=1){
							command += ' ';
							command += args[j];
						}
						for(int i = 0; i < playerList.size(); i+=1){
							playerList.get(i).sendMessage(command.replaceAll("&", "§"));
						}
					}
				}
			}
		}
		return true;
	}

}