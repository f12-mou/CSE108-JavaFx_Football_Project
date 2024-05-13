package client;

import util.NetworkUtil;

public class Client {

    private NetworkUtil networkUtil;
    public Client(String serverAddress, int serverPort) {
        try {
            networkUtil = new NetworkUtil(serverAddress, serverPort);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public NetworkUtil getNetworkUtil()
    {
        return networkUtil;
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort);
    }

}


