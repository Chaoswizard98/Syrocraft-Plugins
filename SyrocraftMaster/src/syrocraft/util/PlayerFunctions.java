package syrocraft.util;

import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;


public class PlayerFunctions {
	public static String getNbt(ItemStack item) {
		 net.minecraft.server.v1_15_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		 NBTTagCompound itemCompound = (nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound());
		 return itemCompound.toString();
	}
}
