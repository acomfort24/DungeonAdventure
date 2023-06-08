package view;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getSaveLoadService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class LoadSelectScene extends VBox {
    public LoadSelectScene() {
        super();
        final Text text = new Text("Choose a save");
        text.setFont(new Font(26));
        text.setFill(Color.WHITE);
        this.getChildren().add(text);
        try {
            final List<String> list = findFiles(Paths.get("./"));
            list.remove(0);
            list.remove(0);
            for (String save : list) {
                createLoadFileButton(save);
            }
        } catch (final Exception e) {
            System.out.println(e);
        }
    }
    private void createLoadFileButton(final String theSaveName) {
        final String save = theSaveName.substring(2, theSaveName.length() - 4);
        final Button button = new Button();
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
    private static List<String> findFiles(final Path thePath)
            throws IOException {

        if (!Files.isDirectory(thePath)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }
        final List<String> result;
        try (Stream<Path> walk = Files.walk(thePath)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(".sav"))
                    .collect(Collectors.toList());
        }

        return result;
    }
}
