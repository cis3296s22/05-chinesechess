package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static Socket c1;
    public static DataInputStream in;
    public static DataOutputStream out;

    public Client(){
        // constructor
    }
    public boolean createClient(String ip){
        try{
            c1 = new Socket(ip, 1111);
            in = new DataInputStream(c1.getInputStream());
            out = new DataOutputStream(c1.getOutputStream());
        }catch(IOException e) {return false;}
        return true;
    }

    public String getIP(){
        Socket socket = new Socket();
        try {
            socket.connect( new InetSocketAddress("google.com", 80));
        } catch (IOException e) {
            System.out.println("lmao google broke");
            System.exit(0);
        }
        return socket.getLocalAddress().toString();
    }
    public void sendMsg(String msg){
        try{
            out.writeUTF(msg);
            System.out.println(msg);
        }catch (IOException e){System.out.println("client sent message error :(");}
    }

    public String receiveMsg(){
        String msg = "";
        try{
            msg = new String( in.readUTF() );
        } catch (IOException e) {System.out.println("Client receive msg error :((("); System.exit(0);}
        return msg;
    }
}
