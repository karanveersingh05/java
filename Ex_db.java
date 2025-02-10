import java.sql.*;

public class Ex_db {
    public static void main(String[] args) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dbURL = "jdbc:odbc:AccessDB";
            Connection con = DriverManager.getConnection(dbURL);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM [info_table]");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                System.out.println(rs.getInt(1));    
                System.out.println(rs.getString(2)); 
                System.out.println(rs.getInt(3));    
            }

            if (!hasData) {
                System.out.println("No data found in the table.");
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
