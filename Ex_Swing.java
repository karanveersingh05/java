import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Form extends JFrame {
	Container c1;
    Form() {
        super("Form");
        setLayout(null);
		c1= getContentPane();
		

        JLabel name = new JLabel("Name:");
        name.setBounds(800, 100, 100, 25);
        c1.add(name);

        final JTextField nameField = new JTextField();
        nameField.setBounds(950, 100, 150, 25);
        c1.add(nameField);

        JLabel passwd = new JLabel("Password:");
        passwd.setBounds(800, 150, 100, 25);
        c1.add(passwd);

        JPasswordField passwdField = new JPasswordField();
        passwdField.setBounds(950, 150, 150, 25);
        c1.add(passwdField);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(800, 200, 100, 25);
        c1.add(gender);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(950, 200, 70, 25);
        c1.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setBounds(1020, 200, 70, 25);
        c1.add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel hobbies = new JLabel("Hobbies:");
        hobbies.setBounds(800, 250, 100, 25);
        c1.add(hobbies);

        JCheckBox football = new JCheckBox("Football");
        football.setBounds(950, 250, 80, 25);
        c1.add(football);

        JCheckBox basketball = new JCheckBox("Basketball");
        basketball.setBounds(1030, 250, 90, 25);
        c1.add(basketball);

        JCheckBox swimming = new JCheckBox("Swimming");
        swimming.setBounds(1130, 250, 90, 25);
        c1.add(swimming);

        JLabel city = new JLabel("City:");
        city.setBounds(800, 300, 100, 25);
        c1.add(city);

        JComboBox CT = new JComboBox();
        CT.addItem("Select City");
        CT.addItem("New Delhi");
        CT.addItem("Jaipur");
        CT.addItem("Mumbai");
        CT.addItem("Kolkata");
        CT.addItem("Bengaluru");
        CT.addItem("Chennai");
        CT.addItem("Noida");
        CT.addItem("Dehradun");
        CT.setBounds(950, 300, 150, 25);
        c1.add(CT);

        JLabel stateList = new JLabel("State:");
        stateList.setBounds(800, 350, 100, 25);
        c1.add(stateList);

        JComboBox state = new JComboBox();
        state.addItem("Select State");
        state.addItem("Delhi");
        state.addItem("Rajasthan");
        state.addItem("Maharashtra");
        state.addItem("West Bengal");
        state.addItem("Karnataka");
        state.addItem("Tamil Nadu");
        state.addItem("Uttar Pradesh");
        state.addItem("Uttarakhand");
        state.setBounds(950, 350, 150, 25);
        c1.add(state);

        JLabel comments = new JLabel("Comments:");
        comments.setBounds(800, 460, 100, 25);
        c1.add(comments);

        JTextArea commentsField = new JTextArea();
        commentsField.setBounds(950, 460, 300, 150);
        c1.add(commentsField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(900, 650, 80, 30);
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SubmissionFrame(nameField.getText());
            }
        });
        c1.add(submitBtn);

        JButton cancelBtn = new JButton("Exit");
        cancelBtn.setBounds(1000, 650, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        c1.add(cancelBtn);

        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class SubmissionFrame extends JFrame {
    SubmissionFrame(String labelText) {
        super("Form Submitted");
        setLayout(null);

        JLabel label1 = new JLabel("Thank you, " + labelText + "!");
        label1.setBounds(200, 200, 500, 25);
        c1.add(label1);

        JLabel label2 = new JLabel("Your form has been successfully submitted!");
        label2.setBounds(200, 225, 500, 25);
        c1.add(label2);

        JButton closeBtn = new JButton("Exit");
        closeBtn.setBounds(300, 300, 100, 25);
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        c1.add(closeBtn);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

public class Ex_Swing {
    public static void main(String[] args) {
        new Form();
    }
}
