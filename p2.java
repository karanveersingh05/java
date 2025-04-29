import java.awt.*;
import java.awt.event.*;

public class p2 extends Frame implements ActionListener {
    Button submitBtn, cancelBtn;
    TextField namefield, passwdfield;
    Checkbox mcheckbox, fcheckbox, h1checkbox, h2checkbox, h3checkbox;
    Choice cityfield;
    List statefield;
    TextArea commentsfield;

    public p2() {
        super("Registration Form");
        setLayout(null);
        setSize(1920, 1080);

        Label name = new Label("Name:");
        name.setBounds(800, 100, 100, 25);
        add(name);

        namefield = new TextField();
        namefield.setBounds(950, 100, 150, 25);
        add(namefield);

        Label passwd = new Label("Password:");
        passwd.setBounds(800, 150, 100, 25);
        add(passwd);

        passwdfield = new TextField();
        passwdfield.setBounds(950, 150, 150, 25);
        passwdfield.setEchoChar('*');
        add(passwdfield);

        Label gender = new Label("Gender:");
        gender.setBounds(800, 200, 100, 25);
        add(gender);

        CheckboxGroup genderGroup = new CheckboxGroup();
        mcheckbox = new Checkbox("Male", genderGroup, false);
        mcheckbox.setBounds(950, 200, 70, 25);
        add(mcheckbox);

        fcheckbox = new Checkbox("Female", genderGroup, false);
        fcheckbox.setBounds(1030, 200, 70, 25);
        add(fcheckbox);

        Label hobbies = new Label("Hobbies:");
        hobbies.setBounds(800, 250, 100, 25);
        add(hobbies);

        h1checkbox = new Checkbox("Football");
        h1checkbox.setBounds(950, 250, 80, 25);
        add(h1checkbox);

        h2checkbox = new Checkbox("Basketball");
        h2checkbox.setBounds(1030, 250, 80, 25);
        add(h2checkbox);

        h3checkbox = new Checkbox("Swimming");
        h3checkbox.setBounds(1110, 250, 80, 25);
        add(h3checkbox);

        Label city = new Label("City:");
        city.setBounds(800, 300, 100, 25);
        add(city);

        cityfield = new Choice();
        cityfield.add("Select City");
        cityfield.add("New Delhi");
        cityfield.add("Jaipur");
        cityfield.add("Mumbai");
        cityfield.add("Kolkata");
        cityfield.add("Bengaluru");
        cityfield.add("Chennai");
        cityfield.add("Noida");
        cityfield.add("Dehradun");
        cityfield.setBounds(950, 300, 150, 25);
        add(cityfield);

        Label state = new Label("State:");
        state.setBounds(800, 350, 100, 25);
        add(state);

        statefield = new List();
        statefield.add("Select State");
        statefield.add("Delhi");
        statefield.add("Rajasthan");
        statefield.add("Maharashtra");
        statefield.add("West Bengal");
        statefield.add("Karnataka");
        statefield.add("Tamil Nadu");
        statefield.add("Uttar Pradesh");
        statefield.add("Uttarakhand");
        statefield.setBounds(950, 350, 150, 100);
        add(statefield);

        Label comments = new Label("Comments:");
        comments.setBounds(800, 470, 100, 25);
        add(comments);

        commentsfield = new TextArea();
        commentsfield.setBounds(950, 470, 300, 150);
        add(commentsfield);

        submitBtn = new Button("Submit");
        submitBtn.setBounds(900, 650, 80, 30);
        submitBtn.addActionListener(this);
        add(submitBtn);

        cancelBtn = new Button("Exit");
        cancelBtn.setBounds(1000, 650, 80, 30);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitBtn) {
            new f2(namefield.getText());
        } else if (ae.getSource() == cancelBtn) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        p2 obj = new p2();
    }
}
