import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
public class InsertingEmployeesRecords{

	private static String url = "jdbc:mysql://localhost:3306/venkat" , uname = "root" , pswd = "0000";

	private static String INSERT_QUERY = "INSERT INTO EMPLOYEE(ENAME,JOB_ROLE,SALARY)VALUES(?,?,?)";

	public static void main(String[] args) {
		
		DataSource ds = createDataSource();

		try(Connection connect = ds.getConnection();
	        PreparedStatement pst = connect.prepareStatement(INSERT_QUERY);
	        Scanner sc = new Scanner(System.in)){

			while (true) {
				
				System.out.print("\nEnter the Employee Name -> ");
				String ename = sc.nextLine();

				System.out.print("\nEnter the Job_Role  -> ");
				String job_role = sc.nextLine();

				System.out.print("\nEnter the Salary -> ");
				float esalary = sc.nextFloat();

				pst.setString(1,ename);
				pst.setString(2,job_role);
				pst.setFloat(3,esalary);

				pst.addBatch();

				sc.nextLine();
				System.out.print("\nDo you want to insert one more record (yes / no) : ");
				String option = sc.nextLine();

				if(option.equalsIgnoreCase("no"))break;
			}

			int insertedRecords[] = pst.executeBatch();
			System.out.println("\nNo.of records inserted :: "+insertedRecords.length);

		}catch (SQLException e) {
			System.out.println("\nSQLException Occured :: "+e.getMessage());
		}catch (InputMismatchException e) {
			System.out.println("\nInstantiationException Occured :: "+e.getMessage());
		}catch (Exception e) {
			System.out.println("\nException Occured :: "+e.getMessage());
		}
	}
	public static DataSource createDataSource(){

		MysqlDataSource msd = new MysqlDataSource();

		msd.setURL(url);
		msd.setUser(uname);
		msd.setPassword(pswd);

		return msd;
	}

}