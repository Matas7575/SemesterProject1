package view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import model.Student;
import model.VIAModel;

public class SwitchPopUpController {
        private ViewHandler viewHandler;
        private VIAModel model;
        private Region root;
        @FXML private ChoiceBox switchBox;
        private Student student;
        private StudentsController studentsController;


        public SwitchPopUpController(){}

        public void init(ViewHandler viewHandler, VIAModel model, Region root, StudentsController studentsController) {
            this.viewHandler = viewHandler;
            this.model = model;
            this.root = root;
            this.studentsController = studentsController;
            switchBox.getItems().add("X");
            switchBox.getItems().add("Y");
            switchBox.getItems().add("Z");
            switchBox.getItems().add("DK");
            switchBox.setValue(model.getStudentList().getStudent(studentsController.selectedStudent().getViaID()).getClassxyz());
        }

        public Region getRoot() {
            return root;
        }

        public void reset() {
            switchBox.setValue(model.getStudentList().getStudent(studentsController.selectedStudent().getViaID()).getClassxyz());
        }

        @FXML private void cancelPressed() {
            viewHandler.closePopUp();
        }

        @FXML private void okPressed() {
            model.getStudentList().getStudent(studentsController.selectedStudent().getViaID()).setClassxyz((String) switchBox.getValue());
            studentsController.refresh();
            viewHandler.closePopUp();
        }
    }

