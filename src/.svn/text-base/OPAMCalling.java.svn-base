import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class OPAMCalling {

	JFrame f=new JFrame("Title");
	private Dimension dim;

	public OPAMCalling(int x,int y,int length,int width,opvalues a)
	{
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		//dim = getSize();


		// dimensions of the op-amp in detailed view
		double ssw = x*dim.width/1920.00;
		double ssh = y*dim.height/1080.00;
		double ssw1 = length*dim.width/1920.00;
		double ssh1 = width*dim.height/1080.00;

		int w = 1000*dim.width/1920;
		int h = 1000*dim.height/1080;

		OPAM op=new OPAM((int)ssw,(int)ssh,(int)ssw1,(int)ssh1,a);
		// op.setLayout(null);


		ssw = (x+length)*dim.width/1920.00;
		ssh = (y+width)*dim.height/1080.00;
		ssw1 = 75*dim.width/1920.00;
		ssh1 = 25*dim.height/1080.00;
		JButton button = new JButton("back"); // buttton to close the window
		button.setBounds(x+length,y+width, 75, 25);

		op.add(button);
		f.add(op);
		f.setSize(w,h);
		f.setVisible(true);
		button.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						//new defaultcircuit();
						f.dispose();
					}
				}
		);

	}
}


