package syrocraft.util;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class MobFunctions {
    //====================
    //Test for Is Undead =
    //====================
    public static boolean isUndead(LivingEntity e){
    	if(e.getType().equals(EntityType.SKELETON)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.ZOMBIE)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.WITHER)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.WITHER_SKELETON)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.PIG_ZOMBIE)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.SKELETON_HORSE)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.ZOMBIE_HORSE)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.STRAY)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.HUSK)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.DROWNED)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.PHANTOM)){
    		return true;
    	}
    	return false;
    }
    
    //=======================
    //Test for Is Arthropod =
    //=======================
    public static boolean isArthropod(LivingEntity e){
    	if(e.getType().equals(EntityType.SPIDER)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.CAVE_SPIDER)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.SILVERFISH)){
    		return true;
    	}
    	else if(e.getType().equals(EntityType.ENDERMITE)){
    		return true;
    	}
    	return false;
    }
}
