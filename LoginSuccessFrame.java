import java.awt.*;
import java.awt.event.*;

public class LoginSuccessFrame extends Frame implements ActionListener {

    Label lblmessage;
    Button btnexit;

    public LoginSuccessFrame(String username, String category) {
        super("Login Success");
        setLayout(null);
        setSize(320, 150);

        lblmessage = new Label("Welcome " + username + " (" + category + ")");
        btnexit = new Button("Exit");

        lblmessage.setBounds(40, 40, 250, 30);
        btnexit.setBounds(110, 90, 90, 30);

        btnexit.addActionListener(this);

        add(lblmessage);
        add(btnexit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnexit) {
            dispose();
            System.exit(0);
        }
    }
}
