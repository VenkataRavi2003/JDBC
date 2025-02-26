import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FetchingIplTeamDetails{

	private static String selectQuery = "Select * from iplteams";

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ravi";
		String uname = "root";
		String pswd = "0000";

		try(Connection connect = DriverManager.getConnection(url,uname,pswd)){

			try(PreparedStatement pst = connect.prepareStatement(selectQuery)){

				try(ResultSet rset = pst.executeQuery()){
					System.out.println("ID\tTeam_Name\tTeam_State\tCaptain");
					System.out.println("~~ \t~~~~~~~~~~~~~~\t~~~~~~~~~\t~~~~~~~");
					while(rset.next() == true){
						System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t\t"+rset.getString(3)+"\t\t"+rset.getString(4));
					}

				}

			}
		}catch(SQLException sqe){
			sqe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}