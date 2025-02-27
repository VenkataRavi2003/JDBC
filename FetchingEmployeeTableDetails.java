import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
public class FetchingEmployeeTableDetails{

	private static final String URL = "jdbc:mysql://localhost:3306/ravi";
	private static final String UNAME = "root";
	private static final String PSWD = "0000";
	private static final String SELECT_QUERY = "SELECT ID,ENAME,JOB_ROLE,SALARY FROM EMPLOYEE";

	public static void main(String[] args) {
		
		DataSource ds = createDataSource();

		try(Connection connect = ds.getConnection();
	        PreparedStatement pst = connect.prepareStatement(SELECT_QUERY);
	        ResultSet rset = pst.executeQuery()){

			System.out.println("\n::Employee Table::");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
			while (rset.next()==true) {
				System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t\t"+rset.getString(3)+"\t\t\t"+rset.getFloat(4));
			}

		}catch (SQLException e) {
			System.out.println("\nSQLException Occured :: "+e.getMessage());
		}catch (Exception e) {
			System.out.println("\nException Occured :: "+e.getMessage());
		}




	}
	public static DataSource createDataSource(){

		MysqlDataSource msd = new MysqlDataSource();

		msd.setURL(URL);
		msd.setUser(UNAME);
		msd.setPassword(PSWD);

		return msd;
	}
}