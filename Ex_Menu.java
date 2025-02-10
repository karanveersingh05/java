import java.awt.*;
import java.awt.event.*;
public class Ex_Menu extends Frame
{
	MenuBar mb;
	Menu F,E,Cl;
	MenuItem op,ct,cp,r,b,g,sv;
	
	public Ex_Menu()
	{
		super("Menu Demo");
		setSize(700,700);
		setLayout(null);
		
		Button clb= new Button("Close");
		clb.setBounds(350,350,150,50);
		add(clb);
		clb.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		
		mb= new MenuBar();
		
		F= new Menu("File");
		E= new Menu("Edit");
		Cl= new Menu("Colour");
		
		op= new MenuItem("Open");
		ct= new MenuItem("Cut");
		cp= new MenuItem("Copy");
		r= new MenuItem("Red");
		b= new MenuItem("Blue");
		g= new MenuItem("Green");
		sv= new MenuItem("Save");
		
		F.add(op);
		F.add(sv);
		Cl.add(r);
		Cl.add(b);
		Cl.add(g);
		E.add(ct);
		E.add(cp);
		E.add(Cl);
		mb.add(F);
		mb.add(E);
		
		setMenuBar(mb);
		
		setVisible(true);
		
		r.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBackground(Color.RED);
            }
		});
		
		b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBackground(Color.BLUE);
            }
		});
		
		g.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBackground(Color.GREEN);
            }
		});
		
	}
	public static void main (String args[])
	{
		Ex_Menu obj= new Ex_Menu();
	
	}
}