import java.sql.*;

public class ListTables {
    public static void main(String[] args) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dbURL = "jdbc:odbc:AccessDB"; // Ensure this DSN is correctly set up
            Connection con = DriverManager.getConnection(dbURL);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM MSysObjects WHERE type=1 AND name NOT LIKE 'MSys%'");

            System.out.println("Tables in the database:");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
