package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SceneSwapController {
    private Stage myStage;
    private String mySceneString = "World";
    private Scene myScene;
    private Scene myPreviousScene;
    private String myPreviousSceneString;
    private Parent myParent;
    public void switchToScene(String theScene) throws IOException {
        myPreviousSceneString = mySceneString;

        Parent root = FXMLLoader.load(getClass().getResource(theScene));
        mySceneString = theScene;
        myScene = new Scene(root);
        myStage.setScene(myScene);
        myStage.show();
    }
    public void switchToPreviousScene() throws IOException {
        switchToScene(myPreviousSceneString);
    }
    public String getCurrentScene() {
        return mySceneString;
    }
}
