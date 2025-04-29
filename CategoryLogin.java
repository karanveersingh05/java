import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CategoryLogin extends Frame implements ActionListener {

    Label lbluser, lblpass, lblcat;
    TextField txtuser, txtpass;
    Choice chcat;
    Button btnlogin, btncancel;
    Connection con;
    Statement st;
    ResultSet rs;

    public CategoryLogin() {
        super("Category Login");
        setLayout(null);
        setSize(420, 280);

        lbluser = new Label("Username:");
        lblpass = new Label("Password:");
        lblcat = new Label("Category:");

        txtuser = new TextField();
        txtpass = new TextField();
        txtpass.setEchoChar('*');

        chcat = new Choice();
        chcat.add("Student");
        chcat.add("Faculty");
        chcat.add("Parent");

        btnlogin = new Button("Login");
        btncancel = new Button("Cancel");

        lbluser.setBounds(60, 60, 110, 27);
        lblpass.setBounds(60, 110, 110, 27);
        lblcat.setBounds(60, 160, 110, 27);

        txtuser.setBounds(180, 60, 140, 28);
        txtpass.setBounds(180, 110, 140, 28);
        chcat.setBounds(180, 160, 140, 28);

        btnlogin.setBounds(110, 210, 90, 33);
        btncancel.setBounds(220, 210, 90, 33);

        btnlogin.addActionListener(this);
        btncancel.addActionListener(this);

        add(lbluser);
        add(lblpass);
        add(lblcat);
        add(txtuser);
        add(txtpass);
        add(chcat);
        add(btnlogin);
        add(btncancel);

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
        if (ae.getSource() == btnlogin) {
            try {
                String username = txtuser.getText();
                String password = txtpass.getText();
                String category = chcat.getSelectedItem();

                String query = "SELECT * FROM MENU1 WHERE Username='" + username + "' AND Password='" + password + "' AND Category='" + category + "'";
                rs = st.executeQuery(query);

                if (rs.next()) {
                    new LoginSuccessFrame(username, category);
                    this.dispose();
                } else {
                    new LoginFailedFrame();
                    this.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == btncancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new CategoryLogin();
    }
}
