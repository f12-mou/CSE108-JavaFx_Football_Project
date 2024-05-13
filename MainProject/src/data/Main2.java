package data;


import java.util.List;
import java.util.Scanner;
public class Main2 {
   private static List<Players> playerList;
   public static List<Players> getPlayerList()
   {
       return playerList;
   }

    public static void main2() throws Exception {
       playerList=FileOperations.read();
        List<Clubs>clubList=FileOperations.readClubs();
        List<Country>countryList=FileOperations.readCountry();
            System.out.println("Main Menu :");
            System.out.println("(1) Search Players");
            System.out.println("(2) Search Clubs");
            System.out.println("(3) Add Player");
            System.out.println("(4) Exit System");
            int option;
            String dummy;
            Scanner scanner2 = new Scanner(System.in).useDelimiter("\n");
            dummy=scanner2.next();
            while(Queries.check(dummy.trim(),2)==false)
            {
                System.out.println("ENTER CORRECT OPTION PLEASE");
                dummy=scanner2.next();
            }
            option=Integer.parseInt(dummy.trim());
            if(option<1 || option>4)
            {
                System.out.println("ENTER ANY CORRECT OPTION PLEASE ");
            }
            if(option==1)
            {
                Queries.point1(playerList,clubList,countryList);
            }
            if(option==2)
            {
                Queries.point2(playerList,clubList,countryList);
            }
            if(option==3)
            {
                int previous=playerList.size();
                playerList= Queries.point3(playerList,clubList,countryList);
                if(playerList.size()>previous)
                {
                    clubList = Queries.point31(playerList.get(playerList.size() - 1), clubList, countryList);
                    countryList = Queries.point32(playerList.get(playerList.size() - 1), clubList, countryList);
                }
            }
            if(option==4)
            {
                for(int i=0;i<playerList.size();i++)
                {
                    if(playerList.get(i).getPictureState()==true)
                        System.out.println(playerList.get(i).GetName()+" has a picture");
                }
                FileOperations.write(playerList);
                System.exit(0);
            }

    }
}

