import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class f1 extends Frame {
    f1() {
        super("Form");
        setLayout(null);

        Label name = new Label("Name:");
        name.setBounds(800, 100, 100, 25);
        add(name);

        final TextField nameField = new TextField(); 
        nameField.setBounds(950, 100, 150, 25);
        add(nameField);

        Label passwd = new Label("Password:");
        passwd.setBounds(800, 150, 100, 25);
        add(passwd);

        final TextField passwdField = new TextField();
        passwdField.setBounds(950, 150, 150, 25);
        passwdField.setEchoChar('*'); 
        add(passwdField);

        Label gender = new Label("Gender:");
        gender.setBounds(800, 200, 100, 25);
        add(gender);

        final CheckboxGroup genderGroup = new CheckboxGroup();
        final Checkbox mCheckbox = new Checkbox("Male", genderGroup, false);
        mCheckbox.setBounds(950, 200, 70, 25);
        add(mCheckbox);

        final Checkbox fCheckbox = new Checkbox("Female", genderGroup, false);
        fCheckbox.setBounds(1020, 200, 70, 25);
        add(fCheckbox);

        Label hobbies = new Label("Hobbies:");
        hobbies.setBounds(800, 250, 100, 25);
        add(hobbies);

        final Checkbox h1Checkbox = new Checkbox("Football");
        h1Checkbox.setBounds(950, 250, 80, 25);
        add(h1Checkbox);

        final Checkbox h2Checkbox = new Checkbox("Basketball");
        h2Checkbox.setBounds(1030, 250, 80, 25);
        add(h2Checkbox);

        final Checkbox h3Checkbox = new Checkbox("Swimming");
        h3Checkbox.setBounds(1110, 250, 80, 25);
        add(h3Checkbox);

        Label city = new Label("City:");
        city.setBounds(800, 300, 100, 25);
        add(city);

        final Choice cityField = new Choice();
        cityField.add("New Delhi");
        cityField.add("Jaipur");
        cityField.add("Mumbai");
        cityField.add("Kolkata");
        cityField.add("Bengaluru");
        cityField.setBounds(950, 300, 150, 25);
        add(cityField);

        Label state = new Label("State:");
        state.setBounds(800, 350, 100, 25);
        add(state);

        final List stateField = new List();
        stateField.add("Delhi");
        stateField.add("Rajasthan");
        stateField.add("Maharashtra");
        stateField.add("West Bengal");
        stateField.add("Karnataka");
        stateField.setBounds(950, 350, 150, 25);
        add(stateField);

        Label comments = new Label("Comments:");
        comments.setBounds(800, 410, 100, 25);
        add(comments);

        final TextArea commentsField = new TextArea();
        commentsField.setBounds(950, 410, 300, 150);
        add(commentsField);

        Button submitBtn = new Button("Submit");
        submitBtn.setBounds(900, 600, 80, 30);
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = passwdField.getText();
                String gender = mCheckbox.getState() ? "Male" : "Female";
                String hobbies = "";
                if (h1Checkbox.getState()) hobbies += "Football ";
                if (h2Checkbox.getState()) hobbies += "Basketball ";
                if (h3Checkbox.getState()) hobbies += "Swimming ";
                String city = cityField.getSelectedItem();
                String state = stateField.getSelectedItem();
                String comments = commentsField.getText();

                saveToDatabase(name, password, gender, hobbies, city, state, comments);
                new f2(name);
            }
        });
        add(submitBtn);

        Button cancelBtn = new Button("Exit");
        cancelBtn.setBounds(1000, 600, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(cancelBtn);

        setSize(1920, 1080);
        setVisible(true);
    }

    void saveToDatabase(String name, String password, String gender, String hobbies, String city, String state, String comments) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:AccessDB");
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO form (Name, Password, Gender, Hobbies, City, State, Comment) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, gender);
            pst.setString(4, hobbies);
            pst.setString(5, city);
            pst.setString(6, state);
            pst.setString(7, comments);
            pst.executeUpdate();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class f2 extends Frame {
    f2(String labelText) {
        super("Form Submitted");
        setLayout(null);

        Label label1 = new Label("Thank you, " + labelText + "!");
        label1.setBounds(200, 200, 500, 25);
        add(label1);

        Label label2 = new Label("Your entry has been successfully submitted!");
        label2.setBounds(200, 225, 500, 25);
        add(label2);

        Button closeBtn = new Button("Exit");
        closeBtn.setBounds(300, 300, 100, 25);
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(closeBtn);

        setSize(800, 800);
        setVisible(true);
    }
}

public class p2db {
    public static void main(String[] args) {
        new f1();
    }
}
