package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

public class Server {
	private Socket socketNum;
    public Server(Socket socket){
        try {
            new Thread(new InputHandler(socket)).start();
            socketNum=socket;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void OutputAll(String string){
    	BufferedReader reader=null;
    	  boolean commune = true;
          BufferedWriter writer = null;
          System.out.print("j");
          try {
        	  System.out.print("n");
              writer = new BufferedWriter(new OutputStreamWriter(socketNum.getOutputStream()));
              Scanner scanner = new Scanner(System.in);
              
                  System.out.print("> ");
                  String text = scanner.nextLine();
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
    public static class InputHandler implements Runnable {

        private Socket socket;

        public InputHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            boolean commune = true;
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (commune) {
                    String text = reader.readLine();
                    System.out.println("\n<client> " + text);
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
}