package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScheduleSessController {
    @FXML
    private ChoiceBox semesterChoice;
    @FXML
    private ChoiceBox classChoice;
    @FXML
    private ChoiceBox courseChoice;
    @FXML
    private DatePicker dateSelect;
    @FXML
    private ChoiceBox startChoice;
    @FXML
    private ChoiceBox numberChoice;
    @FXML
    private ChoiceBox repeatChoice;
    @FXML
    private Label errorLabel;
    @FXML
    private ChoiceBox classroomChoice;
    @FXML
    private ViewHandler viewHandler;
    private VIAModel model;
    private Region root;

    public ScheduleSessController() {
        //Jerry was here!
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



        //source for the following code:
        // https://stackoverflow.com/questions/20794359/javafx-cascading-dropdown-based-on-selection

        final ObservableList<String> firstSemesterCourses = FXCollections.observableArrayList("SDJ", "RWD", "DMA","SEP");
        final ObservableList<String> secondSemesterCourses = FXCollections.observableArrayList("DBS", "SEP", "SDJ","SWE");
        final ObservableList<String> thirdSemesterCourses = FXCollections.observableArrayList("DNP", "CAO", "NES","SEP","SDJ");
        final ObservableList<String> fourthSemesterCourses = FXCollections.observableArrayList("ADS", "AND", "DAI","ESW","INO","SEP");
        final ObservableList<String> fifthSemesterCourses = FXCollections.observableArrayList("INP");
        final ObservableList<String> sixthSemesterCourses = FXCollections.observableArrayList("BPR", "SEP");
        final ObservableList<String> seventhSemesterCourses = FXCollections.observableArrayList("BPR");

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

        // CHANGE CLASSES DEPENDING ON SEMESTER
        semesterChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                switch (t1.toString()) {
                    case "1":
                        classChoice.setItems(getClassesForGivenSemester(1));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                    case "2":
                        classChoice.setItems(getClassesForGivenSemester(2));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                    case "3":
                        classChoice.setItems(getClassesForGivenSemester(3));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                    case "4":
                        classChoice.setItems(getClassesForGivenSemester(4));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                    case "5":
                        classChoice.setItems(getClassesForGivenSemester(5));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                    case "6":
                        classChoice.setItems(getClassesForGivenSemester(6));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                    case "7":
                        classChoice.setItems(getClassesForGivenSemester(7));
                        classChoice.setValue(classChoice.getItems().get(0));
                        break;
                }
            }
        });

        final ObservableList<MyTime> possibleStartingTimes = FXCollections.observableArrayList();
        possibleStartingTimes.addAll(Arrays.asList(Session.POSSIBLE_STARTING_TIMES));

        final ObservableList<Integer> possibleNumberOfLessons = FXCollections.observableArrayList(1,2,3,4,5,6);

        startChoice.setItems(possibleStartingTimes);
        numberChoice.setItems(possibleNumberOfLessons);

        ObservableList<Classroom> availableClassrooms = FXCollections.observableArrayList();
        for (int i = 0; i < model.getClassroomList().size(); i++)
        {
            availableClassrooms.add(model.getClassroomList().getClassroom(i));
        }

        ObservableList<Integer> availableRepeatTimes = FXCollections.observableArrayList();
        for (int i = 0; i < 17; i++)
        {
            availableRepeatTimes.add(i);
        }
        repeatChoice.setItems(availableRepeatTimes);
        classroomChoice.setItems(availableClassrooms);
        reset();
    }

    public ObservableList<String> getClassesForGivenSemester(int semester)
    {
        final ObservableList<String> displayTheseClasses =
            FXCollections.observableArrayList();
        AllCoursesList allCourses = model.getAllCourses();
        for (int i = 0; i < allCourses.size(); i++)
        {
            if (semester == allCourses.getCourse(i).getSemester())
            {
                if (!(displayTheseClasses.contains(allCourses.getCourse(i).getClassxyz())))
                {
                    displayTheseClasses.add(allCourses.getCourse(i).getClassxyz());
                }
            }
        }
        return displayTheseClasses;
    }

    public void reset() {
        semesterChoice.setValue(semesterChoice.getItems().get(0));
        classChoice.setValue(classChoice.getItems().get(0));
        courseChoice.setValue(courseChoice.getItems().get(0));
        numberChoice.setValue(numberChoice.getItems().get(0));
        startChoice.setValue(startChoice.getItems().get(0));
        classroomChoice.setValue(classroomChoice.getItems().get(0));
        repeatChoice.setValue(repeatChoice.getItems().get(0));
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void cancelButton() {
        viewHandler.openView("hub");
    }

    @FXML
    private void dateChanged() {
        displayAvailableClassrooms();
    }

    @FXML
    private void startTimeChanged()
    {
        displayAvailableClassrooms();
        if (startChoice.getValue().equals(Session.POSSIBLE_STARTING_TIMES[7]))
        {
            final ObservableList<Integer> possibleNumberOfLessons = FXCollections.observableArrayList(1, 2, 3, 4, 5);
            numberChoice.setItems(possibleNumberOfLessons);
        }
        else if (startChoice.getValue().equals(Session.POSSIBLE_STARTING_TIMES[8]))
        {
            final ObservableList<Integer> possibleNumberOfLessons = FXCollections.observableArrayList(1, 2, 3, 4);
            numberChoice.setItems(possibleNumberOfLessons);
        }
        else if (startChoice.getValue().equals(Session.POSSIBLE_STARTING_TIMES[9]))
        {
            final ObservableList<Integer> possibleNumberOfLessons = FXCollections.observableArrayList(1, 2, 3);
            numberChoice.setItems(possibleNumberOfLessons);
        }
        else if (startChoice.getValue().equals(Session.POSSIBLE_STARTING_TIMES[10]))
        {
            final ObservableList<Integer> possibleNumberOfLessons = FXCollections.observableArrayList(1, 2);
            numberChoice.setItems(possibleNumberOfLessons);
        }
        else
        {
            final ObservableList<Integer> possibleNumberOfLessons = FXCollections.observableArrayList(
                1, 2,3,4,5,6);
            numberChoice.setItems(possibleNumberOfLessons);
        }
    }

    @FXML
    private void numLessonsChanged()
    {
        displayAvailableClassrooms();
    }

    public void displayAvailableClassrooms()
    {
        if (numberChoice.getSelectionModel().getSelectedItem() != null && startChoice.getValue() != null && dateSelect.getValue() != null)
        {
            if (dateSelect.getValue() != null)
            {
                String[] parts = startChoice.getValue().toString().split(":");
                MyDate chosenDate = new MyDate(dateSelect.getValue().getDayOfMonth(), dateSelect.getValue().getMonthValue(), dateSelect.getValue().getYear());
                ArrayList<Classroom> availableClassrooms;
                availableClassrooms = model.returnAvailableClassrooms(chosenDate,new MyTime(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])),(Integer)numberChoice.getSelectionModel().getSelectedItem());

                ObservableList<Classroom> availableClassroomsShow = FXCollections.observableArrayList(availableClassrooms);
                classroomChoice.setItems(availableClassroomsShow);
            }
        }
    }

    //source: https://stackoverflow.com/questions/31605585/javafx-how-to-save-the-selected-from-choicebox
    @FXML
    private void scheduleButton()
    {
        int chosenSemester = (Integer) semesterChoice.getSelectionModel().getSelectedItem();
        String chosenClass = classChoice.getValue().toString();
        String[] parts = startChoice.getValue().toString().split(":");
        Session toBeScheduled = new Session(courseChoice.getValue().toString(), (Integer) numberChoice.getSelectionModel().getSelectedItem(),
            new MyTime(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])),
                new MyDate(dateSelect.getValue().getDayOfMonth(), dateSelect.getValue().getMonthValue(), dateSelect.getValue().getYear()), (Classroom) (classroomChoice.getValue()));
        try
        {
            model.scheduleSession(toBeScheduled, chosenSemester, chosenClass);
            System.out.println(
                "Scheduled: " + courseChoice.getValue().toString() + " for class " + chosenSemester + "" + chosenClass + "on the " +
                        new MyDate(dateSelect.getValue().getDayOfMonth(), dateSelect.getValue().getMonthValue(), dateSelect.getValue().getYear()) + ": "
                    + startChoice.getValue().toString() + " - " + toBeScheduled.getEndTime() + "in classroom: " + toBeScheduled.getClassroom());
            for (int i = 1; i <= (Integer) repeatChoice.getSelectionModel().getSelectedItem(); i++)
            {
                Session session = toBeScheduled.copy();
                session.getSessionDate().stepForward(i * 7);
                session.setWeekNumber(toBeScheduled.getWeekNumber()+i);
                if (session.getWeekNumber() > 52)
                {
                    int newWeekNumber = session.getWeekNumber() - 52;
                    session.setWeekNumber(newWeekNumber);
                }
                model.scheduleSession(session, chosenSemester, chosenClass);
                System.out.println("Scheduled: " + courseChoice.getValue().toString() + " for class "
                    + chosenSemester + "" + chosenClass + "on the " + session.getSessionDate() + ": " + startChoice.getValue().toString() + " - "
                    + toBeScheduled.getEndTime() + "in classroom: " + toBeScheduled.getClassroom());
            }
            errorLabel.setText("");
        }
        catch (Exception e)
        {
            errorLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
        model.exportClassSessionList(chosenSemester,chosenClass);
        model.saveCurrentChanges(model);
        displayAvailableClassrooms();
    }
}
