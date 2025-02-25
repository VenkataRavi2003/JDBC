import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CreatingAndInsertingTheRecordsIntoEmployeeTable{
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/ravi";
		String uname = "root";
		String pswd = "0000";

		try(Connection connect = DriverManager.getConnection(url,uname,pswd)){

			String creatingTableQuery = "CREATE TABLE EMPLOYEE (EID INT PRIMARY KEY AUTO_INCREMENT,ENAME VARCHAR(30) NOT NULL,JOB_TYPE VARCHAR(30) NOT NULL,ESALRY DOUBLE NOT NULL);";

			try(Statement stmnt = connect.createStatement()){

				int count = stmnt.executeUpdate(creatingTableQuery);
				if(count > 0){
					System.out.println("\nTable Created Successfully.");
				}
			}

			String INSERT_QUERY = "INSERT INTO EMPLOYEE(ENAME,JOB_TYPE,ESALRY)VALUES(?,?,?)";

			try(Scanner sc = new Scanner(System.in);PreparedStatement pst = connect.prepareStatement(INSERT_QUERY)){

				while(true){

					System.out.print("\nEnter Employee Name  : ");
					String ename =  sc.next();
					
					System.out.print("\nEnter JOB_TYPE       :  ");
					String jobType = sc.next();

					System.out.print("\nEnter Employee Salary : ");
					double salary = sc.nextDouble();

					pst.setString(1,ename);
					pst.setString(2,jobType);
					pst.setDouble(3,salary);

					pst.executeUpdate();

					pst.addBatch();

					System.out.print("\nDo you want to insert one more record [YES/NO]  : ");
					String option = sc.next();

					if(option.equalsIgnoreCase("no"))break;

				}

				int records[] = pst.executeBatch();

				System.out.println("\nno.of recored inserted : "+records.length);

			}

		}catch(SQLException sqe){
			System.out.println("\n"+sqe.getMessage());
		}catch(Exception e){
			System.out.println("\n"+e.getMessage());
		}
	}
}