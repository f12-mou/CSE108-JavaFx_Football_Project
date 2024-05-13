package data;
import java.util.ArrayList;
import java.util.List;

public class Clubs {
    private String name;
    private Players[] players;
    private int playerCount;

    public Clubs(String token) {
        name=token;
        players=new Players[100];
    }
    public Clubs()
    {
        players=new Players[100];
    }
    public void SetName(String name)
    {
        this.name=name;
    }
    public void addPlayers(Players p)
    {
        players[playerCount]=p;
        playerCount=playerCount+1;
    }
    public String GetName()
    {
        return name;
    }
    public int GetPlayerCount()
    {
        return playerCount;
    }
    public List<Players> GetMaxSalary()
    {
        List<Players>players2=new ArrayList();
        double max=0;
        for(int i=0;i<playerCount;i++)
        {
            if(max<players[i].GetSalary())
            {
                max=players[i].GetSalary();
            }
        }
        for(int i=0;i<playerCount;i++)
        {
            if(max==players[i].GetSalary())
            {
                players2.add(players[i]);
            }
        }
        return players2;
    }
    public List<Players> GetMaxAge()
    {
        List<Players>players2=new ArrayList();
        int max=0;
        for(int i=0;i<playerCount;i++)
        {
            if(max<players[i].GetAge())
            {
                max=players[i].GetAge();
            }
        }
        for(int i=0;i<playerCount;i++)
        {
            if(max==players[i].GetAge())
            {
                players2.add(players[i]);
            }
        }
        return players2;
    }
    public List<Players> GetMaxHeight()
    {
        List<Players>players2=new ArrayList();
        double max=0;
        for(int i=0;i<playerCount;i++)
        {
            if(max<players[i].GetHeight())
            {
                max=players[i].GetHeight();
            }
        }
        for(int i=0;i<playerCount;i++)
        {
            if(max==players[i].GetHeight())
            {
                players2.add(players[i]);
            }
        }
        return players2;
    }
    public double GetYearlySalary()
    {
        double total_salary=0;
        for(int i=0;i<playerCount;i++)
        {
            total_salary=total_salary+players[i].GetSalary();
        }
        return 52*total_salary;
    }
    public void Print_Players()
    {
        for(int i=0;i<playerCount;i++)
        {
            players[i].print();
        }
    }
    public boolean check_number(int a)
    {
        int flag=0;
        for(int i=0;i<playerCount;i++)
        {
            if(players[i].GetNumber()==a)
            {
                flag=1;
                break;
            }
        }
        if(flag==0)
            return true;
        else
            return false;
    }
}


