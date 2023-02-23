package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Student;
import model.VIAModel;

public class StudentsController
{
    @FXML private TextField searchBar;
    @FXML private ChoiceBox searchBy;
    @FXML private TableView tableView;
    @FXML private TableColumn classColumn;
    @FXML private TableColumn VIAIDColumn;
    @FXML private TableColumn NameColumn;
    private TableSelectionModel selectionModel;
    private ViewHandler viewHandler;
    private VIAModel model;
    private Region root;
    private StudentsPopUpController studentsPopUpController;

    public StudentsController() {
        //Wazowski was here
    }

    public void init(ViewHandler viewHandler, VIAModel model, Region root) {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        searchBy.getItems().add("Class");
        searchBy.getItems().add("VIA ID");
        searchBy.getItems().add("Name");
        searchBy.setValue(searchBy.getItems().get(0));
        tableJoyStick();
    }

    //source for code:
    // https://stackoverflow.com/questions/47559491/making-a-search-bar-in-javafx
    public void tableJoyStick() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        for (int i = 0; i < model.getStudentList().size(); i++)
        {
            students.add(model.getStudentList().getStudent(i));
        }

        classColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("classFull"));
        VIAIDColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("viaID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));

        FilteredList<Student> filteredStudents = new FilteredList(students, p -> true);
        tableView.setItems(filteredStudents);

        searchBar.setPromptText("Search here!");
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            switch ((String)searchBy.getValue())//Switch on choiceBox value
            {
                case "Class":
                    filteredStudents.setPredicate(p -> p.getClassFull().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "VIA ID":
                    filteredStudents.setPredicate(p -> p.getViaID().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Name":
                    filteredStudents.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
            }
        });
        searchBy.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
            -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                searchBar.setText("");
            }
        });
    }

    public void reset() {
        searchBar.setText("");
        searchBy.setValue("Class");
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void backPressed() {
        viewHandler.openView("hub");
    }

    @FXML private void switchClassPressed() {
        if (tableView.getSelectionModel().getSelectedItem()!=null){
            viewHandler.openView("SwitchPopUp");
            tableView.refresh();
        }
    }
    public Student selectedStudent()
    {
        return (Student) tableView.getSelectionModel().getSelectedItem();

    }
    public void refresh()
    {
        tableView.refresh();
    }
}
