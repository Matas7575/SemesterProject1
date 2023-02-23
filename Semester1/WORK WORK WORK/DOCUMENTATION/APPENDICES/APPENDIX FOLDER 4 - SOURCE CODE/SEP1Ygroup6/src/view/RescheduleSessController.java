package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
import java.time.LocalDate;

public class RescheduleSessController {
    @FXML private ChoiceBox semesterChoice;
    @FXML private ChoiceBox classChoice;
    @FXML private ChoiceBox weekChoice;
    @FXML private TableView tableView;
    @FXML private TableColumn dateColumn;
    @FXML private TableColumn weekdayColumn;
    @FXML private TableColumn startTimeColumn;
    @FXML private TableColumn endTimeColumn;
    @FXML private TableColumn courseColumn;
    private TableSelectionModel selectionModel;
    private ViewHandler viewHandler;
    private VIAModel model;
    private Region root;

    public  RescheduleSessController() {
        //Hercules was here!
    }

    public Region getRoot() {
        return root;
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
        MyDate now = new MyDate(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear());
        for (int i = 52-(now.weeksBetween(new MyDate(31,12,2021))); i <= 52; i++) {
            weekChoice.getItems().add(i);
        }
        for (int i = 1; i < 5; i++)
        {
            weekChoice.getItems().add(i);
        }
        reset();

        resetTable();
    }

    public void reset() {
        semesterChoice.setValue(semesterChoice.getItems().get(0));
        classChoice.setValue(classChoice.getItems().get(0));
        weekChoice.setValue(weekChoice.getItems().get(0));
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

    @FXML private void goBackPressed() {
        resetTable();
        viewHandler.openView("hub");
    }

    @FXML private void cancelSessionPressed() {
        Session cancelThis = (Session)tableView.getSelectionModel().getSelectedItem();
        if (cancelThis == null)
        {
            return;
        }
        int chosenSemester = -1;
        int chosenWeek = -1;
        if (semesterChoice.getSelectionModel().getSelectedItem() instanceof String)
        {
            chosenSemester = Integer.parseInt((String)semesterChoice.getSelectionModel().getSelectedItem());
        }
        else {
            chosenSemester = (Integer)semesterChoice.getSelectionModel().getSelectedItem();
        }
        String chosenClass = classChoice.getValue().toString();
        if (weekChoice.getSelectionModel().getSelectedItem() instanceof String)
        {
            chosenWeek = Integer.parseInt((String)weekChoice.getSelectionModel().getSelectedItem());
        }
        else {
            chosenWeek = (Integer)weekChoice.getSelectionModel().getSelectedItem();
        }
        model.getAllCourseLists().getCourseListByClass(chosenSemester,chosenClass).getCourse(cancelThis.getCourseTitle()).getSessionList().remove(cancelThis);
        MyDate sessionDate = cancelThis.getSessionDate();
        MyTime startTime = cancelThis.getStartTime();
        int numLessons = cancelThis.getNumberOfLessons();
        Classroom unbookHer = cancelThis.getClassroom();
        model.unbookClassroom(unbookHer,sessionDate,startTime,numLessons);
        initTable(chosenSemester, chosenClass, chosenWeek);
        model.saveCurrentChanges(model);
        model.exportClassSessionList(chosenSemester,chosenClass);
    }

    @FXML private void searchPressed() {
        resetTable();
        int chosenSemester = -1;
        int chosenWeek = -1;
        if (semesterChoice.getSelectionModel().getSelectedItem() instanceof String)
        {
            chosenSemester = Integer.parseInt((String)semesterChoice.getSelectionModel().getSelectedItem());
        }
        else {
            chosenSemester = (Integer)semesterChoice.getSelectionModel().getSelectedItem();
        }
        String chosenClass = classChoice.getValue().toString();

        if (weekChoice.getSelectionModel().getSelectedItem() instanceof String)
        {
            chosenWeek = Integer.parseInt((String)weekChoice.getSelectionModel().getSelectedItem());
        }
        else {
            chosenWeek = (Integer)weekChoice.getSelectionModel().getSelectedItem();
        }
        CourseList thisClass = model.getAllCourseLists().getCourseListByClass(chosenSemester,chosenClass);
        for (int i = 0; i < thisClass.size(); i++)
        {
            System.out.println(thisClass.getCourse(i).getSessionList());
        }

        initTable(chosenSemester,chosenClass, chosenWeek);
    }

    public void resetTable()
    {
        tableView.refresh();
    }

    // NEW INIT TABLE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void initTable(int semester, String classxyz, int week)
    {
        ObservableList<Session> sessions = FXCollections.observableArrayList();

        CourseList thisClassCourseList = model.getAllCourseLists().getCourseListByClass(semester, classxyz);

        for (int i = 0; i < thisClassCourseList.size(); i++) // go thru every course for this class
        {
            for (int j = 0;
                 j < thisClassCourseList.getCourse(i).getSessionList().size(); j++) // go thru every session
            {
                Session session = thisClassCourseList.getCourse(i).getSessionList().getSession(j);
                if (MyDate.getWeekNumber(session.getSessionDate()) == week)
                {
                    sessions.add(session);
                }
            }
        }

        ObservableList<Session> sessionsTemp = FXCollections.observableArrayList(sessions);

        ObservableList<Session> sessionsSorted = FXCollections.observableArrayList();

        for (int j = 0; j < sessions.size(); j++)
        {
            Session earliestSession = sessionsTemp.get(0);
            for (int i = 1; i < sessionsTemp.size(); i++)
            {
                if (sessionsTemp.get(i).isBefore(earliestSession))
                {
                    earliestSession = sessionsTemp.get(i);
                }
            }
            sessionsSorted.add(earliestSession);
            sessionsTemp.remove(earliestSession);
        }

        dateColumn.setCellValueFactory(new PropertyValueFactory<Session, MyDate>("sessionDate"));
        weekdayColumn.setCellValueFactory(new PropertyValueFactory<Session, MyDate>("dayOfTheWeek"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<Session, MyDate>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<Session, MyDate>("endTime"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<Session, MyDate>("courseTitle"));
        tableView.setItems(sessionsSorted);
    }
    @FXML private void rescheduleSelectedSessButtonPressed()
    {
        if (tableView.getSelectionModel().getSelectedItem()!=null)
        {
            viewHandler.openView("ReschedulePopUp");
        }
    }

    public Session getSelectedSession()
    {
        return (Session)tableView.getSelectionModel().getSelectedItem();
    }

    public int getSelectedSemester()
    {
        return (Integer)semesterChoice.getValue();
    }

    public String getSelectedClass()
    {
        return (classChoice.getValue().toString());
    }

    public int getChosenWeek()
    {
        return (Integer)(weekChoice.getValue());
    }
}
