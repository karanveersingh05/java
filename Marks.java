import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Marks extends JFrame implements ActionListener {
    Container container;
    JLabel lblTitle, lblFrom, lblTo, lblResult;
    JButton btnShow;
    JComboBox marksFromCombo, marksToCombo;
    Connection con;
    Statement st;
    ResultSet rs;

    public Marks() {
        setBounds(200, 200, 700, 400);
        setLayout(null);
        container = getContentPane();

        lblTitle = new JLabel("Select range of marks", JLabel.CENTER);
        lblFrom = new JLabel("From", JLabel.RIGHT);
        lblTo = new JLabel("To", JLabel.RIGHT);
        lblResult = new JLabel("");
        btnShow = new JButton("Show");

        marksFromCombo = new JComboBox();
        marksToCombo = new JComboBox();

        btnShow.addActionListener(this);

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:AccessDB");
            st = con.createStatement();

            rs = st.executeQuery("SELECT MAX(maxmks) AS maxMarks FROM smks");
            int maxMarks = 100;
            if (rs.next()) {
                maxMarks = rs.getInt("maxMarks");
            }

            for (int i = 0; i <= maxMarks; i++) {
                marksFromCombo.addItem(new Integer(i));
                marksToCombo.addItem(new Integer(i));
            }
        } catch (Exception e) {
            System.out.println("Error fetching max marks: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error closing DB resources: " + e.getMessage());
            }
        }

        lblTitle.setBounds(250, 60, 200, 20);
        lblFrom.setBounds(80, 140, 40, 20);
        lblTo.setBounds(280, 140, 20, 20);
        marksFromCombo.setBounds(115, 140, 100, 20);
        marksToCombo.setBounds(300, 140, 100, 20);
        btnShow.setBounds(450, 140, 100, 20);
        lblResult.setBounds(200, 220, 350, 20);

        container.add(lblTitle);
        container.add(lblFrom);
        container.add(lblTo);
        container.add(marksFromCombo);
        container.add(marksToCombo);
        container.add(btnShow);
        container.add(lblResult);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        int fromMarks = ((Integer) marksFromCombo.getSelectedItem()).intValue();
        int toMarks = ((Integer) marksToCombo.getSelectedItem()).intValue();

        if (fromMarks > toMarks) {
            int temp = fromMarks;
            fromMarks = toMarks;
            toMarks = temp;
        }

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:AccessDB");
            st = con.createStatement();

            String query = "SELECT COUNT(mks) AS count FROM smks WHERE mks BETWEEN " 
                            + fromMarks + " AND " + toMarks;
            rs = st.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt("count");
                lblResult.setText(count + " student(s) scored marks from " + fromMarks + " to " + toMarks + ".");
            }
        } catch (Exception e) {
            System.out.println("Error fetching student count: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error closing DB resources: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Marks();
    }
}
