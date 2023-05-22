package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SceneSwapController {
    private Stage stage;
    private Scene scene;
    private Scene previousScene;
    private Parent parent;
    public void switchToScene(String theScene, ActionEvent theEvent) throws IOException {
//        previousScene = (Stage)((Node)theEvent.getSource()).getScene();
        Parent root = FXMLLoader.load(getClass().getResource(theScene));
        stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPreviousScene(ActionEvent theEvent) throws IOException {
        switchToScene("", theEvent);
    }
}
