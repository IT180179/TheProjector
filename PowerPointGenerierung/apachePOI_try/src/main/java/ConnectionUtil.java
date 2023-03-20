import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public Connection conn(String dbname, String user, String pass){

        Connection conn = null;

        try{
            Class.forName("org.postgresql.Drive");

            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=TheProjector" + dbname+user+pass);

            if(conn != null){
                System.out.println("Connection Established");
            } else{
                System.out.println("Connection Failes");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;

    }

}
