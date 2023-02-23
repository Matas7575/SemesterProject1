package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;
import model.VIAModel;

public class AddStudentController {
    @FXML private ChoiceBox semesterChoice;
    @FXML private ChoiceBox classChoice;
    @FXML private ChoiceBox courseChoice;
    private StudentsController studentsController;
    private ViewHandler viewHandler;
    private VIAModel model;
    private Region root;

    public AddStudentController(){
        //Shrek was here
    }

    public void init(ViewHandler viewHandler, VIAModel model, Region root) {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        for (int i = 1; i <= 7; i++) {
            for (int j = 0; j < model.getAllCourses().size(); j++) {
                if (model.getAllCourses().getCourse(j).getSemester() == i) {
                    semesterChoice.getItems().add(i);
                    break;
                }
            }
        }
        final String classes = "XYZ";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < model.getAllCourses().size(); j++) {
                if (i < 3) {
                    if (model.getAllCourses().getCourse(j).getClassxyz().equals((String.valueOf(classes.charAt(i))))) {
                        classChoice.getItems().add(classes.charAt(i));
                        break;
                    }
                }
                if (model.getAllCourses().getCourse(j).getClassxyz().equals("DK")) {
                    classChoice.getItems().add("DK");
                    break;
                }
            }
        }
        final ObservableList<String> firstSemesterCourses =
                FXCollections.observableArrayList("SDJ", "RWD", "DMA","SEP");

        final ObservableList<String> secondSemesterCourses =
                FXCollections.observableArrayList("DBS", "SEP", "SDJ","SWE");

        final ObservableList<String> thirdSemesterCourses =
                FXCollections.observableArrayList("DNP", "CAO", "NES","SEP","SDJ");

        final ObservableList<String> fourthSemesterCourses =
                FXCollections.observableArrayList("ADS", "AND", "DAI","ESW","INO","SEP");

        final ObservableList<String> fifthSemesterCourses =
                FXCollections.observableArrayList("INP");

        final ObservableList<String> sixthSemesterCourses =
                FXCollections.observableArrayList("BPR", "SEP");

        final ObservableList<String> seventhSemesterCourses =
                FXCollections.observableArrayList("BPR");

        semesterChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                switch (t1.toString()) {
                    case "1":
                        courseChoice.setItems(firstSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                    case "2":
                        courseChoice.setItems(secondSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                    case "3":
                        courseChoice.setItems(thirdSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                    case "4":
                        courseChoice.setItems(fourthSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                    case "5":
                        courseChoice.setItems(fifthSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                    case "6":
                        courseChoice.setItems(sixthSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                    case "7":
                        courseChoice.setItems(seventhSemesterCourses);
                        courseChoice.setValue(courseChoice.getItems().get(0));
                        break;
                }
            }
        });
        semesterChoice.setValue(semesterChoice.getItems().get(0));
        classChoice.setValue(classChoice.getItems().get(0));
//        if(studentsController.)
    }

    public void selected(int index) {
        semesterChoice.setValue(model.getStudentList().getStudent(index).getSemester());
        classChoice.setValue(model.getStudentList().getStudent(index).getClassFull());
        courseChoice.setValue(model.getStudentList().getStudent(index).getCourseList().getCourse(0));
    }

    public void reset() {
        semesterChoice.setValue("1");
        classChoice.setValue("X");
        courseChoice.setValue("SDJ1");
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void cancelButtonPressed() {
        viewHandler.closePopUp();
    }

    @FXML private void addToClassButtonPressed() {

    }

    @FXML private void addToCourseButtonPressed() {

    }
}
