package com.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MultiServer extends Thread{
	private ArrayList<Server> serverThreads=new ArrayList<Server>();
	public Runnable run(ServerSocket socket){
		while(true){
		ForHumanity fh=new ForHumanity();
		
		Scanner in=new Scanner(System.in);
		try{
		boolean isInterrupted=false;
		Socket ss=socket.accept();
		int num=0;
		ArrayList<String> names=new ArrayList<String>();
		while(!isInterrupted){
			Server thread=new Server(ss, num);
			serverThreads.add(thread);
			System.out.println(serverThreads.size());
			if(in.nextBoolean()==true){
				isInterrupted=true;
			}
			num++;
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
		
		int[] choice=new int[serverThreads.size()];
		
		boolean winner=false;
		while(winner==false){
			
			int[] inputs=new int[serverThreads.size()];
			for(Server s:serverThreads){
				s.OutputAll("Welcome");
			}
			//for(Server s:serverThreads){
			//	s.OutputAll("Enter name");
			//	names.add(s.inputCatcher());
			//}
			for(String s:names){
				System.out.println(s);
			}
			String blackCard=fh.dealBlack().getItems();
			for(Server s:serverThreads){
				s.OutputAll(blackCard);
			}
			for(int i=0; i<serverThreads.size(); i++){
				//if(prez.nextczar()==i){
					
				//}else{
				Server s=serverThreads.get(i);
				for(int j=0; j<playercards[0].length; j++){
					s.OutputAll(j+": "+playercards[i][j].getItems());
				}
				//}
			}
			for(int i=0; i<serverThreads.size(); i++){
				//if(prez.nextczar()==i){
				//}else{
				Server s=serverThreads.get(i);
				s.OutputAll("Input your choice");
				System.out.println("l");
				String string=s.inputCatcher();
				System.out.println("HiLo");
				choice[i]=Integer.getInteger
						(string);
				System.out.println(string);
				//}
			}
			
			System.out.println("Enter number of client");
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	public void doAll(int out, Socket ss) throws IOException{
		System.out.print("h");
		Server s=serverThreads.get(out);
		System.out.print("b");
			
			System.out.print("l");
			
	}
}
