import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TestFrame extends Frame implements ActionListener {

    Label lblquestion;
    CheckboxGroup optionsGroup;
    Checkbox opt1, opt2, opt3, opt4;
    Button btnnext, btnsubmit;

    Connection con;
    Statement st;
    ResultSet rs;

    String username;
    int totalQuestions;
    int currentQuestion = 1;
    int correct = 0;

    String[] questions = new String[6];
    String[][] options = new String[6][4];
    String[] answers = new String[6];

    public TestFrame(String username, int totalQuestions) {
        super("Test");
        setLayout(null);
        setSize(600, 400);

        this.username = username;
        this.totalQuestions = totalQuestions;

        lblquestion = new Label();
        optionsGroup = new CheckboxGroup();
        opt1 = new Checkbox("", optionsGroup, false);
        opt2 = new Checkbox("", optionsGroup, false);
        opt3 = new Checkbox("", optionsGroup, false);
        opt4 = new Checkbox("", optionsGroup, false);

        btnnext = new Button("Next");
        btnsubmit = new Button("Submit");
        btnsubmit.setEnabled(false);

        lblquestion.setBounds(50, 50, 500, 30);
        opt1.setBounds(100, 100, 400, 30);
        opt2.setBounds(100, 140, 400, 30);
        opt3.setBounds(100, 180, 400, 30);
        opt4.setBounds(100, 220, 400, 30);
        btnnext.setBounds(150, 300, 100, 35);
        btnsubmit.setBounds(300, 300, 100, 35);

        btnnext.addActionListener(this);
        btnsubmit.addActionListener(this);

        add(lblquestion);
        add(opt1);
        add(opt2);
        add(opt3);
        add(opt4);
        add(btnnext);
        add(btnsubmit);

        connectDB();
        loadQuestions();

        showQuestion(1);

        setVisible(true);
    }

    public void connectDB() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dbURL = "jdbc:odbc:AccessDB";
            con = DriverManager.getConnection(dbURL);
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadQuestions() {
        try {
            rs = st.executeQuery("SELECT TOP " + totalQuestions + " * FROM awttest ORDER BY RND(id)");

            int i = 1;
            while (rs.next() && i <= totalQuestions) {
                questions[i] = rs.getString("testquestion");
                options[i][0] = rs.getString("option1");
                options[i][1] = rs.getString("option2");
                options[i][2] = rs.getString("option3");
                options[i][3] = rs.getString("option4");
                answers[i] = rs.getString("answer");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showQuestion(int qno) {
        lblquestion.setText("Q" + qno + ": " + questions[qno]);
        opt1.setLabel(options[qno][0]);
        opt2.setLabel(options[qno][1]);
        opt3.setLabel(options[qno][2]);
        opt4.setLabel(options[qno][3]);
        optionsGroup.setSelectedCheckbox(null);

        if (qno == totalQuestions) {
            btnnext.setEnabled(false);
            btnsubmit.setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        Checkbox selected = optionsGroup.getSelectedCheckbox();
        if (selected != null) {
            String selectedAnswer = selected.getLabel();
            if (selectedAnswer.equals(answers[currentQuestion])) {
                correct++;
            }
        }

        if (ae.getSource() == btnnext) {
            currentQuestion++;
            showQuestion(currentQuestion);
        }

        if (ae.getSource() == btnsubmit) {
            new ResultFrame(username, correct, totalQuestions);
            this.dispose();
        }
    }
}
