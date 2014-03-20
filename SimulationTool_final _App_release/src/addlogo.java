import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class addlogo extends JPanel{
	public addlogo(){
	}

	// Paints the image to the background of the frame based on the window size on page 2
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("doodle2.png")); // get the image
		g.drawImage(a,0,0,getSize().width, getSize().height,null); 
	}
}
