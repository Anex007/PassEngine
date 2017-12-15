package Pass_Engine;

import Pass_Engine.Utils.AlertBox;
import Pass_Engine.Utils.FileHandler;

import Pass_Engine.Utils.TabTemplate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab newUsertab;

    private static Stage stage;
    private String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
    private static String OS = System.getProperty("os.name").toLowerCase();

    public Controller(){

    }

    public Controller(Stage sg){
        this.stage = sg;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            ArrayList<String> users;

            if(isWindows()){

                if(!FileHandler.fileExist(cwd+"\\Data\\users.txt")){
                    File fData = new File(cwd+"\\Data");
                    fData.mkdir();
                    try {
                        FileHandler.Create(cwd+"\\Data\\users.txt", "");


                        AlertBox abox = new AlertBox("Welcome", "To get started Enter a UserName to store the password");
                        abox.alert();

                        tabPane.getTabs().remove(0);

                        return;
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }

                users = FileHandler.readStr(cwd+"\\Data\\users.txt");

            }else{

                if(!FileHandler.fileExist(cwd+"/Data/users.txt")){
                    File fData = new File(cwd+"/Data");
                    fData.mkdir();
                    try {
                        FileHandler.Create(cwd+"/Data/users.txt", "");


                        AlertBox abox = new AlertBox("Welcome", "To get started Enter a UserName to store the password");
                        abox.alert();

                        tabPane.getTabs().remove(0);

                        return;
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }

                users = FileHandler.readStr(cwd+"/Data/users.txt");
            }



            for(int i=0; i<users.size(); i++){
                TabTemplate temp = new TabTemplate();
                tabPane.getTabs().add(i+1,temp.getDefaultTemplate(users.get(i)));
            }

            tabPane.getTabs().remove(0);
            SelectionModel tabSelect = tabPane.getSelectionModel();
            tabSelect.select(0);
        } catch (FileNotFoundException e) {
            // Do a AlertBox which alerts that they must have deleted the file.
            e.printStackTrace();

        }

    }

    public void AddUser(){
        if(!newUsertab.isSelected()){
            return;
        }

        AlertBox box = new AlertBox("Enter User Name", "");
        box.filluserData();
        String user = box.getText();
        System.out.println("User To Add: "+user);
        if(user == null || user.equals(" ") || user.equals(""))
            return;

        try {

            File userDir;

            if(isWindows()){
                ArrayList allUsers = FileHandler.readStr(cwd+"\\Data\\users.txt");
                if (allUsers.contains(user))
                    return;
                FileHandler.Append(cwd+"\\Data\\users.txt", user);
                userDir = new File(cwd+"\\Data\\"+user);

                Long key = System.currentTimeMillis();
                Long Choose = System.nanoTime();

                String data = key.toString()+"\r\n"+Choose.toString();

                // Making the Files for user
                if(userDir.getAbsoluteFile().mkdir())
                    FileHandler.Create(cwd + "\\Data\\" + user + "\\Key.vm", data);

            }else{
                ArrayList allUsers = FileHandler.readStr(cwd+"/Data/users.txt");
                if (allUsers.contains(user))
                    return;
                FileHandler.Append(cwd+"/Data/users.txt", user);
                userDir = new File(cwd+"/Data/"+user);

                Long key = System.currentTimeMillis();
                Long Choose = System.nanoTime();

                String data = key.toString()+"\n"+Choose.toString();

                // Making the Files for user
                if(userDir.getAbsoluteFile().mkdir())
                    FileHandler.Create(cwd + "/Data/" + user + "/Key.vm", data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TabTemplate temp = new TabTemplate();

        tabPane.getTabs().add(temp.getDefaultTemplate(user));
    }

    public void selectAddUser(){
        tabPane.getSelectionModel().select(newUsertab);
    }

    public void closeWindow(){
        stage.close();
    }


    public void deleteUser(){
        AlertBox abox = new AlertBox("Delete User", "");
        String toDel;
        try {

            ArrayList<String> users;

            if(isWindows()){
                users = FileHandler.readStr(cwd+"\\Data\\users.txt");
                abox.delete(users, cwd+"\\Data\\users.txt");

                toDel = abox.getToDel();

                // Deleting all User files
                File userData = new File(cwd+"\\Data\\"+toDel+"\\key.vm");
                userData.delete();

                userData = new File(cwd+"\\Data\\"+toDel);
                userData.delete();
            }else {
                users = FileHandler.readStr(cwd+"/Data/users.txt");
                abox.delete(users, cwd+"/Data/users.txt");

                toDel = abox.getToDel();

                // Deleting all User files
                File userData = new File(cwd+"/Data/"+toDel+"/key.vm");
                userData.delete();

                userData = new File(cwd+"/Data/"+toDel);
                userData.delete();
            }


            ObservableList<Tab> allTabs = tabPane.getTabs();

            for(int i=0; i<allTabs.size(); i++){
                if(allTabs.get(i).getText().equals(toDel))
                    allTabs.remove(i);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }
}
