import java.awt.*;
import java.awt.event.*;

class f2 extends Frame {
    f2(String labelText) {
        super("Form Submitted");
        setLayout(null);

        Label label1 = new Label("Thank you, " + labelText + "!");
        label1.setBounds(200, 200, 500, 25);
        add(label1);

        Label label2 = new Label("Your form has been successfully submitted!");
        label2.setBounds(200, 225, 500, 25);
        add(label2);

        Button closeBtn = new Button("Exit");
        closeBtn.setBounds(300, 300, 100, 25);
        
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(closeBtn);

        setSize(800, 800);
        setVisible(true);
    }
}

