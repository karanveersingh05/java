import java.awt.*;
import java.awt.event.*;

public class f2 extends Frame implements ActionListener {
    Button closebtn;
    Label label1, label2;

    public f2(String labeltext) {
        super("Form Submitted");
        setLayout(null);
        setSize(800, 800);

        label1 = new Label("Thank you, " + labeltext + "!");
        label1.setBounds(200, 200, 500, 25);
        add(label1);

        label2 = new Label("Your form has been successfully submitted!");
        label2.setBounds(200, 250, 500, 25);
        add(label2);

        closebtn = new Button("Exit");
        closebtn.setBounds(300, 350, 100, 30);
        closebtn.addActionListener(this);
        add(closebtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == closebtn) {
            System.exit(0);
        }
    }
}
