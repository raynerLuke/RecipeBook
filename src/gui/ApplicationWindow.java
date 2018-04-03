package gui;

import java.util.ArrayList;

import controller.RecipeController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Recipe;

public class ApplicationWindow extends Application {

	private TextField nameField;
	private TextField ageField;
	private ListView<Recipe> lview;
	private ObservableList<Recipe> observableView;
	private ComboBox<String> genderComboBox;
	private Button closeButton;
	
	public void start(Stage stage) throws Exception 
	{
		stage.setTitle("Recipe Book Controller");
		BorderPane bp = new BorderPane();
	
		bp.setTop(topPane());
		bp.setLeft(leftPane());
		bp.setRight(rightPane());
		bp.setCenter(centerPane());
		bp.setBottom(bottomPane());
		
		Scene firstScene = new Scene(bp, 780 , 410);
		stage.setScene(firstScene);
		stage.show();
	    
		closeButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e)
		    {
			   stage.close(); 	
			}
		});
    }
    
	private Node topPane()
	{
		HBox viewBox = new HBox();
	    Text title = new Text("Recipe Book");
		
		viewBox.setStyle("-fx-background-color : #8888AA");
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		viewBox.getChildren().add(title);
		viewBox.setAlignment(Pos.CENTER);
		
		return viewBox;
	}
    
	private Node leftPane()
	{
		GridPane pane = new GridPane();
		lview = new ListView<Recipe>();
		pane.add(lview, 0, 0);
		pane.setAlignment(Pos.TOP_RIGHT);
		pane.setPadding(new Insets(20,20,10,45));
		pane.setStyle("-fx-background-color : #8888AA");
        
		ArrayList<Recipe> recipes = RecipeController.getInstance().getAllRecipes();
		this.observableView = FXCollections.observableList(recipes);
		
		lview.setItems(observableView);
		lview.setPrefSize(250, 277);
		lview.setStyle("-fx-border-color : black;"
				+ "-fx-border-width : 2;"
				+ "-fx-border-style : solid;"
				+ "-fx-background-color : #8888AA");
		
		return pane;
	}
	
	
	private Node rightPane()
	{
	    VBox vb = new VBox(20);
	    vb.setPadding(new Insets(20,25,10,20));
	    vb.setStyle("-fx-background-color : #8888AA");
	    
	    Button loadButton = new Button("Load");
	    Button saveButton = new Button("Save");
	    Button addButton = new Button("Add");
	    
	    loadButton.setPrefSize(120, 25);
	    saveButton.setPrefSize(120, 25);
	    addButton.setPrefSize(120, 25);
	    
	    addButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) 
            {
			    /*String nameEntered = nameField.getText();
			    int ageEntered = Integer.parseInt(ageField.getText());			    
			    String genderEntered = genderComboBox.getSelectionModel().getSelectedItem().toString();
			    Gender gender;
			    if(genderEntered.equals("Male"))
			    {
			    	gender = Gender.MALE;
			    }
			    else{
			    	gender = Gender.FEMALE;
			    }
			    
			    Patient newPatient = new Patient(nameEntered, ageEntered, gender);
			    observableView.add(newPatient);*/
			 }
	    });
	    
	    saveButton.setOnAction(new EventHandler<ActionEvent>(){
	    	public void handle(ActionEvent e) 
			{
	    		RecipeController.getInstance().SaveToFile();
	    		
	    		Alert saveAlert = new Alert(AlertType.INFORMATION);
	    		saveAlert.setHeaderText(null);
	    		saveAlert.setContentText("Saved");
	    		saveAlert.showAndWait();
			}
	    });
	    
	    loadButton.setOnAction(new EventHandler<ActionEvent>(){
	    	public void handle(ActionEvent e)
	    	{
	    	    try {
					RecipeController.getInstance().LoadFromFile();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	    ArrayList<Recipe> loadedList = RecipeController.getInstance().getAllRecipes();
	    	    observableView.addAll(loadedList);
	    	    
	    	    Alert saveAlert = new Alert(AlertType.INFORMATION);
	    		saveAlert.setHeaderText(null);
	    	    saveAlert.setContentText("Loaded");
	    		saveAlert.showAndWait();
	    	}
	    });
	    
	    vb.getChildren().addAll(loadButton,saveButton,addButton);
	    return vb;
	}
	
	private Node centerPane()
	{
		GridPane pane = new GridPane();
		GridPane innerPane = new GridPane();
		
		pane.setAlignment(Pos.TOP_RIGHT);
		pane.add(innerPane, 1, 1);
		pane.setStyle("-fx-background-color : #8888AA");
		pane.setPadding(new Insets (20,10,20,10));
		
		innerPane.setVgap(25);
		innerPane.setHgap(5);
		innerPane.setPadding(new Insets(25,20,80,20));
		innerPane.setAlignment(Pos.TOP_RIGHT);
		innerPane.setStyle("-fx-background-color : #8888AA;"
				+ "-fx-border-style : solid;"
				+ "-fx-border-width : 2;"
				+ "-fx-border-color : black;");
		
		Label nameLabel = new Label("Name : ");
		Label ageLabel = new Label("Age : ");
		Label genderLabel = new Label("Gender : ");
		this.nameField = new TextField();
		this.ageField = new TextField();
		
		genderComboBox = new ComboBox<String>();
		genderComboBox.getItems().addAll("Male", "Female");
		
		innerPane.add(nameLabel, 0 , 1);
		innerPane.add(nameField, 1, 1);
		innerPane.add(ageLabel, 0  ,2 );
		innerPane.add(ageField, 1, 2);
		innerPane.add(genderLabel, 0, 3);
		innerPane.add(genderComboBox, 1, 3);
	   
		return pane;
	}
	
	public Node bottomPane()
	{
		HBox hb = new HBox();
	    this.closeButton  = new Button("Close");
		closeButton.setPrefSize(120, 25);
	
		hb.setStyle("-fx-background-color : #8888AA");
		hb.setPadding(new Insets(10,20,20,10));
		hb.setAlignment(Pos.TOP_RIGHT);
		hb.getChildren().add(closeButton);
		
		return hb;
	}
	
	
	public static void main(String[] args)
	{
	    ApplicationWindow appWindow = new ApplicationWindow();
		Application.launch(args);
	}
}
