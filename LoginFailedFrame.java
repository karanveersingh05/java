import java.awt.*;
import java.awt.event.*;

public class LoginFailedFrame extends Frame implements ActionListener {

    Label lblmsg;
    Button btnexit;

    public LoginFailedFrame() {
        super("Login Failed");
        setLayout(null);
        setSize(350, 150);

        lblmsg = new Label("Login Failed. Invalid credentials or category.");
        btnexit = new Button("Exit");

        lblmsg.setBounds(30, 40, 300, 25);
        btnexit.setBounds(120, 80, 100, 30);

        btnexit.addActionListener(this);

        add(lblmsg);
        add(btnexit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnexit) {
            System.exit(0);
        }
    }
}
