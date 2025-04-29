import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class address extends JFrame implements ActionListener,ItemListener
    {
        //Connection
        Connection con;
        ResultSet rs;
        Statement st;

        //Components
        Container cn;
        JComboBox c1,c2,c3;
        JButton b;
        public address()
            {
                super("My_Frame");
                cn = getContentPane();
                c1 = new JComboBox();
                c2 = new JComboBox();
                c3 = new JComboBox();
                b = new JButton();

                c1.setBounds(100,50,100,25);
                c2.setBounds(100,100,100,25);
                c3.setBounds(100,150,100,25);
                b.setBounds(100,200,100,25);

                
                cn.add(c1);
                cn.add(c2);
                cn.add(c3);
                cn.add(b);
                try 
                {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    con = DriverManager.getConnection("jdbc:odbc:AccessDB");
                    st = con.createStatement();
                    rs = st.executeQuery("Select distinct State from address1");
                    while(rs.next()) 
                    {
                            c1.addItem(rs.getString("State"));        
                        }
                        rs = st.executeQuery("Select distinct City from address1 where State = '"+c1.getSelectedItem()+"'");
                        if(rs.next())
                            {
                                c2.addItem(rs.getString("City"));
                            }
                            rs = st.executeQuery("Select distinct village from address1 where City = '"+c2.getSelectedItem()+"'");
                            if(rs.next())
                            {
                                c3.addItem(rs.getString("village"));
                            }
                        }
                        catch(Exception e)
                        {
                            
                        }
                c1.addItemListener(this);
                c2.addItemListener(this);
                c3.addItemListener(this);
                b.addActionListener(this);
                setLayout(null);
                setSize(400,400);
                setVisible(true);
            }
        public void actionPerformed(ActionEvent ae)
        {
            try 
                {
                    if(ae.getSource() == b)
                        {
                            this.dispose();
                        }
                }
            catch(Exception e)
                {
    
                }
            }
            public void itemStateChanged(ItemEvent ie)
            {
                try 
                    {
                        if(ie.getSource() == c1)
                            {
                                rs = st.executeQuery("Select distinct City from address1 where State = '"+c1.getSelectedItem()+"'");
                                while(rs.next())
                                    {
                                        c2.removeAll();
                                        c2.addItem(rs.getString("City"));
                                    }
                                rs = st.executeQuery("Select distinct village from address1 where City = '"+c2.getSelectedItem()+"'");
                                while(rs.next())
                                    {
                                        c3.addItem(rs.getString("village"));
                                    }
                            }
                    }
                catch(Exception e)
                    {
        
                    }
            }
        public static void main(String s[])
            {
                address ob = new address();
            }
    }