/*
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Testverbindung {

    public static void main(String[] args) {

        Testverbindung connection = new Testverbindung();

        try{
            connection.test();
        }catch (SQLException ex) {
            Logger.getLogger(Testverbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void test() throws SQLException{

        DriverManager.registerDriver(new org.postgresql.Driver());
        String url ="jdbc:postgresql://localhost/Edu4You";


    }

}
*/