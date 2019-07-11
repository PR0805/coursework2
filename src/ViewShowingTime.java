import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

public class ViewShowingTime {
    
    Stage window;
    Scene scene;
    VBox vbox;
    GridPane gridPane;
    Button bk;
    TableView<Showing> table;
    
    public ViewShowingTime(){
        
       window = new Stage();
       vbox = new VBox();
       scene = new Scene(vbox, 2000, 1000);
       gridPane = new GridPane();
       bk = new Button("Back");
       table = new TableView<Showing>();
       
       vbox.getChildren().addAll(gridPane, table);
       vbox.setAlignment(Pos.CENTER);
       gridPane.getChildren().addAll(bk);
       
        gridPane.setVgap(50);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(10,0,0,10));
       
       GridPane.setConstraints(bk, 0, 0);
	
	//ShowingId column
        TableColumn<Showing, String> showingColumn = new TableColumn<>("Showing ID");
        showingColumn.setMinWidth(300);
        showingColumn.setCellValueFactory(new PropertyValueFactory<>("showing"));

        //Movie Title column
        TableColumn<Showing, String> movieColumn = new TableColumn<>("Movie Title");
        movieColumn.setMinWidth(300);
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("movie"));

        //Date and time column
        TableColumn<Showing, String> datetmColumn = new TableColumn<>("Date / Time");
        datetmColumn.setMinWidth(300);
        datetmColumn.setCellValueFactory(new PropertyValueFactory<>("datetm"));

	//Room Number column
        TableColumn<Showing, String> roomColumn = new TableColumn<>("Room Number");
        roomColumn.setMinWidth(200);
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));


	table.setItems(getShowing());
        table.getColumns().addAll(showingColumn, movieColumn, datetmColumn, roomColumn);
	
       
       window.setScene(scene);
       window.show();
       window.setTitle("View Showing Time");
       
       bk.setOnAction((e) -> {
              window.close();
              Coursework2 coursework2= new Coursework2();
              coursework2.start(window);
        });
        
        
    }

	    //Get all of the showings
        public ObservableList<Showing> getShowing(){
	
	ArrayList<Showing> showingList = null;
	List list = null;

	try {

		showingList = new Showing().getshowing();
		list = new ArrayList();
	  	
		for (Showing s : showingList){
			//int showing, String movie, DateTime datetm, int room
			list.add(new Showing(
				s.getShowing(),
				s.getMovie(),
				s.getDatetm(),
				s.getRoom()
				
			));

		}

	} catch (SQLException e){
		
		System.err.print("Error:" + e.getMessage());

	}

	ObservableList showing= FXCollections.observableList(list);
        return showing;

}
    
}
