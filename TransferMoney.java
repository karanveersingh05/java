import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TransferMoney extends JFrame implements ActionListener {
    private Container container;
    private JLabel lblFrom, lblTo, lblAmount;
    private JComboBox fromAccountCombo, toAccountCombo;
    private JTextField amountField;
    private JButton btnTransfer;
    private static final String DB_URL = "jdbc:odbc:AccessDB";

    public TransferMoney() {
        super("Transfer Funds");
        setBounds(500, 200, 500, 500);
        setLayout(null);
        container = getContentPane();

        lblFrom = new JLabel("From:", JLabel.RIGHT);
        lblTo = new JLabel("To:", JLabel.RIGHT);
        lblAmount = new JLabel("Amount:", JLabel.RIGHT);
        fromAccountCombo = new JComboBox();
        toAccountCombo = new JComboBox();
        amountField = new JTextField();
        btnTransfer = new JButton("Transfer");

        loadAccountOptions();

        btnTransfer.addActionListener(this);

        lblFrom.setBounds(100, 100, 60, 20);
        fromAccountCombo.setBounds(170, 100, 150, 20);
        lblTo.setBounds(100, 140, 60, 20);
        toAccountCombo.setBounds(170, 140, 150, 20);
        lblAmount.setBounds(100, 180, 60, 20);
        amountField.setBounds(170, 180, 150, 20);
        btnTransfer.setBounds(200, 300, 100, 20);

        container.add(lblFrom);
        container.add(lblTo);
        container.add(lblAmount);
        container.add(fromAccountCombo);
        container.add(toAccountCombo);
        container.add(amountField);
        container.add(btnTransfer);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TransferMoney();
    }

    public void actionPerformed(ActionEvent ae) {
        String fromAccount = (String) fromAccountCombo.getSelectedItem();
        String toAccount = (String) toAccountCombo.getSelectedItem();
        if (fromAccount == null || toAccount == null || amountField.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double transferAmount = 0;
        try {
            transferAmount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount entered", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fromAccount.equals(toAccount)) {
            JOptionPane.showMessageDialog(null, "Sender and Receiver account cannot be the same.", "Account Error", JOptionPane.ERROR_MESSAGE);
        } else {
            transferMoney(Integer.parseInt(fromAccount), Integer.parseInt(toAccount), transferAmount);
        }
    }

    private void loadAccountOptions() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(DB_URL);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ac FROM acc");
            while (rs.next()) {
                String account = rs.getString("ac");
                fromAccountCombo.addItem(account);
                toAccountCombo.addItem(account);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading account information: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {}
        }
    }

    private void transferMoney(int senderId, int receiverId, double amount) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rsSender = null;
        ResultSet rsReceiver = null;
        PreparedStatement psSender = null;
        PreparedStatement psReceiver = null;
        PreparedStatement psTransaction = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(DB_URL);
            con.setAutoCommit(false);
            stmt = con.createStatement();

            rsSender = stmt.executeQuery("SELECT bal FROM acc WHERE ac = " + senderId);
            if (rsSender.next()) {
                double senderBalance = rsSender.getDouble("bal");
                if (senderBalance >= amount) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Complete Funds Transfer? This action cannot be undone.", "Funds Transfer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (confirm == JOptionPane.YES_OPTION) {
                        psSender = con.prepareStatement("UPDATE acc SET bal = ? WHERE ac = ?");
                        psSender.setDouble(1, senderBalance - amount);
                        psSender.setInt(2, senderId);
                        psSender.executeUpdate();

                        rsReceiver = stmt.executeQuery("SELECT bal FROM acc WHERE ac = " + receiverId);
                        if (rsReceiver.next()) {
                            double receiverBalance = rsReceiver.getDouble("bal");
                            psReceiver = con.prepareStatement("UPDATE acc SET bal = ? WHERE ac = ?");
                            psReceiver.setDouble(1, receiverBalance + amount);
                            psReceiver.setInt(2, receiverId);
                            psReceiver.executeUpdate();

                            psTransaction = con.prepareStatement("INSERT INTO transfer (sender_id, receiver_id, amount) VALUES (?, ?, ?)");
                            psTransaction.setInt(1, senderId);
                            psTransaction.setInt(2, receiverId);
                            psTransaction.setDouble(3, amount);
                            psTransaction.executeUpdate();

                            con.commit();
                            JOptionPane.showMessageDialog(null, "Funds Transferred Successfully!", "Transaction Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            con.rollback();
                            JOptionPane.showMessageDialog(null, "Receiver account not found.", "Transaction Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        con.rollback();
                        JOptionPane.showMessageDialog(null, "Funds Transfer Canceled", "Transaction Canceled", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    con.rollback();
                    JOptionPane.showMessageDialog(null, "Insufficient Balance", "Funds Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                con.rollback();
                JOptionPane.showMessageDialog(null, "Sender account not found.", "Transaction Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error processing the transaction: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            try {
                if (con != null) con.rollback();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "Error during rollback: " + se.getMessage(), "Rollback Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (rsSender != null) rsSender.close();
                if (rsReceiver != null) rsReceiver.close();
                if (psSender != null) psSender.close();
                if (psReceiver != null) psReceiver.close();
                if (psTransaction != null) psTransaction.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {}
        }
    }
}
