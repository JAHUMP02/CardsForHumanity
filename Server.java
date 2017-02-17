package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class Server {
	private Socket socketNum;
    public Server(Socket socket, int i){
        try {
            socketNum=socket;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void OutputAll(String string){
    	BufferedReader reader=null;
    	  boolean commune = true;
          BufferedWriter writer = null;
          try {
              writer = new BufferedWriter(new OutputStreamWriter(socketNum.getOutputStream()));
              Scanner scanner = new Scanner(System.in);
                  String text = string;
                  writer.write(text);
                  writer.newLine();
                  writer.flush();
                  if (text.equalsIgnoreCase("bye")) {
                      commune = false;
                  }
              
          } catch (Exception exp) {
              exp.printStackTrace();
          } 
    }
    public String inputCatcher(){
		 boolean commune = true;
        BufferedReader reader = null;
        try {
        	
            reader = new BufferedReader(new InputStreamReader(socketNum.getInputStream()));
            String text=null;
            while(text==null){
                text = reader.readLine();
            }
                return text;
            
        } catch (Exception exp) {
            exp.printStackTrace();
        } 
        
        return "Failure in the INPUT CATCHER";
    }
    public static class InputHandler implements Runnable {

        private Socket socket;
        
        private int num=0;

        public InputHandler(Socket socket, int i) {
            this.socket = socket;
            num=i;
        }

        @Override
        public void run() {
            boolean commune = true;
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (commune) {
                    String text = reader.readLine();
                    System.out.println("\n<client "+num+"> " + text);
                    if (text.toLowerCase().equals("bye")) {
                        commune = false;
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (Exception e) {
                }
                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }
    public static class OuputHandler implements Runnable {

        private Socket socket;

        public OuputHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            boolean commune = true;
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner scanner = new Scanner(System.in);
                while (commune) {
                    System.out.print("> ");
                    String text = scanner.nextLine();
                    writer.write(text);
                    writer.newLine();
                    writer.flush();
                    if (text.equalsIgnoreCase("bye")) {
                        commune = false;
                    }
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {
                }
                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }
    public class Input{
    	
    	}
    
}