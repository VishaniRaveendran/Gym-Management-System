package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataViewTable extends Application {
    private TableView tableVw = new TableView();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Online Gym Management System");


        final Label lab = new Label("Member List");

        tableVw.setEditable(false);

        //table columns

        TableColumn<Integer, DefaultMember> column1 = new TableColumn<>("Membership Number");
        column1.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        column1.setPrefWidth(200);


        TableColumn<String, DefaultMember> column2 = new TableColumn<>("First Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column2.setPrefWidth(200);

        TableColumn<String, DefaultMember> column3 = new TableColumn<>("Last Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column3.setPrefWidth(150);


        TableColumn<String, DefaultMember> column4 = new TableColumn<>("Contact Number");
        column4.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        column4.setPrefWidth(150);

        TableColumn<Integer, Over60Member> column5 = new TableColumn<>("Age");
        column5.setCellValueFactory(new PropertyValueFactory<>("age"));
        column5.setPrefWidth(100);

        TableColumn <String, StudentMember>column6 = new TableColumn<>("School Name");
        column6.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        column6.setPrefWidth(100);

        ;

        tableVw.getColumns().add(column1);
        tableVw.getColumns().add(column2);
        tableVw.getColumns().add(column3);
        tableVw.getColumns().add(column4);
        tableVw.getColumns().add(column5);
        tableVw.getColumns().add(column6);




        File file = new File("searchSave.txt");      //file which is having member details
        try {
            Scanner input = new Scanner(file);

            DefaultMember member = null;

            //reading data from file
            while (input.hasNext()){
                int memberId = input.nextInt();
                String firstName = input.next();
                String lastName = input.next();
                String startMembershipdate = input.next();
                String contactNumber = input.next();
                String schoolName = input.next();
                int age = input.nextInt();
                String type = input.next();


                //inserting data into the table
                if (type.equals("student")){

                    tableVw.getItems().add(new StudentMember(memberId, firstName, lastName, startMembershipdate,contactNumber,type,schoolName));

                } else if (type.equals("over60")) {
                    tableVw.getItems().add(new Over60Member(memberId, firstName, lastName, startMembershipdate,contactNumber,type,age));
                }else {
                    tableVw.getItems().add(new DefaultMember(memberId, firstName, lastName, startMembershipdate,contactNumber,type));
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HBox hBox = new HBox();

        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Enter member id / name");
        searchTextField.setPrefSize(700, 30);
        searchTextField.setStyle("-fx-font-size: 18px;");

        Button searchBtn = new Button("Search");
        searchBtn.setPrefHeight(30);
        searchBtn.setStyle("-fx-font-size: 18px;");
        searchBtn.setDefaultButton(true);

        Label resultLabel = new Label("Result");
        resultLabel.setStyle("-fx-font-size: 18px;");

        searchTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                searchBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String searchValue = searchTextField.getText();
                        MyGymManager manager = new MyGymManager();
                        manager.search(searchValue, resultLabel);
                    }
                });
            }
        });

        hBox.getChildren().addAll(searchTextField,searchBtn);



        tableVw.setPrefSize(1500, 500);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(lab,tableVw,hBox,resultLabel);

        ((Group)scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
        stage.setWidth(1500);
        stage.setHeight(700);
    }
}
