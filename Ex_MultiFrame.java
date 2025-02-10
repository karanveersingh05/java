import java.awt.*;
import java.awt.event.*;

public class Ex_MultiFrame extends Frame {
    public Ex_MultiFrame() {
        super("Frame 1");
        setSize(1000, 1000);
        setLayout(null);

        Button b1 = new Button("Open");
        Button b2 = new Button("Close");

        b1.setBounds(100, 200, 150, 100);
        b2.setBounds(400, 200, 150, 100);

        add(b1);
        add(b2);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Frame2();
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    class Frame2 extends Frame {
        public Frame2() {
            super("Frame 2");
            setSize(700, 700);
            setLayout(null);

            Button clb = new Button("Close");
            clb.setBounds(400, 250, 100, 150);
            add(clb);

            clb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            setVisible(true);
        }
    }

    public static void main(String[] args) {
       Ex_MultiFrame obj= new Ex_MultiFrame();
    }
}

