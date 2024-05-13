package data;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Queries {
    static Scanner scanner2 = new Scanner(System.in).useDelimiter("\n");
    public  static void point1(List<Players> playerList,List<Clubs>clubsList,List<Country>countryList) {
        while (true) {
            int option2;
            String[] s={"NAME","CLUB AND COUNTRY","POSITION","SALARY RANGE"};
            System.out.println("Player Searching Options:");
            System.out.println("(1) By Player Name");
            System.out.println("(2) By Club and Country");
            System.out.println("(3) By Position");
            System.out.println("(4) By Salary Range");
            System.out.println("(5) Country-wise player count");
            System.out.println("(6) Back to Main Menu");
            List<Players>players=new ArrayList();
            Players p = new Players();
            String dummy;
            dummy=scanner2.next();
            while(check(dummy.trim(),2)==false)
            {
                System.out.println("ENTER CORRECT OPTION PLEASE");
                dummy=scanner2.next();
            }
            option2=Integer.parseInt(dummy.trim());
            if (option2 > 6 || option2 < 1) {
                System.out.println("ENTER ANY VALID OPTION PLEASE : ");
                continue;
            }
            if (option2 == 1) {
                String name;
                name = scanner2.next();
                while (check(name.trim(), 0) == false) {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    name = scanner2.next();
                }
                players = p.SearchByName(name.trim(), playerList);
            }
            if (option2 == 2) {
                String clubName;
                String countryName;
                clubName = scanner2.next();
                countryName = scanner2.next();
                while (check(clubName.trim(), 0) == false || check(countryName.trim(), 0) == false) {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    clubName = scanner2.next();
                    countryName = scanner2.next();
                }
                clubName=clubName.trim();
                countryName=countryName.trim();
                if (clubName.equalsIgnoreCase("any")) {
                    Country c;
                    for (int i = 0; i < countryList.size(); i++) {
                        c = countryList.get(i);
                        if (countryName.equalsIgnoreCase(c.GetName())) {
                            c.PrintAllPlayers();
                        }
                    }
                    continue;
                }
                else if (countryName.equalsIgnoreCase("any")) {
                    Clubs c;
                    for (int i = 0; i < clubsList.size(); i++) {
                        c = clubsList.get(i);
                        if (clubName.equalsIgnoreCase(c.GetName())) {
                            c.Print_Players();
                        }
                    }
                    continue;
                }
                else {
                    players = p.SearchByTwo(clubName, countryName, playerList);
                }
            }
            if (option2 == 3) {
                String position;
                position = scanner2.next();
                while (check(position.trim(), 1) == false) {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    position = scanner2.next();
                }
                players = p.SearchByPosition(position.trim(), playerList);
            }
            if (option2 == 4) {
                String a, b;
                a = scanner2.next();
                b = scanner2.next();
                while (check(a.trim(), 2) == false || check(b.trim(), 2) == false) {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    a = scanner2.next();
                    b = scanner2.next();
                }
                players = p.SearchBySalary(Integer.parseInt(a.trim()), Integer.parseInt(b.trim()), playerList);
            }
            if (option2 == 5) {
                int total_forward=0;
                int total_mid=0;
                int total_goalkeeper=0;
                int total_defender=0;
                for(int i=0;i<playerList.size();i++)
                {
                    Players pp=playerList.get(i);
                    if(pp.GetPosition().equalsIgnoreCase("defender"))
                        total_defender=total_defender+1;
                    if(pp.GetPosition().equalsIgnoreCase("goalkeeper"))
                        total_goalkeeper=total_goalkeeper+1;
                    if(pp.GetPosition().equalsIgnoreCase("forward"))
                        total_forward=total_forward+1;
                    if(pp.GetPosition().equalsIgnoreCase("midfielder"))
                        total_mid=total_mid+1;

                }
                System.out.println("POSITION WISE LIST ARE :");
                System.out.println("DEFENDER : "+total_defender);
                System.out.println("FORWARD : "+total_forward);
                System.out.println("GOALKEEPER : "+total_goalkeeper);
                System.out.println("MIDFIELDER : "+total_mid);
            }
            if (option2 == 6)
                return;
            if (option2 > 0 && option2 < 5 && players.size()==0) {
                System.out.println("NO SUCH PLAYER WITH THIS "+s[option2-1]);
            }
            for (int i = 0; i < players.size(); i++) {
                players.get(i).print();
            }
        }
    }
    public static void point2(List<Players> playerList,List<Clubs>clubsList,List<Country>countryList) {
        while(true) {
            int option2;
            int i=0;
            String dummy;
            List<Players>players=new ArrayList();
            Scanner scanner2 = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Club Searching Options:");
            System.out.println("(1) Player(s) with the maximum salary of a club");
            System.out.println("(2) Player(s) with the maximum age of a club");
            System.out.println("(3) Player(s) with the maximum height of a club");
            System.out.println("(4) Total yearly salary of a club");
            System.out.println("(5) Back to Main Menu");
            Scanner scan = new Scanner(System.in);
            dummy=scanner2.next();
            while(check(dummy.trim(),2)==false)
            {
                System.out.println("ENTER ANY CORRECT OPTION.");
                dummy=scanner2.next();
            }
            option2=Integer.parseInt(dummy.trim());
            if(option2>5 || option2<1)
            {
                System.out.println("ENTER ANY VALID OPTION PLEASE : ");
                continue;
            }
            if (option2 == 1) {
                String clubName;
                clubName = scanner2.next();
                while(check(clubName.trim(),0)==false)
                {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    clubName = scanner2.next();
                }
                clubName=clubName.trim();
                for ( i = 0; i < clubsList.size(); i++) {
                    Clubs c = clubsList.get(i);
                    if ((c.GetName()).equalsIgnoreCase(clubName)) {
                        players=c.GetMaxSalary();
                    }
                }
            }
            if(option2==2)
            {
                String clubName;
                clubName = scanner2.next();
                while(check(clubName.trim(),0)==false)
                {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    clubName = scanner2.next();
                }
                clubName=clubName.trim();
                for ( i = 0; i < clubsList.size(); i++) {
                    Clubs c = clubsList.get(i);
                    if ((c.GetName()).equalsIgnoreCase(clubName)) {
                        players=c.GetMaxAge();
                    }
                }
            }
            if(option2==3)
            {
                String clubName;
                clubName = scanner2.next();
                while(check(clubName.trim(),0)==false)
                {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    clubName = scanner2.next();
                }
                clubName=clubName.trim();
                for ( i = 0; i < clubsList.size(); i++) {
                    Clubs c = clubsList.get(i);
                    if ((c.GetName()).equalsIgnoreCase(clubName)) {
                        players=c.GetMaxHeight();
                    }
                }
            }
            if(option2==4)
            {
                String clubName;
                clubName = scanner2.next();
                while(check(clubName,0)==false)
                {
                    System.out.println("ENTER THE INFORMATION CORRECTLY PLEASE");
                    clubName = scanner2.next();
                }
                clubName=clubName.trim();
                for ( i = 0; i < clubsList.size(); i++) {
                    Clubs c = clubsList.get(i);
                    if ((c.GetName()).equalsIgnoreCase(clubName)) {
                        System.out.println(c.GetYearlySalary());
                    }
                }
            }
            if(option2>0 && option2<4 && players.size()==0)
            {
                System.out.println("NO MATCHES ARE FOUND ACCORDING TO YOUR REQUEST");
            }
            if (option2 == 5)
                return;
            for (i = 0; i < players.size(); i++) {
                players.get(i).print();
            }
        }
    }
    public  static List<Players> point3(List<Players> playerList,List<Clubs>clubsList,List<Country>countryList) {
        int age;
        double height;
        int number;
        double salary;
        String name;
        String country;
        String club;
        String position;
        String dummy;
        int type_of_problem=0;
        System.out.println("ENTER THE NAME OF THE PLAYER : ");
        name=scanner2.next();
        while(check(name.trim(),0)==false)
        {
            System.out.println("ENTER THE NAME OF THE PLAYER : ");
            name=scanner2.next();
        }
        name=name.trim();
        System.out.println("ENTER THE NAME OF THE COUNTRY : ");
        country=scanner2.next();
        while(check(country.trim(),0)==false)
        {
            System.out.println("ENTER THE NAME OF THE PLAYER : ");
            country=scanner2.next();
        }
        country=country.trim();
        System.out.println("ENTER THE POSITION OF THE PLAYER : ");
        position=scanner2.next();
        while(check(position.trim(),1)==false)
        {
            System.out.println("ENTER THE NAME OF THE PLAYER : ");
            position=scanner2.next();
        }
        position=position.trim();
        System.out.println("ENTER THE CLUB NAME : ");
        club=scanner2.next();
        while(check(club.trim(),0)==false)
        {
            System.out.println("ENTER THE NAME OF THE PLAYER : ");
            club=scanner2.next();
        }
        club=club.trim();
        System.out.println("ENTER THE AGE OF THE PLAYER : ");
        dummy=scanner2.next();
        while(check(dummy.trim(),2)==false)
        {
            System.out.println("ENTER THE AGE OF THE PLAYER : ");
            dummy=scanner2.next();
        }
        age= Integer.parseInt(dummy.trim());
        System.out.println("ENTER THE NUMBER : ");
        dummy=scanner2.next();
        while(check(dummy.trim(),2)==false)
        {
            System.out.println("ENTER THE AGE OF THE PLAYER : ");
            dummy=scanner2.next();
        }
        number=Integer.parseInt(dummy.trim());
        System.out.println("ENTER THE HEIGHT : ");
        dummy=scanner2.next();
        while(check(dummy.trim(),3)==false)
        {
            System.out.println("ENTER THE HEIGHT OF THE PLAYER : ");
            dummy=scanner2.next();
        }
        height=Double.parseDouble(dummy.trim());
        System.out.println("ENTER THE SALARY : ");
        dummy=scanner2.next();
        while(check(dummy.trim(),3)==false)
        {
            System.out.println("ENTER THE SALARY OF THE PLAYER : ");
            dummy=scanner2.next();
        }
        salary=Double.parseDouble(dummy.trim());
        Character.toUpperCase(name.charAt(0));
        Character.toUpperCase(position.charAt(0));
        Character.toUpperCase(club.charAt(0));
        Character.toUpperCase(country.charAt(0));
        Players p=new Players(name,country,position,club,age,number,height,salary);
        for(int i=0;i<playerList.size();i++)
        {
            Players p2=playerList.get(i);
            if(name.equalsIgnoreCase(p2.GetName()))
            {
                type_of_problem=1;
                break;
            }
        }
        for(int i=0;i<clubsList.size();i++)
        {
            Clubs c=clubsList.get(i);
            if(club.equalsIgnoreCase(c.GetName()))
            {
                if(c.GetPlayerCount()==7)
                {
                    type_of_problem=2;
                    break;
                }
                if(c.check_number(number)==false)
                {
                    type_of_problem=3;
                }
            }
        }
        if(type_of_problem==0) {
            playerList.add(p);
            System.out.println("PLAYER IS ADDED SUCCESSFULLY.");
        }
        else if(type_of_problem==1)
        {
            System.out.println("COULDN'T ADD THE PLAYER AS PLAYER WITH THE SAME NAME IS ALREADY IN THE DATABASE");
        }
        else if(type_of_problem==2)
        {
            System.out.println("COULDN'T ADD THE PLAYER AS THE SPECIFIED CLUB CAN'T HOLD MORE THAN 7 PLAYERS.");

        }
        else
        {
            System.out.println("COULDN'T ADD THE PLAYER AS THE SPECIFIED CLUB HAS THE SAME JERSEY NUMBER");
        }
        return playerList;
    }

    public static List<Clubs> point31(Players players,List<Clubs>clubList,List<Country>countryList) {
        String club=players.GetClub();
        int mark=0;
        int flag=0;
        for(int i=0;i<clubList.size();i++)
        {
            Clubs c=clubList.get(i);
            if(club.equalsIgnoreCase(c.GetName()))
            {
                c.addPlayers(players);
                flag=1;
            }
        }
        if(flag==0)
        {
            clubList.add(new Clubs(club));
            clubList.get(clubList.size()-1).addPlayers(players);
        }
        return clubList;
    }

    public static List<Country> point32(Players players,List<Clubs>clubList,List<Country>countryList) {
        String country=players.GetCountry();
        int mark=0;
        int flag=0;
        for(int i=0;i<countryList.size();i++)
        {
            Country c;
            c=countryList.get(i);
            if(country.equalsIgnoreCase(c.GetName()))
            {
                c.AddClubs(new Clubs(players.GetClub()),players);
                flag=1;
            }
        }
        if(flag==0)
        {
            countryList.add(new Country(country));
            (countryList.get(countryList.size()-1)).AddClubs(new Clubs(players.GetClub()),players);
        }
        return countryList;
    }

    public static boolean check(String s,int type)
    {
        int flag=1;
        if(type==0) {
            int letter_found=0;
            for (int i = 0; i < s.length(); i++) {
                if(Character.isLetter(s.charAt(i))==true || Character.isSpace(s.charAt(i)))
                {
                    if(Character.isLetter(s.charAt(i))==true)
                        letter_found=1;
                    continue;
                }
                else { flag=0; }
            }
            if(letter_found==0)
            {
                System.out.println("ENTER LETTERS PLEASE.");
                flag=0;
            }
            if(flag==0)
            {
                System.out.println("ONLY LETTERS AND SPACES ARE ALLOWED.");
            }
        }
        if(type==1) {
            for (int i = 0; i < s.length(); i++) {
                if(Character.isLetter(s.charAt(i))==true) { continue; }
                else { flag=0; }
            }
            if(flag==0)
            {
                System.out.println("ONLY LETTERS ARE ALLOWED.");
            }
        }
        if(type==2) {
            int digit_found=0;
            for (int i = 0; i < s.length(); i++) {
                if(Character.isDigit(s.charAt(i))==true)
                {
                    digit_found=1;
                    continue;
                }
                else { flag=0; }
            }
            if(digit_found==0)
                flag=0;
            if(flag==0)
            {
                System.out.println("ONLY DIGITS ARE ALLOWED ");
            }
        }
        if(type==3) {
            int count=0;
            for (int i = 0; i < s.length(); i++) {
                if(Character.isDigit(s.charAt(i))==true || s.charAt(i)=='.')
                {
                    if(s.charAt(i)=='.')
                        count=count+1;
                    continue;
                }
                else { flag=0; }
            }
            if(count>1)
                flag=0;
            if(flag==0)
            {
                System.out.println("ONLY DIGITS AND ONLY ONE DOT ARE ALLOWED");
            }
        }
        if(flag==0) { return false; }
        else {return true;}
    }
}


