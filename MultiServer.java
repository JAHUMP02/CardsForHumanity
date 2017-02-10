package com.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MultiServer extends Thread{
	private ArrayList<Server> serverThreads=new ArrayList<Server>();
	public void run(ServerSocket socket){
		ForHumanity fh=new ForHumanity();
		Scanner in=new Scanner(System.in);
		try{
		boolean isInterrupted=false;
		while(!isInterrupted){
			Server thread=new Server(socket.accept());
			serverThreads.add(thread);
			System.out.println(serverThreads.size());
			if(in.nextBoolean()==true){
				isInterrupted=true;
			}
		}
		int[] score = new int[serverThreads.size()];
		for(int i=0; i<score.length; i++){
			score[i]=0;
		}
		boolean acceptable=true;
		int finalscore;
		while(acceptable == true){
			System.out.println("What is the score you will be trying to reach?");
			finalscore = in.nextInt();
			if(finalscore>0){
				acceptable=false;
			}else{
				System.out.println("Input a higher score");
			}
		}
		fh.shuffle();
		Card[][] playercards=new Card[serverThreads.size()][7];
		for(int i = 0; i < playercards.length; i++){
			for(int j = 0; j < playercards[0].length; j++){
				playercards[i][j]=new Card();
				playercards[i][j]=fh.deal();
			}
		}
		President prez=new President(serverThreads.size());
		
		boolean winner=false;
		while(winner==false){
			int prezi=prez.nextczar(serverThreads.size());
			int[] inputs=new int[serverThreads.size()];
			for(Server s:serverThreads){
				s.OutputAll();
			}
			System.out.println("Enter number of client");
			serverThreads.get(in.nextInt()).OutputAll();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doAll(int out, Socket ss) throws IOException{
		System.out.print("h");
		Server s=serverThreads.get(out);
		System.out.print("b");
			
			System.out.print("l");
			
	}
}
