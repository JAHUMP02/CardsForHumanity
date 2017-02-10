package com.example;

public class Card {
	//false is white
	//true is black
	private boolean type;
	private String items;
	public void setType(String s){
		if(s.equals("white")){
			type=false;
		}else{
			type=true;
		}
	}
	public void setWords(String s){
		items=s;
	}
	public boolean getType(){
		return type;
	}
	public String getItems(){
		return items;
	}
}
