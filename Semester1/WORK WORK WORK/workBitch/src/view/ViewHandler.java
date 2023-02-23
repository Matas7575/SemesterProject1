package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.VIAModel;

public class ViewHandler {
    private Scene currentScene, secondaryScene;
    private Stage primaryStage, secondaryStage;
    private StudentsController studentController;
    private RescheduleSessController rescheduleSessController;
    private ScheduleSessController scheduleSessController;
    private StudentsPopUpController studentsPopUpController;
    private SwitchPopUpController switchPopUpController;
    private RescheduleSessPopUpController rescheduleSessPopUpController;
    private MainController controller;
    private VIAModel model;

    public ViewHandler(VIAModel model) {
        this.currentScene = new Scene(new Region());
        this.secondaryScene = new Scene(new Region());
        this.model = model;
    }

    public StudentsController getStudentsController()
    {
        return studentController;
    }

    public RescheduleSessController getRescheduleSessController() {
        return rescheduleSessController;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Scheduler");
        Image icon = new Image("dnfc_background.png");
        this.primaryStage.getIcons().add(icon);
        openView("hub");
    }

    public void closePopUp() {
        secondaryStage.close();
    }

    public void openView(String id) {
        Region root, secondaryRoot;
        switch (id) {
            case "Schedule" : {
                root = ScheduleSess("scheduleSess.fxml");
                currentScene.setRoot(root);
                primaryStage.setScene(currentScene);
                primaryStage.setWidth(root.getPrefWidth());
                primaryStage.setHeight(root.getPrefHeight());
                primaryStage.show();
                break;
            }
            case "Student" : {
                root = Student("Students.fxml");
                currentScene.setRoot(root);
                primaryStage.setScene(currentScene);
                primaryStage.setWidth(root.getPrefWidth());
                primaryStage.setHeight(root.getPrefHeight());
                primaryStage.show();
                break;
            }
            case "reschedule" : {
                root = RescheduleSess("rescheduleSess.fxml");
                currentScene.setRoot(root);
                primaryStage.setScene(currentScene);
                primaryStage.setWidth(root.getPrefWidth());
                primaryStage.setHeight(root.getPrefHeight());
                primaryStage.show();
                break;
            }
            case "studentsRemove" : {
                secondaryRoot = StudentsPopUp("studentsPopUp.fxml");
                secondaryScene.setRoot(secondaryRoot);
                secondaryStage = new Stage();
                secondaryStage.setScene(secondaryScene);
                secondaryStage.setWidth(secondaryRoot.getPrefWidth());
                secondaryStage.setHeight(secondaryRoot.getPrefHeight());
                secondaryStage.setTitle("Student remover");
                Image icon = new Image("dnfc_background.png");
                secondaryStage.getIcons().add(icon);
                secondaryStage.show();
                break;
            }
            case "SwitchPopUp" :
            {
                secondaryRoot = SwitchPopUp("SwitchPopUp.fxml");
                secondaryScene.setRoot(secondaryRoot);
                secondaryStage = new Stage();
                secondaryStage.setScene(secondaryScene);
                secondaryStage.setWidth(secondaryRoot.getPrefWidth());
                secondaryStage.setHeight(secondaryRoot.getPrefHeight());
                secondaryStage.setTitle("Switch student");
                Image icon = new Image("dnfc_background.png");
                secondaryStage.getIcons().add(icon);
                secondaryStage.show();
                break;
            }
            case "ReschedulePopUp" :
            {
                secondaryRoot = ReschedulePopUp("rescheduleSessPopUp.fxml");
                secondaryScene.setRoot(secondaryRoot);
                secondaryStage = new Stage();
                secondaryStage.setScene(secondaryScene);
                secondaryStage.setWidth(secondaryRoot.getPrefWidth());
                secondaryStage.setHeight(secondaryRoot.getPrefHeight());
                secondaryStage.setTitle("Reschedule session");
                Image icon = new Image("dnfc_background.png");
                secondaryStage.getIcons().add(icon);
                secondaryStage.show();
                break;
            }
            default : {
                root = mainController("mainPage.fxml");
                currentScene.setRoot(root);
                primaryStage.setScene(currentScene);
                primaryStage.setWidth(root.getPrefWidth());
                primaryStage.setHeight(root.getPrefHeight());
                primaryStage.show();
                break;
            }
        }
    }

    private Region StudentsPopUp(String fxmlfile) {
        if(studentsPopUpController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                studentsPopUpController = loader.getController();
                studentsPopUpController.init(this,model,root,this.getStudentsController());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            studentsPopUpController.reset();
        return studentsPopUpController.getRoot();
    }
    private Region SwitchPopUp(String fxmlfile) {
        if(switchPopUpController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                switchPopUpController = loader.getController();
                switchPopUpController.init(this,model,root,this.getStudentsController());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            switchPopUpController.reset();
        return switchPopUpController.getRoot();
    }
    private Region ReschedulePopUp(String fxmlfile) {
        if(rescheduleSessPopUpController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                rescheduleSessPopUpController = loader.getController();
                rescheduleSessPopUpController.init(this,model,root,this.getRescheduleSessController());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            rescheduleSessController.reset();
        return rescheduleSessPopUpController.getRoot();
    }

    public void closeView() {
        primaryStage.close();
    }

    public Region mainController(String fxmlfile) {
        if(controller == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                controller = loader.getController();
                controller.init(this,model,root);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            controller.reset();
        return controller.getRoot();
    }

    public Region Student(String fxmlfile) {
        if(studentController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                studentController = loader.getController();
                studentController.init(this,model,root);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            studentController.reset();
        return studentController.getRoot();
    }

    public Region RescheduleSess(String fxmlfile) {
        if(rescheduleSessController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                rescheduleSessController = loader.getController();
                rescheduleSessController.init(this,model,root);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            rescheduleSessController.reset();
        return rescheduleSessController.getRoot();
    }

    public Region ScheduleSess(String fxmlfile) {
        if(scheduleSessController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlfile));
                Region root = loader.load();
                scheduleSessController = loader.getController();
                scheduleSessController.init(this,model,root);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            scheduleSessController.reset();
        return scheduleSessController.getRoot();
    }
}
