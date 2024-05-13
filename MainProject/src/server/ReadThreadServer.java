package server;

import data.Players;
import util.NetworkUtil;
import java.io.*;
import java.util.*;
import dto.*;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    public HashMap<String, ClientInfo> clientMap;
    private List<Players>playersList;
    private  List<Players>listResult;
    private List<Double>listDouble;


    public ReadThreadServer(List<Players>p,HashMap<String, ClientInfo> map, NetworkUtil networkUtil) {
        this.clientMap = map;
        this.networkUtil = networkUtil;
        this.playersList=p;
        listResult=new ArrayList<>();
        listDouble=new ArrayList<>();
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
               if(o instanceof BuyPlayers)
               {
                   BuyPlayers buyPlayers=(BuyPlayers) o;
                   Players bought= buyPlayers.getP();
                   String name= buyPlayers.getName();
                   ClientInfo clientInfo = clientMap.get(bought.GetClub());
                   List<Players>l1=clientInfo.getSoldPlayers();
                   List<Double>l2=clientInfo.getPriceList();
                   for(int i=0;i<l1.size();i++)
                   {
                       if(bought.GetName().equalsIgnoreCase(l1.get(i).GetName()))
                       {
                           l1.remove(i);
                           l2.remove(i);
                           break;
                       }
                   }
                   List<Players>l3=clientInfo.getPlayersList();
                   for(int i=0;i<l3.size();i++)
                   {
                       if(bought.GetName().equalsIgnoreCase(l3.get(i).GetName()))
                       {
                          l3.remove(i);
                          break;
                       }
                   }
                   ClientInfo clientInfo1=clientMap.get(name);
                   List<Players>list=clientInfo1.getPlayersList();
                   bought.SetClub(name);
                   list.add(bought);

                   for(int i=0;i<playersList.size();i++)
                   {
                       if(playersList.get(i).GetName().equalsIgnoreCase(bought.GetName()))
                       {
                           playersList.get(i).SetClub(name);
                           System.out.println(playersList.get(i).GetName()+" "+playersList.get(i).GetClub());
                       }
                   }
                   System.out.println(playersList.size());


               }
                if (o instanceof RegisterMessage) {
                    RegisterMessage obj = (RegisterMessage) o;
                    int flag=1;
                    Iterator<String> iterator = clientMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        String name = iterator.next();
                        if(name.equalsIgnoreCase(obj.getName()))
                        {
                            flag=0;break;
                        }
                    }
                    if(flag==1) {
                        networkUtil.write("successRegister");
                        ClientInfo clientInfo = new ClientInfo();
                        clientInfo.setPassword(obj.getPassword());
                        clientInfo.setOnline(false);
                        clientInfo.setNetworkUtil(networkUtil);
                        clientMap.put(obj.getName(), clientInfo);

                    }
                    else
                        networkUtil.write("unsuccessRegister");
                }
                if(o instanceof String)
                {
                   String str=(String) o;
                   if(str.equalsIgnoreCase("PlayerListofClub"))
                   {
                       Object o1=networkUtil.read();
                       String nameClub = "";
                       if(o1 instanceof String)
                       {
                           nameClub=(String)o1;
                       }
                       ClientInfo clientInfo=clientMap.get(nameClub);
                       SendAllList sendAllList=new SendAllList();
                       sendAllList.setPlayersList(clientInfo.getPlayersList());
                       networkUtil.getOos().reset();
                       networkUtil.write(sendAllList);
                   }
                   if(str.equalsIgnoreCase("sendPlayersList"))
                   {
                       Iterator<String> iterator = clientMap.keySet().iterator();
                       ToClientPlayerList toClientPlayerList=new ToClientPlayerList();
                       List<Players>ans=new ArrayList<>();
                       while (iterator.hasNext()) {
                           String name = iterator.next();
                           ClientInfo clientInfo = clientMap.get(name);
                           List<Players>p=clientInfo.getSoldPlayers();

                           for(int i=0;i<p.size();i++)
                               ans.add(p.get(i));
                       }
                       networkUtil.getOos().reset();
                       toClientPlayerList.setPlayersList(ans);
                       networkUtil.write(toClientPlayerList);
                   }
                    if(str.equalsIgnoreCase("sendPriceList"))
                    {
                        Iterator<String> iterator = clientMap.keySet().iterator();
                        ToClientPriceList toClientPriceList=new ToClientPriceList();
                        List<Double>ans=new ArrayList<>();
                        while (iterator.hasNext()) {
                            String name = iterator.next();
                            ClientInfo clientInfo = clientMap.get(name);
                            List<Double>p=clientInfo.getPriceList();

                            for(int i=0;i<p.size();i++)
                                ans.add(p.get(i));
                        }
                    toClientPriceList.setPriceList(ans);
                    networkUtil.getOos().reset();
                        networkUtil.write(toClientPriceList);
                    }
                }
                if (o instanceof SendPlayer) {
                    SendPlayer obj = (SendPlayer) o;
                    ClientInfo clientInfo=clientMap.get(obj.getClientName());
                    clientInfo.getSoldPlayers().add(obj.getPlayer());
                    clientInfo.getPriceList().add(Double.parseDouble(obj.getTaka()));
                    listResult.add(obj.getPlayer());
                    listDouble.add(Double.parseDouble(obj.getTaka()));
                }
                if (o instanceof LoginMessage) {
                    LoginMessage obj = (LoginMessage) o;
                    ClientInfo clientInfo = clientMap.get(obj.getName());
                    if (clientInfo != null) {
                        if (clientInfo.getPassword().equals(obj.getPassword())) {
                            clientInfo.setOnline(true);
                            networkUtil.write("success");
                            List<Players>selected=clientInfo.getPlayersList();
                            int s=selected.size();
                            for(int i=0;i<s;i++)
                                selected.remove(0);
                            for(int i=0;i<playersList.size();i++)
                            {
                                if(playersList.get(i).GetClub().equalsIgnoreCase(obj.getName()))
                                {
                                    selected.add(playersList.get(i));
                                }
                            }
                        } else {
                            networkUtil.write("failure");
                        }
                    }
                    else  networkUtil.write("failure");
                }
                if (o instanceof GetListMessage) {
                    List<String> clientList = new ArrayList<>();
                    GetListMessage obj = (GetListMessage) o;
                    Iterator<String> iterator = clientMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        String name = iterator.next();
                        ClientInfo clientInfo = clientMap.get(name);
                        if (!name.equals(obj.getName()) && clientInfo.isOnline()) {
                            clientList.add(name);
                        }
                    }
                    GetListResponseMessage getListResponseMessage = new GetListResponseMessage();
                    getListResponseMessage.setClientList(clientList);
                    networkUtil.write(getListResponseMessage);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



