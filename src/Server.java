package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket s;
    public static Socket s1;
    public static DataInputStream in;
    public static DataOutputStream out;

    public Server(){
        //constructor
        try{
            s = new ServerSocket(1111);
            s1 = s.accept();
            in = new DataInputStream(s1.getInputStream());
            out = new DataOutputStream(s1.getOutputStream());
        }catch (IOException e){System.out.println("Server connection error :(((((");}
    }

    public void sendMsg(String msg){
        try{
            out.writeUTF(msg);
        } catch (IOException e) {System.out.println("Server send message error");}
    }
    public String receiveMsg(){
        String msg = "";
        try{
            msg = new String(in.readUTF());
        }catch(IOException e){System.out.println("Server receiveing msg error");}
        return msg;
    }
}
