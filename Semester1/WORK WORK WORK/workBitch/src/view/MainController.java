package view;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.VIAModel;

public class MainController {
    private ViewHandler viewHandler;
    private VIAModel model;
    private Region root;

    public MainController() {
        //Chad was here
    }

    public void init(ViewHandler viewHandler, VIAModel model, Region root) {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
    }

    public void reset() {

    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void scheduleSessionsButtonPressed() {
        viewHandler.openView("Schedule");
    }

    @FXML
    private void rescheduleOrCancelPressed() {
        viewHandler.openView("reschedule");
    }

    @FXML
    private void manageStudentsPressed() {
        viewHandler.openView("Student");
    }

    @FXML
    private  void closeProgramPressed() {
        model.saveCurrentChanges(model);
        viewHandler.closeView();
    }

}
