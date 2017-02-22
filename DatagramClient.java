package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class DatagramClient{
	static final String DEFAULT_GROUP = "239.1.2.3";
	static final int DEFAULT_PORT = 1234;
	static final int DEFAULT_TTL = 1;
	public static void main(String[] args){
        try{
            Socket master = new Socket("192.168.202.117", 4844);
            new Thread(new InputHandler(master)).start();
            new Thread(new OuputHandler(master)).start();
            String groupStr = (args.length > 0) ? args[0] : DEFAULT_GROUP;
            InetAddress group = InetAddress.getByName (groupStr);
            int port = (args.length > 1) ? Integer.parseInt (args[1]) : DEFAULT_PORT;
            int ttl = (args.length > 2) ? Integer.parseInt (args[2]) : DEFAULT_TTL;
            MulticastChat chat = new MulticastChat (group, port, ttl);
            chat.start ();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Scanner input = new Scanner(System.in);
    }
    public static class InputHandler implements Runnable{
        private Socket socket;
        public InputHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            boolean commune = true;
            BufferedReader reader = null;
            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (commune){
                    String text = reader.readLine();
                    System.out.println("\n<server> " + text);
                    if (text.toLowerCase().equals("bye")){
                        commune = false;
                    }
                }
            }catch (Exception exp){
                exp.printStackTrace();
            }finally{
                try{
                    reader.close();
                }catch (Exception e){
                }
                try{
                    socket.close();
                } catch (Exception e){
                }
            }
        }
    }
    public static class OuputHandler implements Runnable{
        private Socket socket;
        public OuputHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            boolean commune = true;
            BufferedWriter writer = null;
            try{
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner scanner = new Scanner(System.in);
                while (commune){
                    System.out.print("> ");
                    String text = scanner.nextLine();
                    writer.write(text);
                    writer.newLine();
                    writer.flush();
                    if (text.equalsIgnoreCase("bye")){
                        commune = false;
                    }
                }
            }catch (Exception exp){
                exp.printStackTrace();
            }finally{
                try{
                    writer.close();
                }catch (Exception e){
                }
                try{
                    socket.close();
                }catch (Exception e){
                }
            }
        }
    }
}