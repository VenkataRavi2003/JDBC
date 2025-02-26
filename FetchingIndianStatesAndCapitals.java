import java.util.Properties;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
public class FetchingIndianStatesAndCapitals{

	private static final String SELECT_QUERY = "SELECT ID,STATE,CAPITAL FROM INDIANSTATESANDFAMOUSPLACES";
	public static void main(String[] args) {
		
		DataSource ds = createDataSource("SC.properties");

		try(Connection connect = ds.getConnection();
	        PreparedStatement pst = connect.prepareStatement(SELECT_QUERY);
	        ResultSet rset = pst.executeQuery()){

			while (rset.next() == true) {
				System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t\t"+rset.getString(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DataSource createDataSource(String pname){

		Properties ps = new Properties();

		try(FileInputStream fis = new FileInputStream(pname)){
			ps.load(fis);
		}catch (IOException e) {
			e.printStackTrace();
		}

		MysqlDataSource msd = new MysqlDataSource();
		msd.setURL(ps.getProperty("url"));
		msd.setUser(ps.getProperty("uname"));
		msd.setPassword(ps.getProperty("pswd"));

		return msd;
	}
}