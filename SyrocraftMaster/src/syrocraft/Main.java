package syrocraft;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import syrocraft.expeditions.commands.CreateExpeditionCommand;
import syrocraft.expeditions.commands.CreateExpeditionTaskCommand;
import syrocraft.expeditions.commands.DisableExpeditionCommand;
import syrocraft.expeditions.commands.EnableExpeditionCommand;
import syrocraft.expeditions.commands.ExpeditionsCommand;
import syrocraft.expeditions.commands.GetExpeditionInfoCommand;
import syrocraft.expeditions.commands.GetExpeditionTaskCommand;
import syrocraft.expeditions.commands.RemoveExpeditionCommand;
import syrocraft.expeditions.commands.RemoveExpeditionTaskCommand;
import syrocraft.expeditions.commands.SetExpeditionOrderCommand;
import syrocraft.expeditions.commands.StartExpeditionCommand;
import syrocraft.expeditions.tabCompleters.CreateExpeditionTabCompleter;
import syrocraft.expeditions.tabCompleters.CreateExpeditionTaskTabCompleter;
import syrocraft.expeditions.tabCompleters.DisableExpeditionTabCompleter;
import syrocraft.expeditions.tabCompleters.EnableExpeditionTabCompleter;
import syrocraft.expeditions.tabCompleters.GetExpeditionInfoTabCompleter;
import syrocraft.expeditions.tabCompleters.GetExpeditionTaskTabCompleter;
import syrocraft.expeditions.tabCompleters.RemoveExpeditionTabCompleter;
import syrocraft.expeditions.tabCompleters.RemoveExpeditionTaskTabCompleter;
import syrocraft.expeditions.tabCompleters.SetExpeditionOrderTabCompleter;
import syrocraft.expeditions.tabCompleters.StartExpeditionTabCompleter;
import syrocraft.flags.commands.SetFlagCommand;
import syrocraft.flags.commands.TestFlagCommand;
import syrocraft.flags.tabCompleters.SetFlagTabCompleter;
import syrocraft.flags.tabCompleters.TestFlagTabCompleter;
import syrocraft.statistics.commands.AddStatisticCommand;
import syrocraft.statistics.commands.GetStatisticCommand;
import syrocraft.statistics.commands.PlaytimeCommand;
import syrocraft.statistics.commands.RemoveStatisticCommand;
import syrocraft.statistics.commands.SetStatisticCommand;
import syrocraft.statistics.listeners.LoginEventListener;
import syrocraft.statistics.listeners.LogoutEventListener;
import syrocraft.statistics.tabCompleters.AddStatisticTabCompleter;
import syrocraft.statistics.tabCompleters.GetStatisticTabCompleter;
import syrocraft.statistics.tabCompleters.PlaytimeTabCompleter;
import syrocraft.statistics.tabCompleters.RemoveStatisticTabCompleter;
import syrocraft.statistics.tabCompleters.SetStatisticTabCompleter;
import syrocraft.statistics.util.StatisticsUtil;
import syrocraft.titles.commands.AddTitleRequirementCommand;
import syrocraft.titles.commands.CreateTitleCommand;
import syrocraft.titles.commands.LockedTitlesCommand;
import syrocraft.titles.commands.RemoveTitleCommand;
import syrocraft.titles.commands.RemoveTitleRequirementCommand;
import syrocraft.titles.commands.SetTitleCommand;
import syrocraft.titles.commands.UnlockedTitlesCommand;
import syrocraft.titles.tabCompleters.AddTitleRequirementTabCompleter;
import syrocraft.titles.tabCompleters.CreateTitleTabCompleter;
import syrocraft.titles.tabCompleters.LockedTitlesTabCompleter;
import syrocraft.titles.tabCompleters.RemoveTitleRequirementTabCompleter;
import syrocraft.titles.tabCompleters.RemoveTitleTabCompleter;
import syrocraft.titles.tabCompleters.SetTitleTabCompleter;
import syrocraft.titles.tabCompleters.UnlockedTitlesTabCompleter;
import syrocraft.utilityCommands.commands.ClaimVoteRewardsCommand;
import syrocraft.utilityCommands.commands.GetBossDropCommand;
import syrocraft.utilityCommands.commands.GetVoteDropCommand;
import syrocraft.utilityCommands.commands.GetWishingWellDropCommand;
//import syrocraft.utilityCommands.commands.ParticleSphereCommand;
import syrocraft.utilityCommands.commands.SeenListCommand;
import syrocraft.utilityCommands.commands.SyroExecuteCommand;
import syrocraft.utilityCommands.commands.SyroMessageCommand;
//import syrocraft.utilityCommands.commands.temp_command;
import syrocraft.utilityCommands.tabCompleters.ClaimVoteRewardsTabCompleter;
import syrocraft.utilityCommands.tabCompleters.GetBossDropTabCompleter;
import syrocraft.utilityCommands.tabCompleters.GetVoteDropTabCompleter;
import syrocraft.utilityCommands.tabCompleters.GetWishingWellDropTabCompleter;
import syrocraft.utilityCommands.tabCompleters.SeenListTabCompleter;
import syrocraft.weaponEffects.listeners.DamageDealListener;
import syrocraft.weaponEffects.listeners.DamageTakeListener;
import syrocraft.weaponEffects.listeners.EntityCombustListener;
import syrocraft.weaponEffects.listeners.EntityPotionEffectListener;
import syrocraft.weaponEffects.listeners.PlayerInteractListener;
import syrocraft.weaponEffects.listeners.ProjectileHitListener;
import syrocraft.wishingWell.commands.CreateDropCommand;
import syrocraft.wishingWell.commands.GetDropCommand;
import syrocraft.wishingWell.commands.RemoveDropCommand;
import syrocraft.wishingWell.commands.RemoveDropTableCommand;
import syrocraft.wishingWell.commands.CreateDropTableCommand;
import syrocraft.wishingWell.tabCompleters.CreateDropTabCompleter;
import syrocraft.wishingWell.tabCompleters.GetDropTabCompleter;
import syrocraft.wishingWell.tabCompleters.RemoveDropTabCompleter;
import syrocraft.wishingWell.tabCompleters.RemoveDropTableTabCompleter;
import syrocraft.wishingWell.tabCompleters.CreateDropTableTabCompleter;

public class Main extends JavaPlugin implements Listener {
	Logger SyrocraftMasterLogger = Bukkit.getLogger();

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new LoginEventListener(this), this);//statistic login event listener
		Bukkit.getServer().getPluginManager().registerEvents(new LogoutEventListener(this), this);//statistic logout event listener
		Bukkit.getServer().getPluginManager().registerEvents(new DamageDealListener(this), this);//damage deal event listener
		Bukkit.getServer().getPluginManager().registerEvents(new DamageTakeListener(this), this);//damage take event listener
		Bukkit.getServer().getPluginManager().registerEvents(new EntityPotionEffectListener(this), this);//potion effect event listener
		Bukkit.getServer().getPluginManager().registerEvents(new EntityCombustListener(this), this);//entity combust event listener
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);//player interact event listener
		Bukkit.getServer().getPluginManager().registerEvents(new ProjectileHitListener(this), this);//projectile hit event listener
		SyrocraftMasterLogger.info("SyrocraftMaster is being loaded!");
		
		//==============
		//=Wishing Well=
		//==============

		//Commands
		this.getCommand("CreateDropTable").setExecutor(new CreateDropTableCommand(this));//Create Drop Table Command
		this.getCommand("CreateDrop").setExecutor(new CreateDropCommand(this));//Create Drop Command
		this.getCommand("RemoveDropTable").setExecutor(new RemoveDropTableCommand(this));//Remove Drop Table Command
		this.getCommand("RemoveDrop").setExecutor(new RemoveDropCommand(this));//Remove Drop Command
		this.getCommand("GetDrop").setExecutor(new GetDropCommand(this));//Get Drop Command
		//Tab Completers
		this.getCommand("CreateDropTable").setTabCompleter(new CreateDropTableTabCompleter(this));//Create Drop Table Command
		this.getCommand("CreateDrop").setTabCompleter(new CreateDropTabCompleter(this));//Create Drop Command
		this.getCommand("RemoveDropTable").setTabCompleter(new RemoveDropTableTabCompleter(this));//Remove Drop Table Command
		this.getCommand("RemoveDrop").setTabCompleter(new RemoveDropTabCompleter(this));//Remove Drop Command
		this.getCommand("GetDrop").setTabCompleter(new GetDropTabCompleter(this));//Get Drop Command

		//=============
		//=Expeditions=
		//=============

		//Commands
		this.getCommand("Expeditions").setExecutor(new ExpeditionsCommand(this));//Expeditions Command
		this.getCommand("CreateExpedition").setExecutor(new CreateExpeditionCommand(this));//create expedtion command
		this.getCommand("EnableExpedition").setExecutor(new EnableExpeditionCommand(this));//enable expedition command
		this.getCommand("DisableExpedition").setExecutor(new DisableExpeditionCommand(this));//disable expedition command
		this.getCommand("CreateExpeditionTask").setExecutor(new CreateExpeditionTaskCommand(this));//create expedition task command
		this.getCommand("RemoveExpedition").setExecutor(new RemoveExpeditionCommand(this));//Remove Expedition Command
		this.getCommand("RemoveExpeditionTask").setExecutor(new RemoveExpeditionTaskCommand(this));//Remove Expedition Task Command
		this.getCommand("StartExpedition").setExecutor(new StartExpeditionCommand(this));//Start Expedition Command
		this.getCommand("GetExpeditionTask").setExecutor(new GetExpeditionTaskCommand(this));//Get Expedition Task Command
		this.getCommand("GetExpeditionInfo").setExecutor(new GetExpeditionInfoCommand(this));//Get Expedition Info Command
		this.getCommand("SetExpeditionOrder").setExecutor(new SetExpeditionOrderCommand(this));//Set Expedition Order Command

		//Tab Completers
		this.getCommand("CreateExpedition").setTabCompleter(new CreateExpeditionTabCompleter(this));//create expedtion command
		this.getCommand("EnableExpedition").setTabCompleter(new EnableExpeditionTabCompleter(this));//enable expedition command
		this.getCommand("DisableExpedition").setTabCompleter(new DisableExpeditionTabCompleter(this));//disable expedition command
		this.getCommand("CreateExpeditionTask").setTabCompleter(new CreateExpeditionTaskTabCompleter(this));//create expedition task command
		this.getCommand("RemoveExpedition").setTabCompleter(new RemoveExpeditionTabCompleter(this));//Remove Expedition Command
		this.getCommand("RemoveExpeditionTask").setTabCompleter(new RemoveExpeditionTaskTabCompleter(this));//Remove Expedition Task Command
		this.getCommand("StartExpedition").setTabCompleter(new StartExpeditionTabCompleter(this));//Start Expedition Command
		this.getCommand("GetExpeditionTask").setTabCompleter(new GetExpeditionTaskTabCompleter(this));//Get Expedition Task Command
		this.getCommand("GetExpeditionInfo").setTabCompleter(new GetExpeditionInfoTabCompleter(this));//Get Expedition Info Command
		this.getCommand("SetExpeditionOrder").setTabCompleter(new SetExpeditionOrderTabCompleter(this));//Set Expedition Order Command
		
		//============
		//=Statistics=
		//============
		
		//Commands
		this.getCommand("AddStatistic").setExecutor(new AddStatisticCommand(this));//Add Statistic Command
		this.getCommand("RemoveStatistic").setExecutor(new RemoveStatisticCommand(this));//Remove Statistic Command
		this.getCommand("GetStatistic").setExecutor(new GetStatisticCommand(this));//Get Statistic Command
		this.getCommand("SetStatistic").setExecutor(new SetStatisticCommand(this));//Set Statistic Command
		this.getCommand("Playtime").setExecutor(new PlaytimeCommand(this));//Playtime Command
		
		//Tab Completers
		this.getCommand("AddStatistic").setTabCompleter(new AddStatisticTabCompleter(this));//Add Statistic Command
		this.getCommand("RemoveStatistic").setTabCompleter(new RemoveStatisticTabCompleter(this));//Remove Statistic Command
		this.getCommand("GetStatistic").setTabCompleter(new GetStatisticTabCompleter(this));//Get Statistic Command
		this.getCommand("SetStatistic").setTabCompleter(new SetStatisticTabCompleter(this));//Set Statistic Command
		this.getCommand("Playtime").setTabCompleter(new PlaytimeTabCompleter(this));//Playtime Command
		
		//========
		//=Titles=
		//========
		
		//Commands
		this.getCommand("CreateTitle").setExecutor(new CreateTitleCommand(this));//Create Title Command
		this.getCommand("SetTitle").setExecutor(new SetTitleCommand(this));//Set Title Command
		this.getCommand("AddTitleRequirement").setExecutor(new AddTitleRequirementCommand(this));//Add Title Requirement Command
		this.getCommand("UnlockedTitles").setExecutor(new UnlockedTitlesCommand(this));//Unlocked Titles Command
		this.getCommand("LockedTitles").setExecutor(new LockedTitlesCommand(this));//Locked Titles Command
		this.getCommand("RemoveTitle").setExecutor(new RemoveTitleCommand(this));//Remove Title Command
		this.getCommand("RemoveTitleRequirement").setExecutor(new RemoveTitleRequirementCommand(this));//Remove Title Requirement Command
		
		//Tab Completers
		this.getCommand("CreateTitle").setTabCompleter(new CreateTitleTabCompleter(this));//Create Title Command
		this.getCommand("AddTitleRequirement").setTabCompleter(new AddTitleRequirementTabCompleter(this));//Set Title Command
		this.getCommand("SetTitle").setTabCompleter(new SetTitleTabCompleter(this));//Add Title Requirement Command
		this.getCommand("UnlockedTitles").setTabCompleter(new UnlockedTitlesTabCompleter(this));//Unlocked Titles Command
		this.getCommand("LockedTitles").setTabCompleter(new LockedTitlesTabCompleter(this));//Locked Titles Command
		this.getCommand("RemoveTitle").setTabCompleter(new RemoveTitleTabCompleter(this));//Remove Title Command
		this.getCommand("RemoveTitleRequirement").setTabCompleter(new RemoveTitleRequirementTabCompleter(this));//Remove Title Requirement Command
		
		//=========
		//=Utility=
		//=========
		
		//Commands
		this.getCommand("GetVoteDrop").setExecutor(new GetVoteDropCommand(this));//Get Vote Drop Command
		this.getCommand("ClaimVoteRewards").setExecutor(new ClaimVoteRewardsCommand(this));//Claim Vote Rewards Command
		this.getCommand("GetWishingWellDrop").setExecutor(new GetWishingWellDropCommand(this));//Get Wishing Well Drop Command
		this.getCommand("GetBossDrop").setExecutor(new GetBossDropCommand(this));//Get Boss Drop Command
		
		this.getCommand("SyroExecute").setExecutor(new SyroExecuteCommand(this));//Syro Execute Command
		this.getCommand("SeenList").setExecutor(new SeenListCommand(this));//Seen List Command
		this.getCommand("SyroMessage").setExecutor(new SyroMessageCommand(this));
		this.getCommand("SetFlag").setExecutor(new SetFlagCommand(this));
		this.getCommand("TestFlag").setExecutor(new TestFlagCommand(this));
		//this.getCommand("ParticleSphere").setExecutor(new ParticleSphereCommand(this));//Particle Sphere Command
		//this.getCommand("temp").setExecutor(new temp_command(this));//Seen List Command
		
		//Tab Completers
		this.getCommand("GetVoteDrop").setTabCompleter(new GetVoteDropTabCompleter(this));//Get Vote Drop Command
		this.getCommand("ClaimVoteRewards").setTabCompleter(new ClaimVoteRewardsTabCompleter(this));//Claim Vote Rewards Command
		this.getCommand("GetWishingWellDrop").setTabCompleter(new GetWishingWellDropTabCompleter(this));//Get Wishing Well Drop Command
		this.getCommand("GetBossDrop").setTabCompleter(new GetBossDropTabCompleter(this));//Get Boss Drop Command
		this.getCommand("SeenList").setTabCompleter(new SeenListTabCompleter(this));//Seen List Command
		this.getCommand("SetFlag").setTabCompleter(new SetFlagTabCompleter(this));//Seen List Command
		this.getCommand("TestFlag").setTabCompleter(new TestFlagTabCompleter(this));
		
		//==============
		//=Async Timers=
		//==============
		Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable(){
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){//loop through all players
					StatisticsUtil.UpdatePlaytime(p);//Update players playtime statistic
				}
				System.out.println("Saved Stats.");
			}
		}
		, 12000, 12000);//20 ticks per second, 12000 = 10 minutes.
	}

	@Override
	public void onDisable() {
		SyrocraftMasterLogger.info("SyrocraftMaster is being disabled!");
	}
}