package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.VIAModel;

public class ImportController {
    private ViewHandler viewHandler;
    private VIAModel via;
    private Region root;

    public ImportController() {
        //Timmon and Pumba was here
    }

    public void init(ViewHandler viewHandler, VIAModel via, Region root) {
        this.viewHandler = viewHandler;
        this.via = via;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void cancelButtonPressed() {
        viewHandler.closeView();
    }

    @FXML private void importButtonPressed() {
        viewHandler.openView("hub");
    }
}
