import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataIntoIndianStatesAndCapitalsTable{
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/ravi";
		String uname = "root";
		String pswd = "0000";

		try(Connection connect = DriverManager.getConnection(url,uname,pswd);Scanner sc=new Scanner(System.in)){

			String insertQuery = "INSERT INTO INDIA_STATE_CAPITALS(SNAME,SCAPITAL)VALUES(?,?)";

			try(PreparedStatement pst = connect.prepareStatement(insertQuery)){

				while(true){
					System.out.print("\nEnter State Name  : ");
					String sname = sc.nextLine();

					System.out.print("\nEnter Capital     : ");
					String scapital = sc.nextLine();

					pst.setString(1,sname);
					pst.setString(2,scapital);

					pst.addBatch();

					System.out.print("\nDo you want to insert one more record [YES/NO]  : ");
					String option = sc.nextLine();

					if(option.equalsIgnoreCase("no"))break;
				}
				int insertedRecords[] = pst.executeBatch();

				System.out.println("\nno.of records inserted : "+insertedRecords.length);
			}

		}catch(SQLException sqe){
			sqe.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();

		}
	}
}