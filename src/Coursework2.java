import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Coursework2 extends Application {
    
    FlowPane flowPane;
    Scene scene;
    GridPane gridPane;
    Button vns;
    Button vst;
    Button st;
   
    @Override
    public void start(Stage primaryStage) {
        
        flowPane = new FlowPane();
        scene = new Scene(flowPane, 2000,1000);
        gridPane = new GridPane();
        vns = new Button("View Films");
        vst = new Button("View Showing Time");
        st = new Button("Select and Sell Tickets");
        
        
        flowPane.getChildren().add(gridPane);
        flowPane.setAlignment(Pos.CENTER);
        
        gridPane.getChildren().addAll(vns, vst, st);
        
        gridPane.setVgap(50);
        gridPane.setHgap(20);
        gridPane.setPadding(new Insets(10,0,0,10));
        
        GridPane.setConstraints(vns, 0, 0);
        GridPane.setConstraints(vst, 0, 1);
        GridPane.setConstraints(st, 0, 2);
        
        
        vns.setOnAction((e) -> {
              ViewAndSelect viewAndSelect= new ViewAndSelect();
              primaryStage.close();
        });
        
        vst.setOnAction((e) -> {
              ViewShowingTime viewShowingTime= new ViewShowingTime();
              primaryStage.close();
        });
        
        st.setOnAction((e) -> {
              SellTickets sellTickets= new SellTickets();
              primaryStage.close();
        });
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home page");
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
