import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserAddress extends JFrame implements ActionListener {
    Label lblState, lblCity, lblVillage;
    JComboBox cbState, cbCity, cbVillage;
    Button btnSubmit, btnExit;

    Connection con;
    Statement st;
    ResultSet rs;

    public UserAddress() {
        setLayout(null);
        setSize(400, 300);
        setTitle("Select Address");

        lblState = new Label("State:");
        lblCity = new Label("City:");
        lblVillage = new Label("Village:");

        cbState = new JComboBox();
        cbCity = new JComboBox();
        cbVillage = new JComboBox();

        btnSubmit = new Button("Submit");
        btnExit = new Button("Exit");

        lblState.setBounds(50, 50, 100, 20);
        cbState.setBounds(150, 50, 150, 25);

        lblCity.setBounds(50, 90, 100, 20);
        cbCity.setBounds(150, 90, 150, 25);

        lblVillage.setBounds(50, 130, 100, 20);
        cbVillage.setBounds(150, 130, 150, 25);

        btnSubmit.setBounds(100, 190, 80, 30);
        btnExit.setBounds(200, 190, 80, 30);

        add(lblState);
        add(cbState);
        add(lblCity);
        add(cbCity);
        add(lblVillage);
        add(cbVillage);
        add(btnSubmit);
        add(btnExit);

        cbState.addActionListener(this);
        cbCity.addActionListener(this);
        btnSubmit.addActionListener(this);
        btnExit.addActionListener(this);
        cbCity.setEnabled(false);
        cbVillage.setEnabled(false);

        connectDB();
        loadStates();

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void connectDB() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dbURL = "jdbc:odbc:AccessDB"; 
            con = DriverManager.getConnection(dbURL);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadStates() {
        try {
            cbState.removeAllItems();
            rs = st.executeQuery("SELECT DISTINCT state FROM addresstbl");
            while (rs.next()) {
                cbState.addItem(rs.getString("state"));
            }
            cbCity.removeAllItems();
            cbVillage.removeAllItems();
            cbCity.setEnabled(false);
            cbVillage.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadCities(String state) {
        try {
            cbCity.removeAllItems();
            rs = st.executeQuery("SELECT DISTINCT city FROM addresstbl WHERE state='" + state + "'");
            while (rs.next()) {
                cbCity.addItem(rs.getString("city"));
            }
            cbCity.setEnabled(true);
            cbVillage.removeAllItems();
            cbVillage.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadVillages(String state, String city) {
        try {
            cbVillage.removeAllItems();
            rs = st.executeQuery("SELECT DISTINCT village FROM addresstbl WHERE state='" + state + "' AND city='" + city + "'");
            while (rs.next()) {
                cbVillage.addItem(rs.getString("village"));
            }
            cbVillage.setEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cbState) {
            String selectedState = (String) cbState.getSelectedItem();
            if (selectedState != null) {
                loadCities(selectedState);
            }
        } else if (ae.getSource() == cbCity) {
            String selectedState = (String) cbState.getSelectedItem();
            String selectedCity = (String) cbCity.getSelectedItem();
            if (selectedState != null && selectedCity != null) {
                loadVillages(selectedState, selectedCity);
            }
        } else if (ae.getSource() == btnSubmit) {
            String state = (String) cbState.getSelectedItem();
            String city = (String) cbCity.getSelectedItem();
            String village = (String) cbVillage.getSelectedItem();

            if (state != null && city != null && village != null) {
                new ResultWindow(state, city, village);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select State, City, and Village.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new UserAddress();
    }
}
