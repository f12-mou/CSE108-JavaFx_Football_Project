package dto;

import data.Players;

import java.io.Serializable;

public class SendPlayer implements Serializable {
    String name;
    String taka;
    Players player;
    public SendPlayer()
    {
        player=new Players();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    String clientName;

    public String getTaka() {
        return taka;
    }

    public void setTaka(String taka) {
        this.taka = taka;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }
}
