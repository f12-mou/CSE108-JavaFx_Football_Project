package server;

import client.Client;
import data.Players;
import util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class ClientInfo {
    private String password;
    private boolean isOnline;
    private NetworkUtil networkUtil;
    private List<Players> playersList;
    private List<Double>priceList;
    private List<Players>soldPlayers;

    public List<Players> getSoldPlayers() {
        return soldPlayers;
    }

    public void setSoldPlayers(List<Players> soldPlayers) {
        this.soldPlayers = soldPlayers;
    }

    public List<Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Double> priceList) {
        this.priceList = priceList;
    }



    public List<Players> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }



    public ClientInfo()
    {
        playersList=new ArrayList<>();
        priceList=new ArrayList<>();
        soldPlayers=new ArrayList<>();
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }
}
