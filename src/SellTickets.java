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
import javafx.scene.control.TablePosition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SellTickets {
    
    Stage window;
    Scene scene;
    VBox vbox;
    GridPane gridPane;
    Button bk;
    TableView<Showing> table;
    Button sell;
    Label quantityLabel;
    TextField quantity;
    
    public SellTickets(){
        
       window = new Stage();
       vbox = new VBox();
       scene = new Scene(vbox, 2000, 1000);
       gridPane = new GridPane();
       bk = new Button("Back");
       table = new TableView<Showing>();
       sell = new Button("Sell Tickets");
       quantity = new TextField();
       quantityLabel = new Label("Quantity");
       
       vbox.getChildren().addAll(gridPane, table);
       vbox.setAlignment(Pos.CENTER);
       gridPane.getChildren().addAll(bk, sell, quantity, quantityLabel);
       
        gridPane.setVgap(50);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(10,0,0,10));
       
       GridPane.setConstraints(bk, 0, 1);
       GridPane.setConstraints(sell, 1, 1);
       GridPane.setConstraints(quantityLabel, 0, 0);
       GridPane.setConstraints(quantity, 1, 0);
	
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
        roomColumn.setMinWidth(300);
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));

	TableColumn<Showing, String> ticketColumn = new TableColumn<>("Tickets available");
        ticketColumn.setMinWidth(300);
       	ticketColumn.setCellValueFactory(new PropertyValueFactory<>("Tickets"));


	table.setItems(getShowing());
        table.getColumns().addAll(showingColumn, movieColumn, datetmColumn, roomColumn, ticketColumn);
	
       
       window.setScene(scene);
       window.show();
       window.setTitle("Select and Sell Tickets");
       
       bk.setOnAction((e) -> {
              window.close();
              Coursework2 coursework2= new Coursework2();
              coursework2.start(window);
        });

        sell.setOnAction((e) -> {
	
		TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);	
		int row = pos.getRow();
		Showing item = table.getItems().get(row);
		
		TableColumn col = pos.getTableColumn();
		
		try {

			int value = (int) col.getCellObservableValue(item).getValue();
		
			try {

				int ticketQuantity = Integer.parseInt(quantity.getText());
				if (sellTicket(value, ticketQuantity)){
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Successful");
					alert.setHeaderText("Transaction successful");
					alert.setContentText("Thank you!");
					alert.show();

				} else {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Operation failed");
					alert.setHeaderText("There are not enough tickets available.");
					alert.setContentText("We thank you for your understanding.");
					alert.show();


				}

			} catch (NumberFormatException ex) {
				
				System.err.print("Error:" + ex.getMessage());
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Something went wrong!\n Please enter numerical characters only for quantity.");
				alert.setContentText("Please try again!");
				alert.show();

			}

		} catch (Exception ex){
			
			System.err.print("Error: Incorrect column selected.");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Something went wrong!\n Please select the showing ID to sell ticket.");
			alert.setContentText("Please try again!");
			alert.show();

		}	
		

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
				//String showing, String movie, DateTime datetm, int room
				list.add(new Showing(
					s.getShowing(),
					s.getMovie(),
					s.getDatetm(),
					s.getRoom(),
					s.getTickets()
				));

			}

		} catch(SQLException e){
			
			System.err.print("Error:" + e.getMessage());

		}

		ObservableList showing= FXCollections.observableList(list);
		return showing;

	}

	private boolean sellTicket(int value, int quantity){
		
		boolean successful = false;
		try {
			
			successful = new Showing(value, quantity).sellTickets();
			if (successful) {
				
				boolean saleRecorded = new TicketSales(value, quantity).recordSales();
				if (saleRecorded) {
					
					System.out.print("Sale recorded in database");
 
				} else {
					
					System.err.print("Error: Transaction was successful, but the sale has not been recorded.");

				}

			}

		} catch (SQLException e){
			
			System.err.print("Error" + e.getMessage());

		}

	return successful;
		
	}
    
}
