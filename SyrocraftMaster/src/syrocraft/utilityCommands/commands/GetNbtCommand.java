package syrocraft.utilityCommands.commands;

import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import syrocraft.Main;
import syrocraft.util.PlayerFunctions;

public class GetNbtCommand implements CommandExecutor {
	Main plugin;

	public GetNbtCommand(Main pl) {
		this.plugin = pl;
	}

	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String[] args) {
		if (theSender.isOp()) {
			if (commandLabel.equalsIgnoreCase("GetNbt")) {
				Player p = (Player) theSender;
				if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
					theSender.sendMessage(PlayerFunctions.getNbt(p.getInventory().getItemInMainHand()));
				}
			}
		}
		return true;
	}
}
