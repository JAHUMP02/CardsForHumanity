package com.example;

public class President{
	public President(int p){
		int czar = (int)(Math.random()*p);
		currentczar = czar;
	}
	private int currentczar = 0;
	public int nextczar(int p){
		currentczar++;
		if(currentczar > p-1){
			currentczar = 0;
		}
		return currentczar;
	}
}
