package server;

import data.Players;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, ClientInfo> clientMap;
    private static List<Players> playerList=new ArrayList<>();

    Server() {
        clientMap = new HashMap<>();

        try {
            serverSocket = new ServerSocket(33333);
            try {
                playerList=FileOperations.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("server.Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(playerList,clientMap, networkUtil);
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
