package Pass_Engine.Utils;

import Pass_Engine.Controller;
import Pass_Engine.Hash.Hash;
import Pass_Engine.Hash.ProcessKey;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TabTemplate{

    private String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
    private String User_name;


    public Tab getDefaultTemplate(String name){
        this.User_name = name;
        Tab tab = new Tab(name);
        tab.setClosable(true);

        AnchorPane layout = new AnchorPane();

        TextField text = new TextField();
        text.setMaxWidth(270);
        text.setMaxHeight(20);
        AnchorPane.setTopAnchor(text, 60.0);
        AnchorPane.setLeftAnchor(text, 40.0);
        AnchorPane.setRightAnchor(text, 481.0);
        AnchorPane.setBottomAnchor(text, 465.0);

        TextArea out = new TextArea();
        out.setEditable(false);
        AnchorPane.setTopAnchor(out, 60.0);
        AnchorPane.setLeftAnchor(out, 360.0);
        AnchorPane.setRightAnchor(out,  43.0);
        AnchorPane.setBottomAnchor(out, 420.0);

        Label minLenLabel = new Label("Min Length:");
        AnchorPane.setTopAnchor(minLenLabel, 130.0);
        AnchorPane.setLeftAnchor(minLenLabel, 370.0);
        AnchorPane.setBottomAnchor(minLenLabel, 380.0);


        Label maxLenLabel = new Label("Max Length:");
        AnchorPane.setTopAnchor(maxLenLabel, 130.0);
        AnchorPane.setLeftAnchor(maxLenLabel, 572.0);
        AnchorPane.setBottomAnchor(maxLenLabel, 380.0);

        Label warn = new Label();
        warn.setText("Max Length and Min Length should be a number");
        warn.setVisible(false);
        warn.setStyle("-fx-text-fill: red; -fx-font-size: 14;");
        AnchorPane.setTopAnchor(warn, 168.0);
        AnchorPane.setBottomAnchor(warn, 368.0);
        AnchorPane.setLeftAnchor(warn, 430.0);


        TextField minLen = new TextField();
        AnchorPane.setTopAnchor(minLen, 146.0);
        AnchorPane.setLeftAnchor(minLen, 438.0);
        AnchorPane.setRightAnchor(minLen,  268.0);
        AnchorPane.setBottomAnchor(minLen, 390.0);


        TextField maxLen = new TextField();
        AnchorPane.setTopAnchor(maxLen, 146.0);
        AnchorPane.setLeftAnchor(maxLen, 642.0);
        AnchorPane.setRightAnchor(maxLen,  43.0);
        AnchorPane.setBottomAnchor(maxLen, 390.0);

        minLen.setOnKeyPressed(e -> {
            if (minLen.getText().equals("") || maxLen.getText().equals("")){
                warn.setVisible(false);
                return;
            }
            if(!isInt(minLen.getText())) {
                warn.setVisible(true);
                warn.setText("Max Length and Min Length should be a number");
                return;
            }
            if(Integer.parseInt(minLen.getText()) > Integer.parseInt(maxLen.getText())){
                warn.setText("Max Length should be greater that Min Length");
                warn.setVisible(true);
                return;
            }
            warn.setVisible(false);
        });
        minLen.setOnKeyReleased(e -> {
            if (minLen.getText().equals("") || maxLen.getText().equals("")){
                warn.setVisible(false);
                return;
            }
            if(!isInt(minLen.getText())) {
                warn.setText("Max Length and Min Length should be a number");
                warn.setVisible(true);
                return;
            }
            if(Integer.parseInt(minLen.getText()) > Integer.parseInt(maxLen.getText())){
                warn.setText("Max Length should be greater that Min Length");
                warn.setVisible(true);
                return;
            }
            warn.setVisible(false);
        });


        maxLen.setOnKeyPressed(e -> {
            if(maxLen.getText().equals("") || minLen.getText().equals("")){
                warn.setVisible(false);
                return;
            }
            if(!isInt(maxLen.getText())) {
                warn.setText("Max Length and Min Length should be a number");
                warn.setVisible(true);
                return;
            }
            if(Integer.parseInt(minLen.getText()) > Integer.parseInt(maxLen.getText())){
                warn.setText("Max Length should be greater that Min Length");
                warn.setVisible(true);
                return;
            }
            warn.setVisible(false);

        });
        maxLen.setOnKeyReleased(e -> {
            if(maxLen.getText().equals("") || minLen.getText().equals("")){
                warn.setVisible(false);
                return;
            }
            if(!isInt(maxLen.getText())) {
                warn.setText("Max Length and Min Length should be a number");
                warn.setVisible(true);
                return;
            }


            if(Integer.parseInt(minLen.getText()) > Integer.parseInt(maxLen.getText())){
                warn.setText("Max Length should be greater that Min Length");
                warn.setVisible(true);
                return;
            }
            warn.setVisible(false);
        });


        Button btn = new Button("Hash");
        btn.setMaxWidth(56);
        btn.setMaxHeight(35);
        AnchorPane.setTopAnchor(btn, 105.0);
        AnchorPane.setLeftAnchor(btn, 150.0);
        AnchorPane.setRightAnchor(btn, 594.0);
        AnchorPane.setBottomAnchor(btn, 423.0);
        btn.setOnAction(e -> hash(text, out, warn, minLen.getText(), maxLen.getText()));

        layout.getChildren().addAll(text, btn, out, minLenLabel, maxLenLabel, minLen, maxLen, warn);

        tab.setContent(layout);
        return tab;
    }


    private void hash(TextField text, TextArea out, Label warn ,String s_minLen, String s_maxLen){
        System.out.println("User: "+User_name+" Requested for a Hash.");
        if(text.getText().equals(""))
            return;

        if(s_minLen.equals(""))
            s_minLen = "12";
        if(s_maxLen.equals(""))
            s_maxLen = "13";


        if(!isInt(s_minLen))
            return;
        if(!isInt(s_maxLen))
            return;

        warn.setVisible(false);

        int minLen = Integer.parseInt(s_minLen);
        int maxLen = Integer.parseInt(s_maxLen);

        if(minLen > 64)
            minLen = 64;
        if(maxLen > 64)
            maxLen = 65;


        if (minLen > maxLen)
            return;
        if (minLen == maxLen)
            maxLen = minLen + 1;

        try {
            ArrayList<String> data;
            if(Controller.isWindows()) {
                data = FileHandler.readStr(cwd + "\\Data\\" + User_name + "\\Key.vm");
            }else{
                data = FileHandler.readStr(cwd + "/Data/" + User_name + "/Key.vm");
            }
            Long Key = Long.parseLong(data.get(0));
            Long Choose = Long.parseLong(data.get(1));
            ProcessKey processKey = new ProcessKey(Key, Choose);
            Hash hasher = new Hash(text.getText(), processKey.getRand());
            String hash = hasher.cutHash(hasher.hash(), minLen, maxLen, Key);
            out.setText(hash);
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
    }

    private boolean isInt(String s_int){
        if(s_int.equals(""))
            return false;
        try{
            Integer.parseInt(s_int);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
