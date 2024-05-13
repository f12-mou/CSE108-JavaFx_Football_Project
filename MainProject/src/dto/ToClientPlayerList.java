package dto;

import data.Players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToClientPlayerList implements Serializable {
    private List<Players>playersList;
    public List<Players> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }

    public ToClientPlayerList()
    {
        playersList=new ArrayList<>();
    }
}
