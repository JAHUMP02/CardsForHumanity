package com.example;

public class President{
	private int players;
	public President(int p){
		int czar = (int)(Math.random()*p);
		currentczar = czar;
		players=p;
	}
	private int currentczar = 0;
	public int nextczar(){
		currentczar++;
		if(currentczar > players-1){
			currentczar = 0;
		}
		return currentczar;
	}
}
