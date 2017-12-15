package Pass_Engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class passEngine extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
        primaryStage.setTitle("Pass Engine Beta 1.0");
        Controller ctrl = new Controller(primaryStage);
        primaryStage.setScene(new Scene(root, 800, 600));

        Image icon = new Image("/Pass_Engine/Data/icon.jpeg");

        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {

        if(args.length > 0)launch(args);
        else System.exit(0);

    }
}
