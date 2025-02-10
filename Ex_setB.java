 import java.awt.*;
 public class Ex_setB extends Frame
 {
	public Ex_setB()
	{
		super("Button");
		setSize(500,500);
		setVisible(true);
		setLayout(null);
		Button btn= new Button("First Button");
		btn.setBounds(50,50,100,50);
		add(btn);
		setVisible(true);
	}
	public static void main (String []s)
	{
	Ex_setB obj= new Ex_setB();
	}
}
		