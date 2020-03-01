package UI.TestUI;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
/**
 * @author ericjbruno
 */
public class JavaFXApplication1 extends Application {
    ObservableList<String> entries = FXCollections.observableArrayList();    
    ListView list = new ListView();
 
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Search");
        Button btn = new Button();
        btn.setText("Choose");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
         
        TextField txt = new TextField();
        txt.setPromptText("Search");
        txt.textProperty().addListener(
            new ChangeListener() {
                public void changed(ObservableValue observable, 
                                    Object oldVal, Object newVal) {
                    handleSearchByKey2((String)oldVal, (String)newVal);
                }
            });
         
        // Set up the ListView
        list.setMaxHeight(180);
        // Populate the list's entries
        for ( int i = 0; i < 100; i++ ) {
            entries.add("Item " + i);
        }
        entries.add("Eric J. Bruno");
        entries.add("Joseph Bruno");
        entries.add("Ashley Bruno");
        entries.add("Brandon Bruno");
        list.setItems( entries );
         
        VBox root = new VBox();
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(2);
        root.getChildren().addAll(txt,list,btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
     
    public void handleSearchByKey(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Change to upper case so that case is not an issue
        newVal = newVal.toUpperCase();
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            String entryText = (String)entry;
            if ( entryText.toUpperCase().contains(newVal) ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
 
    public void handleSearchByKey2(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            boolean match = true;
            String entryText = (String)entry;
            for ( String part: parts ) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
 
            if ( match ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
}