package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import model.Student;
import model.VIAModel;

public class StudentsPopUpController {
    @FXML private Label studentId;
    private ViewHandler viewHandler;
    private VIAModel model;
    private Region root;
    public Student student;
    private StudentsController studentsController;

    public StudentsPopUpController() {
        //Tables deserve to die
    }

    public void init(ViewHandler viewHandler, VIAModel model, Region root, StudentsController studentcontroler) {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        this.studentsController = studentcontroler;
        studentId.setText(studentcontroler.selectedStudent().getViaID());
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
        studentId.setText(studentsController.selectedStudent().getViaID());
    }

    @FXML private void cancelPressed() {
        viewHandler.closePopUp();
    }

    @FXML private void removePressed() {

    }


}
