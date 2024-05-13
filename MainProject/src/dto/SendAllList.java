package dto;

import data.Players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SendAllList implements Serializable {
    List<Players> playersList;

    public List<Players> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }

    public SendAllList()
    {
        playersList=new ArrayList<>();
    }
}
