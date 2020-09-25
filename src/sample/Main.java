package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        FlowPane fp = new FlowPane(heightLb, widthLb, tf,tf2);
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
