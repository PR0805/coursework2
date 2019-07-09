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

public class ViewAndSelect {
    
    Stage window;
    Scene scene;
    VBox vbox;
    GridPane gridPane;
    Button bk;
    TableView<Film> table;
    
    public ViewAndSelect(){
        
       window = new Stage();
       vbox = new VBox();
       scene = new Scene(vbox, 2000, 1000);
       gridPane = new GridPane();
       bk = new Button("Back");
       table = new TableView<Film>();
       
       vbox.getChildren().addAll(gridPane, table);
       vbox.setAlignment(Pos.CENTER);
       gridPane.getChildren().addAll(bk);
       
        gridPane.setVgap(50);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(10,0,0,10));
       
       GridPane.setConstraints(bk, 0, 0);
	
	//Movie column
        TableColumn<Film, String> movieColumn = new TableColumn<>("Movie (Title)");
        movieColumn.setMinWidth(300);
        movieColumn.setCellValueFactory(new PropertyValueFactory<>("movie"));

        //Star actors column
        TableColumn<Film, String> actorsColumn = new TableColumn<>("Star actors");
        actorsColumn.setMinWidth(300);
        actorsColumn.setCellValueFactory(new PropertyValueFactory<>("actors"));

        //Directors column
        TableColumn<Film, String> directorsColumn = new TableColumn<>("Directors");
        directorsColumn.setMinWidth(300);
        directorsColumn.setCellValueFactory(new PropertyValueFactory<>("directors"));

	//Genre column
        TableColumn<Film, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(150);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

	//Age column
        TableColumn<Film, String> ageColumn = new TableColumn<>("Age classification");
        ageColumn.setMinWidth(100);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

	table.setItems(getFilm());
        table.getColumns().addAll(movieColumn, actorsColumn, directorsColumn, genreColumn, ageColumn);
	
       
       window.setScene(scene);
       window.show();
       window.setTitle("View films");
       
       bk.setOnAction((e) -> {
              window.close();
              CinemaManagementSystem cinemaManagementSystem= new CinemaManagementSystem();
              cinemaManagementSystem.start(window);
        });
        
        
    }

	    //Get all of the films
        public ObservableList<Film> getFilm(){
	ArrayList<Film> filmList = new Film().getfilm();
	List list = new ArrayList();
  	
	for (Film f : filmList){
		//String movie, String actors, String directors, String genre, String age
		list.add(new Film(
			f.getMovie(),
			f.getActors(),
			f.getDirectors(),
			f.getGenre(),
			f.getAge()
		));

	}
	ObservableList film= FXCollections.observableList(list);
        return film;
}
    
}
