package com.example;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatagramClient extends JFrame{
	static final String DEFAULT_GROUP = "239.1.2.3";
	static final int DEFAULT_PORT = 1234;
	static final int DEFAULT_TTL = 1;
	 private static JFrame jf=new JFrame();
	 private static JOptionPane jop=new JOptionPane();
     private JButton b1;
     private JButton b2;
     private JButton b3;
     private JButton b4;
     private JButton b5;
     private JButton b6;
     private JButton b7;
     
     private static JFrame jf1=new JFrame();
     private JButton c1;
     private JButton c2;
     private JButton c3;
     private JButton c4;
     private JButton c5;
     private JButton c6;
     private JButton c7;
     
     protected static ArrayList<JButton> jal1=new ArrayList<JButton>();
     protected static ArrayList<JButton> jal=new ArrayList<JButton>();
	public DatagramClient(){
		jf.setTitle("Hi");
    	jf.setLayout(new FlowLayout());
    	jf.setSize(300, 800);
    	jf.setLocation(100, 800);
    	
    	 jal.add(b1);
         jal.add(b2);
         jal.add(b3);
         jal.add(b4);
         jal.add(b5);
         jal.add(b6);
         jal.add(b7);
         
        jf1.setTitle("User Inputs");
        jf1.setLayout(new FlowLayout());
        jf1.setSize(300, 300);
        
        jal1.add(c1);
        jal1.add(c2);
        jal1.add(c3);
        jal1.add(c4);
        jal1.add(c5);
        jal1.add(c6);
        jal1.add(c7);
        
	}
	public static void main(String[] args){
		DatagramClient dg=new DatagramClient();
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
    public static class InputHandler extends JFrame implements Runnable{
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
                    String[] textArray=text.split(" ", 1);
                    if(textArray[0].equals("0")){
                    	String[] ta=textArray[1].split(" ", 1);
                    	jal.set(Integer.parseInt(ta[0]), new JButton(ta[1]));
                    }else{
                    	if(textArray[0].equals("1")){
                    		jop.showMessageDialog(null, "Black Card: "+textArray[1]);
                    	}else{
                    		if(textArray[0].equals("2")){
                    		jop.showMessageDialog(null, textArray[1]);
                    		}else{
                    			if(textArray[0].equals("3")){
                    				jop.showInputDialog(null, textArray[1]);
                    			}else{
                    				if(textArray[0].equals("4")){
                    					jal1.set(Integer.parseInt(textArray[1].substring(0, 1)), new JButton(textArray[1]));
                    					
                    				}else{
                    					if(textArray[0].equals("5")){
                    						jf1.setVisible(true);
                    					}else{
                    						if(textArray[0].equals("6")){
                    							jf1.setVisible(false);
                    						}
                    					}
                    				}
                    			}
                    		}
                    	}
                    }
                    for(JButton b: jal){
                    	add(b);
                    }
                    
                    System.out.println("\n<server> " + text);
                    if (text.toLowerCase().equals("bye")){
                        commune = false;
                    }
                   
                }
                jf.setVisible(false);
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
    public static class OuputHandler implements Runnable, ActionListener{
        private Socket socket;
        public OuputHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            boolean commune = true;
            BufferedWriter writer = null;
            try{
           
                Scanner scanner = new Scanner(System.in);
                while (commune){
                    for(JButton b: jal){
                    	b.addActionListener(this);
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
		@Override
		public void actionPerformed(ActionEvent e){
			BufferedWriter writer=null;
			try{
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			for(JButton b:jal){
				if(e.getSource().equals(b)){
					writer.write(b.getText());
				}
			}
			for(JButton b: jal1){
				if(e.getSource().equals(b)){
					writer.write(b.getText());
					jf1.setVisible(false);
				}
			}
			
		}catch(Exception exp){
			exp.printStackTrace();
		}
		}
    }
}
