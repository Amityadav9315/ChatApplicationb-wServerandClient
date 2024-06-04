import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    //constructor
    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting....");
             socket=server.accept();
             br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
             out=new PrintWriter(socket.getOutputStream());
             startReading();
             startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startReading(){
      //read kar ke deta rahega
        Runnable r1=()->{
            while(true){
                try{
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminate the chat");
                        break;
                    }
                    System.out.println("Client :" + msg);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
        new Thread(r1).start();
    }
    public void startWriting (){
       //thread data user lega and th senet karega client tak
        Runnable r2=()->{
            System.out.println("writer started");
            while(true){
                try {
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("This is Server");
        new Server();
    }
}
