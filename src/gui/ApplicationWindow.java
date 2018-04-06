package gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Recipe;
import model.RecipeInput;
import model.RecipeURL;
import model.Type;

public class ApplicationWindow extends Application {
    
	private ComboBox<Type> type;
	private TextField nameField;
	private TextField descriptionField;
	private TextArea methodField;
	private TextArea ingredientsField;
	private TextField urlField;
	private Recipe recipe;
	private ListView<Recipe> lview;
	private ObservableList<Recipe> observableView;
	private Button closeButton;
	private BorderPane bp = new BorderPane();
	
	public void start(Stage stage) throws Exception {
		stage.setTitle("Recipe Book Controller");
		
		bp.setTop(topPane());
		bp.setLeft(leftPane());
		bp.setRight(rightPane());
		bp.setCenter(centerPane(null));
		bp.setBottom(bottomPane());
		
		Scene firstScene = new Scene(bp, 850 , 550);
		stage.setScene(firstScene);
		stage.show();
		
	    try {
			RecipeController.getInstance().LoadFromFile();
		} 
    	catch (ClassNotFoundException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	    ArrayList<Recipe> loadedList = RecipeController.getInstance().getAllRecipes();
    	    observableView.addAll(loadedList);
    	
	    
		closeButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
			   stage.close(); 	
			}
		});
    }
    
	private Node topPane(){
		HBox viewBox = new HBox();
	    Text title = new Text("Recipe Book");
		
		//viewBox.setStyle("-fx-background-color : #8888AA");
		title.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		viewBox.getChildren().add(title);
		viewBox.setAlignment(Pos.CENTER);
		
		return viewBox;
	}
    
	private Node leftPane(){
		GridPane pane = new GridPane();
		lview = new ListView<Recipe>();
		pane.add(lview, 0, 0);
		pane.setAlignment(Pos.TOP_RIGHT);
		pane.setPadding(new Insets(20,20,20,20));
		//pane.setStyle("-fx-background-color : #8888AA");
        
		ArrayList<Recipe> recipes = RecipeController.getInstance().getAllRecipes();
		this.observableView = FXCollections.observableList(recipes);
		
		lview.setItems(observableView);
		lview.setPrefSize(250, 277);
		lview.setStyle("-fx-border-color : black;"
				+ "-fx-border-width : 2;"
				+ "-fx-border-style : solid;");
				//+ "-fx-background-color : #8888AA");
		
		lview.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				recipe = lview.getSelectionModel().getSelectedItem();
			    bp.setCenter(centerPane(recipe));
			}
		});
		
		return pane;
	}
	
	
	private Node rightPane(){
	    VBox vb = new VBox(20);
	    vb.setPadding(new Insets(20,25,10,20));
	   // vb.setStyle("-fx-background-color : #8888AA");
	    
	    Button saveButton = new Button("Save");
	    Button addButton = new Button("Add");
	    Button deleteButton = new Button("Delete");
	    
	    saveButton.setPrefSize(120, 25);
	    addButton.setPrefSize(120, 25);
	    deleteButton.setPrefSize(120, 25);
	    
	    addButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e) {
                recipe = null;
			    bp.setCenter(centerPane(null));
			}
	    });
	    
	    saveButton.setOnAction(new EventHandler<ActionEvent>(){
	    	public void handle(ActionEvent e) {
                Recipe recipeupdated = null;
		        recipeupdated = getTextFields();
                
		        if(recipe != null){
                	RecipeController.getInstance().updateRecipe(recipe, recipeupdated);
                	observableView.remove(recipe);
                	observableView.add(recipeupdated);
                }
                else {
                	RecipeController.getInstance().addRecipe(recipeupdated);
                	observableView.add(recipeupdated);
                }
                RecipeController.getInstance().SaveToFile();
	    	}
	    });
	    
	    deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	    	public void handle(ActionEvent e){
	    	   RecipeController.getInstance().removeRecipe(recipe);
	    	   observableView.remove(recipe);
	    	   RecipeController.getInstance().SaveToFile();
	    	}
	    });
	    
	    vb.getChildren().addAll(addButton,saveButton, deleteButton);
	    return vb;
	}
	
	private Node centerPane(Recipe recipe){
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setPadding(new Insets(20,20,10,45));
		//pane.setStyle("-fx-background-color : #8888AA");
        
		Label nameLabel = new Label("Recipe Name:");
		Label descriptionLabel = new Label("Description : ");
		Label methodLabel = new Label("Cooking Method : ");
		Label ingredientsLabel = new Label("Ingredients : ");
		Label urlLabel = new Label("Link to website");
		Label typeLabel = new Label("How would you like to input a new recipe");
		
		this.nameField = new TextField();
		this.descriptionField = new TextField();
		this.methodField = new TextArea();
		this.ingredientsField = new TextArea();
		this.urlField = new TextField();
		
		this.type = new ComboBox<Type>();
		this.type.setItems( FXCollections.observableArrayList(Type.INPUT, Type.URL));
		
		this.ingredientsField.setPrefSize(100, 150);
		this.methodField.setPrefSize(100, 185);
		
		Hyperlink myHyperlink = new Hyperlink();
		myHyperlink.setText("Click here to view recipe");
		
		if(recipe != null){
		    if(recipe.getType() == Type.INPUT){
			        
		        RecipeInput recipeinput = (RecipeInput) recipe;
		    		
	            nameField.setText(recipe.getRecipeName());
		    	descriptionField.setText(recipeinput.getDescription());
		        methodField.setText(recipeinput.getMethod());
		        ingredientsField.setText(recipeinput.getIngredents().toString());
			        
		        pane.getChildren().addAll(nameLabel,nameField,descriptionLabel,descriptionField,
			       		methodLabel,methodField,ingredientsLabel,ingredientsField); 
		        type.setValue(Type.INPUT);
		    }
		    else{
		    	RecipeURL recipeurl = (RecipeURL) recipe;
		    		
		    	nameField.setText(recipe.getRecipeName());
		    	urlField.setText(recipeurl.getFileLocation());
		    	pane.getChildren().addAll(nameLabel,nameField,urlLabel,urlField,myHyperlink);
		    	type.setValue(Type.URL);
		    	
		    	myHyperlink.setOnAction(e -> {
				    if(Desktop.isDesktopSupported())
				    {
				        try {
				            Desktop.getDesktop().browse(new URI(urlField.getText()));
				        } catch (IOException e1) {
				            e1.printStackTrace();
				        } catch (URISyntaxException e1) {
				            e1.printStackTrace();
				        }
				    }
				});
		    }
		}
		else {
		    pane.getChildren().addAll(typeLabel, type);
		    
		    type.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
			        pane.getChildren().clear();
			        
			        if(type.getValue() == Type.INPUT) {
					    pane.getChildren().addAll(nameLabel,nameField,descriptionLabel,descriptionField,
					            methodLabel,methodField,ingredientsLabel,ingredientsField);  
					}
			        else if(type.getValue() == Type.URL){
			        	pane.getChildren().addAll(nameLabel,nameField,urlLabel,urlField);
			        }
				}
			}); 
		}
		return pane;
	}
	
	private Node bottomPane(){
		HBox hb = new HBox();
	    this.closeButton  = new Button("Close");
		this.closeButton.setPrefSize(120, 25);
	
		//hb.setStyle("-fx-background-color : #8888AA");
		hb.setPadding(new Insets(10,20,20,10));
		hb.setAlignment(Pos.TOP_RIGHT);
		hb.getChildren().add(closeButton);
		
		return hb;
	}
	
	public Recipe getTextFields() {
		Recipe r = null;
		
		if(this.type.getValue() == Type.INPUT && !nameField.getText().trim().isEmpty() && !descriptionField.getText().isEmpty()
        		&& !methodField.getText().trim().isEmpty() && !ingredientsField.getText().trim().isEmpty()) {
            
			r = new RecipeInput(nameField.getText().trim(), Type.INPUT, descriptionField.getText().trim(), 
	        		methodField.getText().trim(), ingredientsField.getText().trim());
        }
        else if(this.type.getValue() == Type.URL && !nameField.getText().trim().isEmpty() && !urlField.getText().trim().isEmpty()){
            r = new RecipeURL(nameField.getText().trim(), Type.URL, urlField.getText().trim());
        }
		return r;
	}
	
	public static void main(String[] args){
	    ApplicationWindow appWindow = new ApplicationWindow();
		Application.launch(args);
	}
}
