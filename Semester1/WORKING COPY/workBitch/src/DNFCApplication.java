import javafx.application.Application;
import javafx.stage.Stage;
import model.VIA;
import view.ViewHandler;

import java.io.File;

public class DNFCApplication extends Application {
    public void start(Stage primaryStage) {
        VIA model = new VIA();
        File viaFile = new File("src/persistence/via.json");
        if (viaFile.exists() && !viaFile.isDirectory()) {

            model = model.loadInfoFromFile();
        }
        model.saveCurrentChanges(model);
        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);
    }
}
