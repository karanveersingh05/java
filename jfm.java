import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.*;

class JavaMedia extends JFrame {
	JButton b1,b2;
	MediaLocator media;
	Player pl;
    public JavaMedia() {
        super("JFM");
        setLayout(null);
		
        b1 = new JButton("Play");
		b2= new JButton("Stop");
		
        b1.setBounds(200, 300, 100, 25);
		b2.setBounds(350, 300, 100, 25);
		
		
        b1.addActionListener(this);
		b2.addActionListener(this);
		
		add(b1);
		add(b2);
    }
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource()== b1){
			media = new MediaLoactor("C:\java\song.mp3");
			pl = Manager.createPlayer(media);
			pl.start();
		}
	}
        
