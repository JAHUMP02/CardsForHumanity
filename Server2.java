package com.example;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 {
	public static void main(String[] args){
		try{
			ServerSocket socket=new ServerSocket(4743);
			MultiServer ms=new MultiServer();
			ms.run(socket);
			
			Scanner input=new Scanner(System.in);
			boolean commune=true;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
