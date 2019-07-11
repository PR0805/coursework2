import java.util.ArrayList;
import java.sql.*;

public class Showing {

    private int showing;
    private String movie;
    private String datetm;
    private int room;
    private int tickets;


    public Showing(){
        this.showing = 0;
        this.movie = "";
        this.datetm = "";
        this.room = 0;
        this.tickets = 0;
    }

    public Showing(int showing, String movie, String datetm, int room){
        this.showing = showing;
        this.movie = movie;
        this.datetm = datetm;
        this.room = room;
    }

    public int getShowing() {
        return showing;
    }

    public void setShowing(int showing) {
        this.showing = showing;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDatetm() {
        return datetm;
    }

    public void setDirectors(String datetm) {
        this.datetm = datetm;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
    
    public int getTickets(){ 
	return tickets;
    }

    private void setTickets(int tickets){
	this.tickets = tickets;
    }

    
    public ArrayList getshowing() {
    ArrayList<Showing> showingList = new ArrayList();

    PreparedStatement preparedStmt = null;
    Connection conn = null;

     try
    {
      conn = new DbConnection().establishConnection();
    
      // create the java mysql update preparedstatement
      String query = "select * from Showings";
      preparedStmt = conn.prepareStatement(query);


      // execute the java preparedstatement
      ResultSet rs = preparedStmt.executeQuery();

      while (rs.next()) {
	
	showingList.add(new Showing(
	
		rs.getInt("showing_id"),
		rs.getString("movie_title"),
		rs.getString("date_time"),
		rs.getInt("room_number")
		
	
	));      

      }
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }

    return showingList;

    } 


    private boolean isAvailable(){
	
	boolean available = false;	
	Connection con = null;
	PreparedStatement prepStatement = null;

	String query = "select tickets_available from Showing where showing_id = ?";
	try {

		con = new DbConnection().establishConnection();
		prepStatement = con.prepareStatement(query);

		prepStatement.setInt(1, tickets);
		ResultSet resultSet = prepStatement.executeQuery();

		if (resultSet.next()){
			
			available = true;

		}

	} catch(SQLException e) {

		System.err.print("Error:" + e.getMessage());
		
	}

	return available;

    }

 /*   public boolean sellTickets(){
	
	boolean available = isAvailable();
	boolean successful = true;
	Connection con = null;
	PreparedStatement prepStatement = null;

	String query = "update tickets_available from";
	
	try {
		if (available) {
		
			

		}

	} catch(SQLException e) {
		
		System.err.print("Error:" + e.getMessage());

	}

    } */
}


