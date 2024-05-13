package dto;

import data.Players;

import java.io.Serializable;

public class BuyPlayers implements Serializable {
    private String name;
    Players p;
    public Players getP() {
        return p;
    }

    public void setP(Players p) {
        this.p = p;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BuyPlayers()
    {

    }


}
