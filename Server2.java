package com.example;

import java.net.*;
import java.util.Scanner;

public class Server2 extends Thread{
	static final String DEFAULT_GROUP = "239.1.2.3";
	  static final int DEFAULT_PORT = 1234;
	  static final int DEFAULT_TTL = 1;
	public static void main(String[] args){
		try{
			ServerSocket socket=new ServerSocket(4844);
			MultiServer ms=new MultiServer();
			String groupStr = (args.length > 0) ? args[0] : DEFAULT_GROUP;
		    InetAddress group = InetAddress.getByName (groupStr);
		    int port = (args.length > 1) ? Integer.parseInt (args[1]) : DEFAULT_PORT;
		    int ttl = (args.length > 2) ? Integer.parseInt (args[2]) : DEFAULT_TTL;
		    MulticastChat chat = new MulticastChat (group, port, ttl);
		    new Thread(chat.start());
			new Thread(ms.run(socket));
			Scanner input=new Scanner(System.in);
			boolean commune=true;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
