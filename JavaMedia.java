import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.*;

public class JavaMedia extends JFrame implements ActionListener{
	JButton b1,b2;
	MediaLocator media;
	Player pl;
    public JavaMedia() {
        super("JFM");
        setLayout(null);
		setSize(800,800);
		
        b1 = new JButton("Play");
		b2= new JButton("Stop");
		
        b1.setBounds(200, 300, 100, 25);
		b2.setBounds(350, 300, 100, 25);
		
		
        b1.addActionListener(this);
		b2.addActionListener(this);
		
		add(b1);
		add(b2);
		setVisible(true);
    }
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource()== b1){
			try{
				media = new MediaLocator("file:/C:\\Users\\karan\\OneDrive\\Desktop\\song.mp3");
			    pl = Manager.createPlayer(media);
			    pl.start();
			}
			catch(Exception e){
				
			}
		}
	}

    public static void main(String args[]) {
        JavaMedia ob= new JavaMedia();
    }
}

