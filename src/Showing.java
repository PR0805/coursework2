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

    public Showing(int showing, String movie, String datetm, int room, int tickets){
        this.showing = showing;
        this.movie = movie;
        this.datetm = datetm;
        this.room = room;
	this.tickets = tickets;
    }

    public Showing(int showing, int tickets){
	this.showing = showing;
	this.tickets = tickets;
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

    
    public ArrayList getshowing() throws SQLException{
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
			rs.getInt("room_number"),
			rs.getInt("tickets_available")
		
		));      

	      }
	      
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    } finally {
		
		if (preparedStmt != null) {
				
		 	preparedStmt.close();

		}

		if (conn != null) {
			
			conn.close();

		}

	    }

	    return showingList;

    } 


    private boolean isAvailable(int ticketQuantity) throws SQLException{
	
	boolean available = false;	
	Connection con = null;
	PreparedStatement prepStatement = null;

	String query = "select * from Showings where showing_id = ? and tickets_available >= ?";
	try {

		con = new DbConnection().establishConnection();
		prepStatement = con.prepareStatement(query);

		prepStatement.setInt(1, showing);
		prepStatement.setInt(2, ticketQuantity);
		ResultSet resultSet = prepStatement.executeQuery();

		if (resultSet.next()){
			
			available = true;

		}

	} catch(SQLException e) {

		System.err.print("Error:" + e.getMessage());
		
	} finally {
		
		if (prepStatement != null) {
				
		 	prepStatement.close();

		}

		if (con != null) {
			
			con.close();

		}

	}

	return available;

    }

  public boolean sellTickets() throws SQLException{
	
	boolean successful = false;
	Connection con = null;
	PreparedStatement prepStatement = null;

	String query = "update Showings set tickets_available = tickets_available - ? where showing_id = ?";
	
	try {
		if (isAvailable(tickets)) {
		
			con = new DbConnection().establishConnection();
			prepStatement = con.prepareStatement(query);

			prepStatement.setInt(1, tickets);
			prepStatement.setInt(2, showing);

			prepStatement.executeUpdate();
			successful = true;

		} 

	} catch(SQLException e) {
		
		System.err.print("Error:" + e.getMessage());

	} finally {
		
		if (prepStatement != null) {
				
		 	prepStatement.close();

		}

		if (con != null) {
			
			con.close();

		}
	}

	return successful;

    } 
}


