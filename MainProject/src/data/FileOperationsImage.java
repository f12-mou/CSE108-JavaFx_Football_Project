package data;

import javafx.scene.image.*;
import javafx.scene.paint.Color;
import sample.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileOperationsImage {
    private static final String INPUT_FILE_NAME = "PlayersImage2.txt";
    private static final String OUTPUT_FILE_NAME = "PlayersImage2.txt";
    private List<Players> playerlist;

    public static void read() throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        List<Players> playerlist = Main.getPlayersListGlobal();
        int maxsize=playerlist.size();
        System.out.println("in data fileImage " + playerlist.size());
        int i = 0;
        while (true) {
            if(i==maxsize)
                break;

            String line = bf.readLine();
            if(line==null)
                break;
            Players p = playerlist.get(i);
            String token[] = line.split(",");
            if (!(token[4].equalsIgnoreCase(Main.getClientName()))) {
                System.out.println("in here for others.");
                int h = Integer.parseInt(token[2]);
                int w = Integer.parseInt(token[3]);
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        bf.readLine();
                    }
                }
                continue;
            }
            if (token[1].equalsIgnoreCase("0")) {
                System.out.println("in here");
                FileInputStream input = new FileInputStream("DefaultImage.jpg");
                Image image = new Image(input);
                int width = (int) image.getWidth();
                int height = (int) image.getHeight();
                WritableImage writableImage = new WritableImage(width, height);
                PixelWriter writer = writableImage.getPixelWriter();
                PixelReader pixelReader = image.getPixelReader();

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        Color color = pixelReader.getColor(x, y);
                        writer.setColor(x, y, color);

                    }
                }
                p.setImage(writableImage, 2);
                // p.setImage(image, 2);
            } else {
                System.out.println("in here2");
                int height = Integer.parseInt(token[2]);
                int width = Integer.parseInt(token[3]);
                WritableImage writableImage = new WritableImage(width, height);
                PixelWriter writer = writableImage.getPixelWriter();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        String token2 = bf.readLine();
                        String token3[] = token2.split(",");

                        double red = Double.parseDouble(token3[0]);
                        double green = Double.parseDouble(token3[1]);
                        double blue = Double.parseDouble(token3[2]);
                        Color color2 = Color.color(red, green, blue);
                        writer.setColor(x, y, color2);

                    }
                }
                //ImageView myimageView = new ImageView(writableImage);
                p.setImage(writableImage, 1);
            }
            i++;

        }
        bf.close();
    }





    public static void write() throws Exception {
       /* BufferedReader bf = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter("FinalPlayersInfo.txt"));
        List<Players> list = Main.getPlayersListGlobal();
        int i=0;
        System.out.println("entering write operation");
        while(true){

            String line = bf.readLine();
            String token[] = line.split(",");
            if (!(token[4].equalsIgnoreCase(Main.getClientName()))) {
                bw.write(line);
                bw.write("\n");
                int h = Integer.parseInt(token[2]);
                int w = Integer.parseInt(token[3]);
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                       String s= bf.readLine();
                        bw.write(s);
                        bw.write("\n");
                    }
                }
                continue;
            }
            Players p = list.get(i);
            if (p.getPictureState() == true) {
                Image image = p.getImage();
                PixelReader pixelReader = image.getPixelReader();
                int width = (int) image.getWidth();
                int height = (int) image.getHeight();
                bw.write(i + ",1," + height + "," + width+","+Main.getClientName());
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        Color color = pixelReader.getColor(x, y);
                        double red = color.getRed();
                        double blue = color.getBlue();
                        double green = color.getGreen();
                        bw.write(String.valueOf(red) + "," + String.valueOf(green) + "," + String.valueOf(blue));
                        bw.write("\n");
                    }
                }
            } else {
                bw.write(i + ",0,0,0,"+Main.getClientName());
                bw.write("\n");
            }

        }*/
        BufferedWriter bw = new BufferedWriter(new FileWriter("PlayersImage2.txt"));
        List<Players> list = Main.getPlayersListGlobal();
        int i=0;
        System.out.println("entering write operation");
        while(true) {
            if(i==list.size())
                break;
            Players p=list.get(i);
            bw.write(String.valueOf(p.GetNumber())+",0,0,0,"+p.GetClub());
            bw.write("\n");
            i++;
        }
        bw.close();
    }
}
