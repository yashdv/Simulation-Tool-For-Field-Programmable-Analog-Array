import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics2D;

public class PutImageOnJFrame extends JPanel{

	private JButton next1;
	private String[] inst;
	public PutImageOnJFrame(String[] s,JButton butt){
		inst = s;
		next1 = butt;
	}




	// Paints the immage to the background of the frame based on the window size
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		double ssw = dim.width/1920.00;
		double ssh = dim.height/1080.00;
		double sw = 100*ssw;

		int bh = 50*dim.height/1080;
		int bw = 150*dim.width/1920;

		int f = 30*dim.height/1080;
		int f2 = 50*dim.height/1080;
		int w = (int)sw;
		Graphics2D g2d = (Graphics2D)g;

		Image a = Toolkit.getDefaultToolkit().getImage(getClass().getResource("cir2.png")); // background image on page 1
		g2d.drawImage(a,0,0,getSize().width, getSize().height,null);

		g2d.setFont(new Font("TimesRoman", Font.PLAIN, f));
		g2d.setColor(Color.BLACK);

		int i;
		for(i=0;i<8;i++){
			if(i==0){
				g2d.setFont(new Font("TimesRoman", Font.BOLD, f2));
				double sh = (100)*ssh;
				int h = (int)sh;
				g2d.drawString(inst[i], w, h);
			}
			else{
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, f));
				double sh = (100+75*i)*ssh;
				int h = (int)sh;
				g2d.drawString(inst[i], w, h);
			}
		}

		next1.setBounds(getSize().width - 240, getSize().height - 110,bw,bh);  //Sets the location and the size of the button
	}
}

