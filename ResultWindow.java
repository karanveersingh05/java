import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultWindow extends JFrame implements ActionListener {
    Label lblResult;
    Button btnExit;

    public ResultWindow(String state, String city, String village) {
        setLayout(null);
        setSize(400, 200);
        setTitle("Address Result");

        lblResult = new Label("You live in " + state + ", " + city + ", " + village + ".");
        lblResult.setBounds(50, 50, 300, 30);

        btnExit = new Button("Exit");
        btnExit.setBounds(150, 100, 80, 30);

        add(lblResult);
        add(btnExit);

        btnExit.addActionListener(this);

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnExit) {
            System.exit(0);
        }
    }
}
