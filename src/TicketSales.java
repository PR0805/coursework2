import java.sql.*;

public class TicketSales{
	
	private int salesId;
	private int showingId;
	private int quantity;

	public TicketSales(int salesId, int showingId, int quantity){
		
		this.salesId = salesId;
		this.showingId = showingId;
		this.quantity = quantity;

	}

	public TicketSales(int showingId, int quantity){
		
		this.showingId = showingId;
		this.quantity = quantity;

	}

	public int getSalesId(){
		
		return salesId;

	}

	public void setSalesId(int salesId){
		
		this.salesId = salesId;

	}

	public int getShowingId(){
		
		return showingId;

	}

	public void setShowingId(int showingId){
		
		this.showingId = showingId;

	}

	public int getQuantity(){
		
		return quantity;

	}

	public void setQuantity(int quantity){
		
		this.quantity = quantity;

	}

	public boolean recordSales() throws SQLException{
		
		boolean successful = false;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		String query = "insert into TicketSales (showing_id, quantity) values (?, ?)";

		try {
		
			con = new DbConnection().establishConnection();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, showingId);
			preparedStatement.setInt(2, quantity);

			preparedStatement.executeUpdate();
			successful = true;

		} catch(SQLException e){

			System.out.print("Error:" + e.getMessage());

		}  finally {
			
			if (preparedStatement != null) {
				
		 		preparedStatement.close();

			}

			if (con != null) {
				
				con.close();

			}

		}
		
		return successful;

	}


}
