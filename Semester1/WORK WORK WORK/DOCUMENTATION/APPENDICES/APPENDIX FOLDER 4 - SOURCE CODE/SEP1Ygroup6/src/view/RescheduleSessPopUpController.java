package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class RescheduleSessPopUpController
{
  private RescheduleSessController rescheduleSessController;
  private Session selectedSession;
  @FXML private Label insertCourse;
  @FXML private Label insertDate;
  @FXML private Label insertStartTime;
  @FXML private Label insertEndTime;
  @FXML private DatePicker dateSelect;
  @FXML private ChoiceBox startChoice;
  @FXML private ChoiceBox numberChoice;
  @FXML private ChoiceBox classroomChoice;
  @FXML private Label errorLabel;
  private ViewHandler viewHandler;
  private VIAModel model;
  private Region root;

  public RescheduleSessPopUpController(){
    // Timmon and Pumba was here
  }

  public Region getRoot() {
    return root;
  }

  public void init(ViewHandler viewHandler, VIAModel model, Region root, RescheduleSessController rescheduleSessController) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.rescheduleSessController = rescheduleSessController;
    this.selectedSession = rescheduleSessController.getSelectedSession();
    insertCourse.setText(selectedSession.getCourseTitle());
    insertDate.setText(selectedSession.getSessionDate().toString());
    insertStartTime.setText(selectedSession.getStartTime().toString());
    insertEndTime.setText(selectedSession.getEndTime().toString());

    final ObservableList<MyTime> possibleStartingTimes =
        FXCollections.observableArrayList();
    possibleStartingTimes.addAll(
        Arrays.asList(Session.POSSIBLE_STARTING_TIMES));

    final ObservableList<Integer> possibleNumberOfLessons =
        FXCollections.observableArrayList(
            1,2,3,4,5,6);

    ObservableList<Classroom> availableClassrooms = FXCollections.observableArrayList();
    for (int i = 0; i < model.getClassroomList().size(); i++)
    {
      availableClassrooms.add(model.getClassroomList().getClassroom(i));
    }
    startChoice.setItems(possibleStartingTimes);
    numberChoice.setItems(possibleNumberOfLessons);
    classroomChoice.setItems(availableClassrooms);
    reset();
  }

  public void reset() {
    numberChoice.setValue(selectedSession.getNumberOfLessons());
    startChoice.setValue(startChoice.getItems().get(0));
    classroomChoice.setValue(classroomChoice.getItems().get(0));
  }

  public void displayAvailableClassrooms()
  {
    if (numberChoice.getSelectionModel().getSelectedItem() != null && startChoice.getValue() != null && dateSelect.getValue() != null)
    {
      String chosenStartTime = startChoice.getValue().toString();
      String[] parts = chosenStartTime.split(":");
      int hour = Integer.parseInt(parts[0]);
      int minutes = Integer.parseInt(parts[1]);
      MyDate chosenDate = new MyDate(dateSelect.getValue().getDayOfMonth(), dateSelect.getValue().getMonthValue(), dateSelect.getValue().getYear());
      ArrayList<Classroom> availableClassrooms;
      availableClassrooms = model.returnAvailableClassrooms(chosenDate, new MyTime(hour, minutes), (Integer) numberChoice.getSelectionModel().getSelectedItem());
      ObservableList<Classroom> availableClassroomsShow = FXCollections.observableArrayList(availableClassrooms);
      classroomChoice.setItems(availableClassroomsShow);
    }
  }

  @FXML private void dateChanged() {
      displayAvailableClassrooms();
  }

  @FXML private void startTimeChanged() {
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
    numberChoice.setValue(selectedSession.getNumberOfLessons());
  }

  @FXML private void numLessonsChanged() {
    displayAvailableClassrooms();
  }

  @FXML private void goBackPressed(){
    viewHandler.closePopUp();
  }

  @FXML private void rescheduleButtonPressed()
  {
    try {
      LocalDate chosenDateLocal = (LocalDate)dateSelect.getValue();
      MyDate chosenDate = new MyDate(chosenDateLocal.getDayOfMonth(), chosenDateLocal.getMonthValue(), chosenDateLocal.getYear());
      MyTime chosenStartTime = (MyTime)startChoice.getValue();
      Classroom chosenClassroom = (Classroom)(classroomChoice.getValue());
      int chosenNumLessons = (Integer)numberChoice.getSelectionModel().getSelectedItem();

      int chosenSemester = rescheduleSessController.getSelectedSemester();
      String chosenClass = rescheduleSessController.getSelectedClass();
      int chosenWeek = rescheduleSessController.getChosenWeek();


      model.rescheduleSession(selectedSession,chosenDate,chosenStartTime,chosenNumLessons,chosenClassroom);
      model.saveCurrentChanges(model);
      model.exportClassSessionList(chosenSemester,chosenClass);
      viewHandler.closePopUp();
      rescheduleSessController.initTable(chosenSemester,chosenClass,chosenWeek);
      rescheduleSessController.resetTable();
    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());
      System.out.println(e.getMessage());
    }
  }
}
