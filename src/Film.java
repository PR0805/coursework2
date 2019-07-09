import java.util.ArrayList;
import java.sql.*;

public class Film {

    private String movie;
    private String actors;
    private String directors;
    private String genre;
    private String age;


    public Film(){
        this.movie = "";
        this.actors = "";
        this.directors = "";
        this.genre = "";
        this.age = "";

    }

    public Film(String movie, String actors, String directors, String genre, String age){
        this.movie = movie;
        this.actors = actors;
        this.directors = directors;
        this.genre = genre;
        this.age = age;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    
    public ArrayList getfilm() {
    ArrayList<Film> filmList = new ArrayList();

     try
    {
      Connection conn = new DbConnection().establishConnection();
    
      // create the java mysql update preparedstatement
      String query = "select * from Films";
      PreparedStatement preparedStmt = conn.prepareStatement(query);


      // execute the java preparedstatement
      ResultSet rs = preparedStmt.executeQuery();

      while (rs.next()) {
	
	filmList.add(new Film(
	
		rs.getString("movie_title"),
		rs.getString("star_actor"),
		rs.getString("director"),
		rs.getString("genre"),
		rs.getString("age_classification")
		
	
	));      

      }
      
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }

    return filmList;

    }

}


