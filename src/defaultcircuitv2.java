import javax.swing.*;
import java.awt.*;

public class defaultcircuitv2 {

	final int OPAM_COUNT=25;
	static int x = 110;
	static int y = 205;
	opvalues temp[] = new opvalues[25];
	JButton button[][] = new JButton[5][5];
	private Dimension dim;


	public defaultcircuitv2(final JFrame frm,String in,opvalues a[],int cnst,int flg,int last_used) {

		dim = Toolkit.getDefaultToolkit().getScreenSize();
		temp = a;

		// scaled value
		/*
		double initial_xcord =320*dim.width/1920.00;
		double initial_ycord = 50*dim.height/1080.00;
		double width = 156*dim.width/1920.00;
		double height = 156*dim.height/1080.00;
		*/
		///*
		double initial_xcord =320;
		double initial_ycord = 50;
		double width = 156;
		double height = 156;
		//*/

		// panel containing op-amps in view 2
		drawcircuitv2 c1 = new drawcircuitv2((int)initial_xcord,(int)initial_ycord,(int)width,(int)height,temp,cnst,flg,last_used);
		c1.setLayout(null);
		c1.setBackground(Color.WHITE);
		frm.setContentPane(c1);
		frm.validate();
		frm.repaint();
	}
}

