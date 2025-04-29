import java.awt.*;
import java.awt.event.*;

public class WelcomeFrame extends Frame implements ActionListener {

    Label lblname, lblquestions;
    TextField txtname, txtquestions;
    Button btnstart;

    public WelcomeFrame() {
        super("Welcome");
        setLayout(null);
        setSize(400, 300);

        lblname = new Label("Enter Name:");
        lblquestions = new Label("No. of Questions:");

        txtname = new TextField();
        txtquestions = new TextField();

        btnstart = new Button("Start Test");

        lblname.setBounds(50, 70, 100, 30);
        lblquestions.setBounds(50, 120, 120, 30);

        txtname.setBounds(180, 70, 150, 30);
        txtquestions.setBounds(180, 120, 150, 30);

        btnstart.setBounds(140, 180, 100, 35);

        btnstart.addActionListener(this);

        add(lblname);
        add(lblquestions);
        add(txtname);
        add(txtquestions);
        add(btnstart);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = txtname.getText();
        int num = Integer.parseInt(txtquestions.getText());

        if (num > 0 && num <= 5) {
            new TestFrame(name, num);
            this.dispose();
        } else {
            System.out.println("Please enter a valid number (1 to 5).");
        }
    }

    public static void main(String args[]) {
        new WelcomeFrame();
    }
}
