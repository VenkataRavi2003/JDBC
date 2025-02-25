import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.SQLException;
public class CreatingAndInsertingTheRecordsIntoIPLTeamsTable{
	private static String Create_Table_Query = "CREATE TABLE IPLTEAMS(ID INT PRIMARY KEY AUTO_INCREMENT,TEAM_NAME VARCHAR(30) NOT NULL UNIQUE,STATE_NAME VARCHAR(30) NOT NULL UNIQUE,CAPTAIN_NAME VARCHAR(30) NOT NULL)";
	private static String Insert_Query = "INSERT INTO IPLTEAMS(TEAM_NAME,STATE_NAME,CAPTAIN_NAME)VALUES(?,?,?)";

	public static void main(String[] args) {

		String url    = "jdbc:mysql://localhost:3306/ravi";
		String uname  = "root";
		String pswd   = "0000";

		try(Connection connect = DriverManager.getConnection(url,uname,pswd)){

			try(Statement stmnt = connect.createStatement()){

				stmnt.executeUpdate(Create_Table_Query);

				System.out.println("\nTable Created Successfully.");


			}
			try(Scanner sc = new Scanner(System.in);PreparedStatement pst = connect.prepareStatement(Insert_Query)){

				while(true){

					System.out.print("\nTeam Name    : ");
					String tname = sc.nextLine();

					System.out.print("\nTeam State   : ");
					String tstate = sc.nextLine();

					System.out.print("\nTeam Captain : ");
					String tcap = sc.nextLine();

					pst.setString(1,tname);
					pst.setString(2,tstate);
					pst.setString(3,tcap);

					pst.addBatch();

					System.out.print("\nDo you want to insert one more record [YES/No] : ");
					String option = sc.nextLine();

					if(option.equalsIgnoreCase("no"))break;

				}
				int insertedRecords[] = pst.executeBatch();

				System.out.println("\nno.of records inserted : "+insertedRecords.length);
			}

		}catch(SQLException sqe){

			System.out.println("\n"+sqe.getMessage());

		}catch(Exception e){

			System.out.println("\n"+e.getMessage());

		}
	}
}