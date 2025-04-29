import java.awt.*;
import java.awt.event.*;

public class ResultFrame extends Frame implements ActionListener {

    Label lblresult;
    Button btnexit;

    public ResultFrame(String username, int correct, int total) {
        super("Result");
        setLayout(null);
        setSize(400, 250);

        lblresult = new Label(username + ", you got " + correct + " out of " + total + " correct!");
        btnexit = new Button("Exit");

        lblresult.setBounds(50, 80, 300, 30);
        btnexit.setBounds(140, 140, 100, 35);

        btnexit.addActionListener(this);

        add(lblresult);
        add(btnexit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
}
