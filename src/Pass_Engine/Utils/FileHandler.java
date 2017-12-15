package Pass_Engine.Utils;


import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class FileHandler {

    public static ArrayList<String> readStr(String path) throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        Scanner file = new Scanner(new File(path));
        while (file.hasNext()){
            data.add(file.next());
        }
        file.close();
        return data;
    }

    public static void Create(String path, String data) throws FileNotFoundException {
        Formatter file = new Formatter(path);
        file.format("%s\r\n", data);
        file.close();
    }

    public static void Append(String path, String data) throws IOException {
        File _F_I_L_E = new File(path);
        FileWriter fw = new FileWriter(_F_I_L_E.getAbsoluteFile(), true);
        Formatter file = new Formatter(fw);
        file.format("%s\r\n", data);
        file.close();
    }

    public static boolean fileExist(String path){
        File fl = new File(path);
        return fl.exists();
    }
}
