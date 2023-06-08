package view;

import com.almasb.fxgl.dsl.FXGL;
import javafx.concurrent.Task;
import com.almasb.fxgl.profile.SaveFile;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getSaveLoadService;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class LoadSelectScene extends VBox {
    Map<String, Map<String, String>> myDBData;
    public LoadSelectScene() {
        super();
        Text text = new Text("Choose a save");
        text.setFont(new Font(26));
        text.setFill(Color.WHITE);
        this.getChildren().add(text);
        try {
            List<String> list = findFiles(Paths.get("./"), ".sav");
            list.remove(0);
            list.remove(0);
            for (String save : list) {
                createLoadFileButton(save);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void createLoadFileButton(String theSaveName) {
        String save = theSaveName.substring(2, theSaveName.length() - 4);
        Button button = new Button();
        button.setText(save);
        button.setOnAction(e -> {
            getSaveLoadService().readAndLoadTask(save + ".sav").run();
            DungeonMainMenu.myLoadSelectScreen.setVisible(false);
        });
        this.getChildren().add(button);
    }
    /*
    searches for files with given extension starting at the given path
     */
    private static List<String> findFiles(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }
        List<String> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }

        return result;
    }
}
