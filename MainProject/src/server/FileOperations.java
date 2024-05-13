package server;

import data.Clubs;
import data.Country;
import data.Players;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    private static final String INPUT_FILE_NAME="players.txt";
    private static final String OUTPUT_FILE_NAME="players.txt";
    private static List<Clubs> clubs=new ArrayList();
    private static List<Country> countryList=new ArrayList();

    public static List<Players> read() throws Exception{
        BufferedReader bf=new BufferedReader(new FileReader(INPUT_FILE_NAME));
        List<Players> playerlist=new ArrayList();
        while(true)
        {
            String line=bf.readLine();
            if(line==null)
                break;
            String[] tokens=line.split(",");
            Players p=new Players();
            p.SetName(tokens[0]);
            p.SetCountry(tokens[1]);
            p.SetAge(Integer.parseInt(tokens[2]));
            p.SetHeight(Double.parseDouble(tokens[3]));
            p.SetClub(tokens[4]);
            p.SetPosition(tokens[5]);
            p.SetNumber(Integer.parseInt(tokens[6]));
            p.SetSalary(Double.parseDouble(tokens[7]));
            playerlist.add(p);
            int mark=0;
            int flag=0;
            Clubs c=null;
            for(int i=0;i<clubs.size();i++)
            {
                c=clubs.get(i);
                if(tokens[4].equalsIgnoreCase(c.GetName()))
                {
                    flag=1;
                    mark=i;
                    break;
                }
            }
            if (flag==1)
            {
                c=clubs.get(mark);
                c.addPlayers(p);
            }
            else
            {
                clubs.add(new Clubs(tokens[4]));
                c=clubs.get(clubs.size()-1);
                c.addPlayers(p);
            }
            mark=0;
            flag=0;
            for(int i=0;i<countryList.size();i++)
            {
                Country cc=null;
                cc=countryList.get(i);
                if(tokens[1].equalsIgnoreCase(cc.GetName()))
                {
                    flag=1;
                    mark=i;
                    break;
                }
            }
            if(flag==1)
            {
                Country cc=countryList.get(mark);
                cc.AddClubs(c,p);
            }
            else
            {
                countryList.add(new Country(tokens[1]));
                Country cc=countryList.get(countryList.size()-1);
                cc.AddClubs(c,p);
            }
        }
        bf.close();
        return playerlist;
    }
    public static List<Clubs> readClubs() throws Exception {
        return clubs;
    }
    public static List<Country> readCountry() throws Exception {

        return countryList;
    }

    public static void write(List<Players>playerList) throws Exception {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("players.txt"));
        writer.write("");
        writer.flush();
        BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
        for(Players p:playerList)
        {
            bw.write(p.GetName()+","+p.GetCountry()+","+(p.GetAge())+","+(p.GetHeight())+","+p.GetClub()+","+p.GetPosition()+","+p.GetNumber()+","+(p.GetSalary()));
            bw.write("\n");
            System.out.println(p.GetName()+" "+p.GetClub());
        }
        bw.close();
    }
}


