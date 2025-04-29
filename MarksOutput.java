import javax.swing.*;
import java.awt.*;

public class MarksOutput extends JFrame {
    JLabel lblResult;

    public MarksOutput(int from, int to, int count) {
        setBounds(400, 300, 400, 150);
        setLayout(new FlowLayout());

        lblResult = new JLabel(count + " student(s) scored marks from " + from + " to " + to + ".");
        add(lblResult);

        setVisible(true);
    }
}
