import java.awt.*;
import java.awt.event.*;

public class Frame1 extends Frame {

    TextField username;
    Button submitbtn;

    public Frame1() {
        super("Greeting");
        setSize(600, 400);
        setLayout(null);

        Label nameLabel = new Label("Enter Your Name:");
        nameLabel.setBounds(50, 100, 100, 30);
        add(nameLabel);

        username = new TextField();
        username.setBounds(170, 100, 150, 30);
        add(username);

        submitbtn = new Button("Submit");
        submitbtn.setBounds(150, 180, 80, 30);
        add(submitbtn);

        submitbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = username.getText();
                if (!name.isEmpty()) {
                    new Frame2(name);
                } else {
                    new Frame2("Stranger");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame1();
    }
}
