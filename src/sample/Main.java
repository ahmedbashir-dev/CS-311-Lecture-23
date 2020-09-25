package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Label heightLb = new Label();
        Label widthLb = new Label();
        TextField tf = new TextField();
        TextField tf2 = new TextField();
        CheckBox completedCb = new CheckBox("Completed");
        Spinner<Integer> spinner = new Spinner<Integer>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
        spinner.setValueFactory(valueFactory);

        Slider hSlider = new Slider(0,100,0);
        Slider vSlider = new Slider(0,100,0);
        vSlider.setOrientation(Orientation.VERTICAL);

        ComboBox<String> countriesComboBox = new ComboBox<String>();
        countriesComboBox.getItems().addAll("Pakistan","Iran","India","KSA");
        ProgressBar progressBar = new ProgressBar(0);
//        progressBar.setProgress(0.5);
        spinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldVal, Integer newVal) {
                if(newVal == 100){
                    completedCb.setSelected(true);
                }
                else{
                    completedCb.setSelected(false);
                }
                progressBar.setProgress(1.00*newVal/100);
                hSlider.setValue(newVal);
                vSlider.setValue(newVal);
            }
        });

        hSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                vSlider.setValue(newVal.doubleValue());
            }
        });

        FlowPane fp = new FlowPane(heightLb, widthLb, tf,tf2,spinner,completedCb,progressBar,hSlider,vSlider,countriesComboBox);
        fp.setHgap(25);
        fp.setVgap(25);

        tf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVal, Boolean newVal) {
                if(newVal==true){
                    System.out.println("Text Field is focused");
                }
                else{
                    System.out.println("Text Field Focus Lost");
                }
            }
        });
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if(newVal.length()>10){
//                    Alert.AlertType alertAlertType;
//                    Alert alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Length Exceeded");
//                    alert.setContentText("Length cannot be greater than 10");
//                    alert.showAndWait();
                    tf2.setDisable(true);
                }
                else{
                    tf2.setDisable(false);
                }
            }
        });
        widthLb.setFont(new Font(32));
        heightLb.setFont(new Font(32));
        fp.setOrientation(Orientation.VERTICAL);
        fp.setAlignment(Pos.CENTER);

        Scene scene = new Scene(fp,250,250);
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                heightLb.setText("Height: " + newVal);
            }
        });

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
                widthLb.setText("Width: " + oldVal);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Properties and Bindings Demo");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
