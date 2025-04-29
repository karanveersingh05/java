import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Ex_DatabaseManipulation extends Frame implements ActionListener {

    MenuBar menubar;
    Menu menu;
    MenuItem newitem, displayitem, updateitem, deleteitem;
    Connection con;
    Statement st;

    public Ex_DatabaseManipulation() {
        super("Menu Driven AWT JDBC App");
        setLayout(null);
        setSize(600, 600);

        menubar = new MenuBar();
        menu = new Menu("Options");

        newitem = new MenuItem("New");
        displayitem = new MenuItem("Display");
        updateitem = new MenuItem("Update");
        deleteitem = new MenuItem("Delete");

        newitem.addActionListener(this);
        displayitem.addActionListener(this);
        updateitem.addActionListener(this);
        deleteitem.addActionListener(this);

        menu.add(newitem);
        menu.add(displayitem);
        menu.add(updateitem);
        menu.add(deleteitem);
        menubar.add(menu);

        setMenuBar(menubar);

        connectDB();

        setVisible(true);
    }

    public void connectDB() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dbURL = "jdbc:odbc:AccessDB";
            con = DriverManager.getConnection(dbURL);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == newitem) {
                st.executeUpdate("INSERT INTO MENU1 (ID, FullName, Age) VALUES (1, 'Karan Veer Singh', 21)");
                System.out.println("New record inserted.");
            }
            if (ae.getSource() == displayitem) {
                ResultSet rs = st.executeQuery("SELECT * FROM MENU1");
                while (rs.next()) {
                    System.out.println(rs.getInt("ID") + " " + rs.getString("FullName") + " " + rs.getInt("Age"));
                }
                rs.close();
            }
            if (ae.getSource() == updateitem) {
                st.executeUpdate("UPDATE MENU1 SET FullName='Karan Singh' WHERE ID=1");
                System.out.println("Record updated.");
            }
            if (ae.getSource() == deleteitem) {
                st.executeUpdate("DELETE FROM MENU1 WHERE ID=1");
                System.out.println("Record deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Ex_DatabaseManipulation();
    }
}
