package Pass_Engine.Utils;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AlertBox {
    private String title;
    private String msg;
    private String text;
    private String toDel;

    public AlertBox(String title, String msg){
        this.title = title;
        this.msg = msg;
    }

    public void filluserData(){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(40, 40, 15, 40));
        layout.setHgap(8);
        layout.setVgap(10);

        Label prompt = new Label("User Name: ");
        prompt.setFont(new Font("Serif", 13));
        GridPane.setConstraints(prompt, 0, 0);

        TextField text = new TextField();
        text.setFont(new Font("Serif", 13));
        GridPane.setConstraints(text, 1, 0);

        Button sub = new Button("Submit");
        sub.setFont(new Font("Serif", 13));
        GridPane.setConstraints(sub, 1, 1);
        sub.setOnAction(e -> {
            window.close();
            this.text = text.getText();
            return ;
        });

        layout.getChildren().addAll(prompt, text, sub);

        window.setScene(new Scene(layout, 400, 200));
        window.setOnCloseRequest(e -> {
            this.text = null;
        });
        window.showAndWait();
    }

    public String getText(){
        return text;
    }

    public void alert(){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);

        Label lbl = new Label();
        lbl.setText(msg);
        //lbl.setScaleShape(true);
        lbl.setPrefSize(260, 50);
        lbl.setWrapText(true);
        lbl.setFont(new Font("Serif",13));

        Button confirm = new Button("OK");
        confirm.setOnAction(e -> {
            window.close();
        });

        AnchorPane layout = new AnchorPane( );

        AnchorPane.setTopAnchor(lbl, 20.0);
        AnchorPane.setRightAnchor(lbl, 12.0);

        AnchorPane.setTopAnchor(confirm, 100.0);
        AnchorPane.setRightAnchor(confirm, 50.0);

        layout.getChildren().addAll(lbl, confirm);

        window.setScene(new Scene(layout, 300, 150));
        window.setResizable(false);
        window.setOnCloseRequest(e -> {
            this.title = null;
        });
        window.showAndWait();
    }

    public void delete(ArrayList<String> users, String file){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        AnchorPane layout = new AnchorPane();

        ComboBox<String> cbox = new ComboBox<>();
        AnchorPane.setTopAnchor(cbox, 40.0);
        AnchorPane.setLeftAnchor(cbox, 20.0);
        AnchorPane.setRightAnchor(cbox, 200.0);
        cbox.setPromptText("Select User To Delete");
        cbox.getItems().addAll(users);

        Button btn = new Button("Delete");
        AnchorPane.setTopAnchor(btn, 80.0);
        AnchorPane.setLeftAnchor(btn, 160.0);

        btn.setOnAction(e -> {
            SelectionModel select = cbox.getSelectionModel();
            String user = (String) select.getSelectedItem();
            this.toDel = user;
            try {
                ArrayList<String> peoples = FileHandler.readStr(file);
                String notDelete = "";
                for(String people:peoples){
                    if(people.equals(user))
                        continue;
                    notDelete += people+"\n";
                }
                FileHandler.Create(file, notDelete);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            cbox.getItems().remove(user);
            select.clearSelection();
        });

        layout.getChildren().addAll(cbox, btn);

        window.setScene(new Scene(layout, 400, 200));
        window.setResizable(false);
        window.showAndWait();
    }

    public String getToDel(){
        return this.toDel;
    }
}
