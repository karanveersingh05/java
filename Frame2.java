import java.awt.*;
import java.awt.event.*;

public class Frame2 extends Frame {

    public Frame2(String username) {
        super("Greeting 2");
        setSize(390, 310);
        setLayout(null);

        Label greeting = new Label("Hello, " + username + "!");
        greeting.setBounds(95, 130, 230, 40);
        add(greeting);

        Button closebtn = new Button("Exit");
        closebtn.setBounds(145, 205, 95, 35);
        add(closebtn);

        closebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
